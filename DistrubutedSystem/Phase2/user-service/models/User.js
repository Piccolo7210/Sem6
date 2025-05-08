import mongoose from 'mongoose';

const userSchema = new mongoose.Schema({
    username: {
        type: String,
        required: true,
        unique: true
    },
    email: {
        type: String,
        required: true,
        unique: true
    },
    name: {
        type: String,
        required: true
    },
    active: {
        type: Boolean,
        default: true
    },
    membershipStatus: {
        type: String,
        enum: ['REGULAR', 'PREMIUM'],
        default: 'REGULAR'
    }
}, {
    timestamps: true
});

export default mongoose.model('User', userSchema);