import express from 'express';
import {
    getUser,
    updateUser,
    deleteUser,
    getActiveUsers,
    getUserCount, // Import the getUserCount function
    registerUser
} from '../controllers/userController.js';

const router = express.Router();

router.get('/stats/active', getActiveUsers);  // New endpoint for active users
router.get('/:id', getUser);
router.post('/register', registerUser);
router.put('/:id', updateUser);
router.delete('/:id', deleteUser);
router.get('/count', getUserCount); // New endpoint for user count

export default router;