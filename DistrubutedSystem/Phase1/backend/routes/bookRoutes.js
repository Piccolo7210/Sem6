import express from 'express';
import { auth, authorize } from '../middleware/auth.js';
import * as bookController from '../controllers/bookController.js';

const router = express.Router();

// Public routes
router.get('/', bookController.getBooks);
router.get('/:id', bookController.getBookById);
router.get('/stats/popular', bookController.getPopularBooks);

// Protected routes - only admin and faculty can modify books
router.post('/', auth, authorize('admin', 'faculty'), bookController.addBook);
router.put('/:id', auth, authorize('admin', 'faculty'), bookController.updateBook);
router.delete('/:id', auth, authorize('admin','faculty'), bookController.deleteBook);

export default router;