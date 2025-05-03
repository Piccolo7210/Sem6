import express from 'express';
import { auth } from '../middleware/auth.js';
import * as userController from '../controllers/userController.js';

const router = express.Router();

// Public routes
router.post('/register', userController.register);
router.post('/login', userController.login);

// Protected routes
router.get('/profile', auth, userController.getProfile);
router.patch('/profile', auth, userController.updateProfile);
router.get('/:id', auth, userController.getUserById);

export default router;