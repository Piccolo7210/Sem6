import express from 'express';
import { auth, authorize } from '../middleware/auth.js';
import * as loanController from '../controllers/loanController.js';

const router = express.Router();

// Protected routes - all loan operations require authentication
router.post('/', auth, authorize('admin', 'faculty'), loanController.issueLoan);
router.post('/returns', auth, authorize('admin', 'faculty'), loanController.returnBook);
router.get('/overdue', auth, authorize('admin', 'faculty'), loanController.getOverdueLoans);
router.get('/stats/overview', auth, authorize('admin', 'faculty'), loanController.getLoansOverview);
router.get('/:user_id', auth, loanController.getUserLoans);
router.put('/:id/extend', auth, loanController.extendLoan);

export default router;