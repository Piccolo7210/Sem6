import Loan from '../models/Loan.js';
import fetch from 'node-fetch';

const BOOK_SERVICE_URL = process.env.BOOK_SERVICE_URL || 'http://localhost:3001';
const USER_SERVICE_URL = process.env.USER_SERVICE_URL || 'http://localhost:3002';

// Utility function to fetch book details
async function getBookDetails(bookId) {
    try {
        const response = await fetch(`${BOOK_SERVICE_URL}/api/books/${bookId}`);
        if (response.status === 404) {
            throw new Error('Book not found');
        }
        if (!response.ok) {
            throw new Error('Book service unavailable');
        }
        return await response.json();
    } catch (error) {
        if (error.message === 'Book not found') {
            throw error;
        }
        throw new Error('Book service unavailable');
    }
}

// Utility function to fetch user details
async function getUserDetails(userId) {
    try {
        const response = await fetch(`${USER_SERVICE_URL}/api/users/${userId}`);
        if (response.status === 404) {
            throw new Error('User not found');
        }
        if (!response.ok) {
            throw new Error('User service unavailable');
        }
        return await response.json();
    } catch (error) {
        if (error.message === 'User not found') {
            throw error;
        }
        throw new Error('User service unavailable');
    }
}

// Utility function to check book availability
async function checkBookAvailability(bookId) {
    const book = await getBookDetails(bookId);
    return book.available_copies > 0;
}

// Utility function to update book availability
async function updateBookAvailability(bookId, operation) {
    const book = await getBookDetails(bookId);
    
    try {
        const updateResponse = await fetch(`${BOOK_SERVICE_URL}/api/books/${bookId}/availability`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                available_copies: book.available_copies + (operation === 'increment' ? 1 : -1),
                operation
            })
        });
        
        if (!updateResponse.ok) {
            throw new Error('Failed to update book availability');
        }
    } catch (error) {
        throw new Error('Book service unavailable');
    }
}

// Utility function to check user status
async function checkUserStatus(userId) {
    const user = await getUserDetails(userId);
    return {
        active: user.active,
        membershipStatus: user.membershipStatus
    };
}

// Create a new loan
export const createLoan = async (req, res) => {
    try {
        const { user_id, book_id, due_date } = req.body;
        
        // Validate user and book existence
        try {
            await getUserDetails(user_id);
        } catch (error) {
            if (error.message === 'User not found') {
                return res.status(404).json({ message: 'User not found' });
            }
            return res.status(503).json({ message: 'User service unavailable' });
        }

        // Check book availability
        try {
            const isAvailable = await checkBookAvailability(book_id);
            if (!isAvailable) {
                return res.status(400).json({ message: 'Book has no available copies' });
            }
        } catch (error) {
            if (error.message === 'Book not found') {
                return res.status(404).json({ message: 'Book not found' });
            }
            return res.status(503).json({ message: 'Book service unavailable' });
        }

        const loan = new Loan({
            user_id,
            book_id,
            issue_date: new Date(),
            due_date: new Date(due_date),
            status: 'ACTIVE'
        });

        try {
            await updateBookAvailability(book_id, 'decrement');
        } catch (error) {
            return res.status(503).json({ message: 'Failed to update book availability' });
        }

        const newLoan = await loan.save();
        res.status(201).json({
            id: newLoan._id,
            user_id: newLoan.user_id,
            book_id: newLoan.book_id,
            issue_date: newLoan.issue_date,
            due_date: newLoan.due_date,
            status: newLoan.status
        });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Return a book
export const returnBook = async (req, res) => {
    try {
        const { loan_id } = req.body;
        const loan = await Loan.findById(loan_id);
        
        if (!loan) {
            return res.status(404).json({ message: 'Loan not found' });
        }
        if (loan.status === 'RETURNED') {
            return res.status(400).json({ message: 'Book already returned' });
        }

        loan.status = 'RETURNED';
        loan.return_date = new Date();

        try {
            await updateBookAvailability(loan.book_id, 'increment');
        } catch (error) {
            return res.status(503).json({ message: 'Book service unavailable' });
        }

        const updatedLoan = await loan.save();
        res.json({
            id: updatedLoan._id,
            user_id: updatedLoan.user_id,
            book_id: updatedLoan.book_id,
            issue_date: updatedLoan.issue_date,
            due_date: updatedLoan.due_date,
            return_date: updatedLoan.return_date,
            status: updatedLoan.status
        });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Get all loans
export const getAllLoans = async (req, res) => {
    try {
        const loans = await Loan.find();
        res.json(loans);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Get loans by user
export const getUserLoans = async (req, res) => {
    try {
        const user_id = req.params.user_id;
        const loans = await Loan.find({ user_id });

        // Get book details for each loan
        const loansWithDetails = await Promise.all(loans.map(async (loan) => {
            try {
                const book = await getBookDetails(loan.book_id);
                return {
                    id: loan._id,
                    book: {
                        id: book.id,
                        title: book.title,
                        author: book.author
                    },
                    issue_date: loan.issue_date,
                    due_date: loan.due_date,
                    return_date: loan.return_date || null,
                    status: loan.status
                };
            } catch (error) {
                return null;
            }
        }));

        // Filter out any loans where we couldn't get book details
        const validLoans = loansWithDetails.filter(loan => loan !== null);

        res.json({
            loans: validLoans,
            total: validLoans.length
        });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Get loan by ID with detailed information
export const getLoan = async (req, res) => {
    try {
        const loan = await Loan.findById(req.params.id);
        if (!loan) {
            return res.status(404).json({ message: 'Loan not found' });
        }

        // Get user and book details
        try {
            const [user, book] = await Promise.all([
                getUserDetails(loan.user_id),
                getBookDetails(loan.book_id)
            ]);

            res.json({
                id: loan._id,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email
                },
                book: {
                    id: book.id,
                    title: book.title,
                    author: book.author
                },
                issue_date: loan.issue_date,
                due_date: loan.due_date,
                return_date: loan.return_date || null,
                status: loan.status
            });
        } catch (error) {
            if (error.message.includes('not found')) {
                return res.status(404).json({ message: error.message });
            }
            return res.status(503).json({ message: 'Service unavailable' });
        }
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Check for overdue loans
export const checkOverdueLoans = async (req, res) => {
    try {
        const now = new Date();
        const overdueLoans = await Loan.find({
            status: 'ACTIVE',
            dueDate: { $lt: now }
        });

        // Update status of overdue loans
        await Promise.all(overdueLoans.map(loan => {
            loan.status = 'OVERDUE';
            return loan.save();
        }));

        res.json(overdueLoans);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};