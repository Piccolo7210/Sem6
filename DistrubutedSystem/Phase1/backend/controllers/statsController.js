import Book from '../models/Book.js';
import User from '../models/User.js';
import Loan from '../models/Loan.js';

export const getPopularBooks = async (req, res) => {
    try {
        const popularBooks = await Book.find()
            .sort({ borrowCount: -1 })
            .limit(10)
            .select('title author borrowCount');

        res.json(popularBooks.map(book => ({
            book_id: book._id,
            title: book.title,
            author: book.author,
            borrow_count: book.borrowCount
        })));
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

export const getActiveUsers = async (req, res) => {
    try {
        const activeLoans = await Loan.aggregate([
            {
                $group: {
                    _id: '$user_id',
                    books_borrowed: { $sum: 1 },
                    current_borrows: {
                        $sum: {
                            $cond: [{ $eq: ['$status', 'ACTIVE'] }, 1, 0]
                        }
                    }
                }
            },
            { $sort: { books_borrowed: -1 } },
            { $limit: 10 }
        ]);

        const userIds = activeLoans.map(loan => loan._id);
        const users = await User.find({ _id: { $in: userIds } });

        const activeUsersData = activeLoans.map(loan => {
            const user = users.find(u => u._id.equals(loan._id));
            return {
                user_id: loan._id,
                name: user.name,
                books_borrowed: loan.books_borrowed,
                current_borrows: loan.current_borrows
            };
        });

        res.json(activeUsersData);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

export const getSystemOverview = async (req, res) => {
    try {
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const [
            totalBooks,
            totalUsers,
            booksAvailable,
            activeLoans,
            overdueLoans,
            loansToday,
            returnsToday
        ] = await Promise.all([
            Book.countDocuments(),
            User.countDocuments(),
            Book.aggregate([{ $group: { _id: null, total: { $sum: '$available_copies' } } }]),
            Loan.countDocuments({ status: 'ACTIVE' }),
            Loan.countDocuments({ status: 'ACTIVE', due_date: { $lt: new Date() } }),
            Loan.countDocuments({ issue_date: { $gte: today } }),
            Loan.countDocuments({ return_date: { $gte: today } })
        ]);

        res.json({
            total_books: totalBooks,
            total_users: totalUsers,
            books_available: booksAvailable[0]?.total || 0,
            books_borrowed: activeLoans,
            overdue_loans: overdueLoans,
            loans_today: loansToday,
            returns_today: returnsToday
        });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};