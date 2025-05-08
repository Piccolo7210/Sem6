import 'dotenv/config';
import express from 'express';
import cors from 'cors';
import mongoose from 'mongoose';
import loanRoutes from './routes/loanRoutes.js';

const app = express();
const PORT = process.env.PORT || 3002;

// Middleware
app.use(cors());
app.use(express.json());

// MongoDB Connection
mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/loan-service', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => console.log('Connected to MongoDB'))
.catch((err) => console.error('MongoDB connection error:', err));

// Routes
app.use('/api', loanRoutes);

// Basic health check route
app.get('/health', (req, res) => {
    res.status(200).json({ status: 'ok', service: 'loan-service' });
});

// Start server
app.listen(PORT, () => {
    console.log(`Loan service running on port ${PORT}`);
});