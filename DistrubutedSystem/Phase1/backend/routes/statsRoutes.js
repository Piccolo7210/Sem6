import express from 'express';
import { auth, authorize } from '../middleware/auth.js';
import * as statsController from '../controllers/statsController.js';

const router = express.Router();

// All statistics routes require authentication and admin/faculty role
router.get('/books/popular',  statsController.getPopularBooks);
router.get('/users/active', statsController.getActiveUsers);
router.get('/overview',  statsController.getSystemOverview);

export default router;