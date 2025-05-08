import mongoose from 'mongoose';

const loanSchema = new mongoose.Schema({
    userId: {
        type: String,
        required: true
    },
    bookId: {
        type: String,
        required: true
    },
    status: {
        type: String,
        enum: ['ACTIVE', 'RETURNED', 'OVERDUE'],
        default: 'ACTIVE'
    },
    dueDate: {
        type: Date,
        required: true
    },
    returnDate: {
        type: Date
    }
}, {
    timestamps: true
});

// Index for efficient queries
loanSchema.index({ userId: 1, status: 1 });
loanSchema.index({ bookId: 1, status: 1 });

export default mongoose.model('Loan', loanSchema);