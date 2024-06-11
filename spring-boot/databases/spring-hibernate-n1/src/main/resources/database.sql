-- Dữ liệu mẫu cho bảng Author
INSERT INTO Author (id, name) VALUES
    (1, 'J.K. Rowling'),
    (2, 'George R.R. Martin'),
    (3, 'Stephen King'),
    (4, 'Jane Austen'),
    (5, 'Leo Tolstoy');

-- Dữ liệu mẫu cho bảng Book
INSERT INTO Book (id, title, author_id) VALUES
    (1, 'Harry Potter and the Philosopher''s Stone', 1),
    (2, 'Harry Potter and the Chamber of Secrets', 1),
    (3, 'A Game of Thrones', 2),
    (4, 'A Clash of Kings', 2),
    (5, 'The Shining', 3),
    (6, 'Carrie', 3),
    (7, 'Pride and Prejudice', 4),
    (8, 'Sense and Sensibility', 4),
    (9, 'War and Peace', 5),
    (10, 'Anna Karenina', 5);
