import express from 'express';
import { auth, authorize } from '../middleware/auth.js';
import * as userController from '../controllers/userController.js';

const router = express.Router();

// Public routes
router.post('/register', userController.register);
router.post('/login', userController.login);

// Protected routes
router.get('/profile', auth, userController.getProfile);
router.patch('/profile', auth, userController.updateProfile);
router.get('/stats/active', auth, authorize('admin', 'faculty'), userController.getActiveUsers);
router.get('/:id', auth, userController.getUserById);

export default router;