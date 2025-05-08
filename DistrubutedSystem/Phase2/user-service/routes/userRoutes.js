import express from 'express';
import {
    getAllUsers,
    getUser,
    createUser,
    updateUser,
    deleteUser,
    updateMembershipStatus,
    checkUserStatus
} from '../controllers/userController.js';

const router = express.Router();

router.get('/', getAllUsers);
router.get('/:id', getUser);
router.post('/', createUser);
router.put('/:id', updateUser);
router.delete('/:id', deleteUser);
router.put('/:id/membership', updateMembershipStatus);
router.get('/:id/status', checkUserStatus);

export default router;