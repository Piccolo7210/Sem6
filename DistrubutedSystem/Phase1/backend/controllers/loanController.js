import Loan from '../models/Loan.js';
import Book from '../models/Book.js';

export const issueLoan = async (req, res) => {
    try {
        const { book_id, user_id, due_date } = req.body;
        const book = await Book.findById(book_id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        
        if (book.available_copies <= 0) {
            return res.status(400).json({ message: 'No copies available' });
        }

        const existingLoan = await Loan.findOne({
            user_id,
            book_id,
            status: 'ACTIVE'
        });

        if (existingLoan) {
            return res.status(400).json({ message: 'You already have an active loan for this book' });
        }

       
        const loan = new Loan({
            user_id,
            book_id,
            due_date,
            issue_date: new Date(),
            status: 'ACTIVE'
        });

        book.available_copies -= 1;
        book.borrowCount += 1;

        await Promise.all([loan.save(), book.save()]);

        res.status(201).json({
            id: loan._id,
            user_id: loan.user_id,
            book_id: loan.book_id,
            issue_date: loan.issue_date,
            due_date: loan.due_date,
            status: loan.status
        });
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

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

        
        const book = await Book.findById(loan.book_id);
        book.available_copies += 1;

        await Promise.all([loan.save(), book.save()]);

        
        res.json({
            id: loan._id,
            user_id: loan.user_id,
            book_id: loan.book_id,
            issue_date: loan.issue_date,
            due_date: loan.due_date,
            return_date: loan.return_date,
            status: loan.status
        });
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

export const getUserLoans = async (req, res) => {
    try {
        const loans = await Loan.find({ user_id: req.params.user_id })
            .populate('book_id')
            .sort({ createdAt: -1 });

        const formattedLoans = loans.map(loan => ({
            id: loan._id,
            book: {
                id: loan.book_id._id,
                title: loan.book_id.title,
                author: loan.book_id.author
            },
            issue_date: loan.issue_date,
            due_date: loan.due_date,
            return_date: loan.return_date || null,
            status: loan.status
        }));

        res.json(formattedLoans);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

export const getOverdueLoans = async (req, res) => {
    try {
        const loans = await Loan.find({
            status: 'ACTIVE',
            due_date: { $lt: new Date() }
        })
        .populate('user_id')
        .populate('book_id');

        const overdueLoans = loans.map(loan => ({
            id: loan._id,
            user: {
                id: loan.user_id._id,
                name: loan.user_id.name,
                email: loan.user_id.email
            },
            book: {
                id: loan.book_id._id,
                title: loan.book_id.title,
                author: loan.book_id.author
            },
            issue_date: loan.issue_date,
            due_date: loan.due_date,
            days_overdue: Math.ceil((Date.now() - loan.due_date) / (1000 * 60 * 60 * 24))
        }));

        res.json(overdueLoans);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

export const extendLoan = async (req, res) => {
    try {
        const { extension_days } = req.body;
        const loan = await Loan.findById(req.params.id);

        if (!loan) {
            return res.status(404).json({ message: 'Loan not found' });
        }

        if (loan.status !== 'ACTIVE') {
            return res.status(400).json({ message: 'Can only extend active loans' });
        }

        if (loan.extensions_count >= 2) {
            return res.status(400).json({ message: 'Maximum extensions reached' });
        }

        const original_due_date = loan.due_date;
        loan.due_date = new Date(loan.due_date.getTime() + extension_days * 24 * 60 * 60 * 1000);
        loan.extensions_count += 1;

        await loan.save();

        res.json({
            ...loan.toObject(),
            original_due_date,
            extended_due_date: loan.due_date
        });
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};