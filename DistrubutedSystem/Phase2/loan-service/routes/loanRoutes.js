import express from 'express';
import { createLoan, returnBook, getUserLoans, getLoan, checkOverdueLoans } from '../controllers/loanController.js';

const router = express.Router();

// Issue a book to a user
router.post('/loans', createLoan);

// Return a book
router.post('/returns', returnBook);

// Get user's loan history
router.get('/loans/user/:user_id', getUserLoans);

// Get specific loan details
router.get('/loans/:id', getLoan);

// Get overdue loans
router.get('/loans/overdue', checkOverdueLoans);

export default router;