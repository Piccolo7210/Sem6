import express from 'express';
import {
    getAllBooks,
    getBook,
    createBook,
    updateBook,
    deleteBook,
    checkAvailability,
    updateAvailability
} from '../controllers/bookController.js';

const router = express.Router();

router.get('/', getAllBooks);
router.get('/:id', getBook);
router.post('/', createBook);
router.put('/:id', updateBook);
router.delete('/:id', deleteBook);
router.get('/:id/availability', checkAvailability);
router.put('/:id/availability', updateAvailability);

export default router;