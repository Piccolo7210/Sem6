import express from 'express';
import {
    searchBooks,
    getBookbyID,
    createBook,
    updateBook,
    deleteBook,
    checkAvailability,
    updateAvailability,
    getPopularBooks,
    getBookStats // Importing the new controller function
} from '../controllers/bookController.js';

const router = express.Router();

router.get('/', searchBooks);
router.get('/popular', getPopularBooks);  // New endpoint for popular books
router.get('/stats', getBookStats); // New endpoint for book statistics
router.get('/:id', getBookbyID);
router.post('/', createBook);
router.put('/:id', updateBook);
router.delete('/:id', deleteBook);
router.get('/:id/availability', checkAvailability);
router.patch('/:id/availability', updateAvailability);

export default router;