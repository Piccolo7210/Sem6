import 'dotenv/config';
import express from 'express';
import cors from 'cors';
import mongoose from 'mongoose';
import bookRoutes from './routes/bookRoutes.js';

const app = express();
const PORT = process.env.PORT || 3001;

// Middleware
app.use(cors());
app.use(express.json());

// MongoDB Connection
mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/book-service', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => console.log('Connected to MongoDB'))
.catch((err) => console.error('MongoDB connection error:', err));

// Routes
app.use('/api/books', bookRoutes);

// Basic health check route
app.get('/health', (req, res) => {
    res.status(200).json({ status: 'ok', service: 'book-service' });
});

// Start server
app.listen(PORT, () => {
    console.log(`Book service running on port ${PORT}`);
});