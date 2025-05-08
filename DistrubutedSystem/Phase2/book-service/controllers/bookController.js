import Book from '../models/Book.js';

// Get all books
export const getAllBooks = async (req, res) => {
    try {
        const books = await Book.find();
        res.json(books);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Get a single book
export const getBook = async (req, res) => {
    try {
        const book = await Book.findById(req.params.id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        res.json(book);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Create a book
export const createBook = async (req, res) => {
    const book = new Book(req.body);
    try {
        const newBook = await book.save();
        res.status(201).json(newBook);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

// Update a book
export const updateBook = async (req, res) => {
    try {
        const book = await Book.findById(req.params.id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        Object.assign(book, req.body);
        const updatedBook = await book.save();
        res.json(updatedBook);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

// Delete a book
export const deleteBook = async (req, res) => {
    try {
        const book = await Book.findById(req.params.id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        await book.deleteOne();
        res.json({ message: 'Book deleted' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Check book availability
export const checkAvailability = async (req, res) => {
    try {
        const book = await Book.findById(req.params.id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        res.json({ available: book.available });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

// Update book availability
export const updateAvailability = async (req, res) => {
    try {
        const book = await Book.findById(req.params.id);
        if (!book) {
            return res.status(404).json({ message: 'Book not found' });
        }
        if (typeof req.body.available !== 'number') {
            return res.status(400).json({ message: 'Available quantity must be a number' });
        }
        if (req.body.available > book.quantity) {
            return res.status(400).json({ message: 'Available quantity cannot exceed total quantity' });
        }
        book.available = req.body.available;
        const updatedBook = await book.save();
        res.json(updatedBook);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};