CREATE TABLE product (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  brand VARCHAR(255),
  madein VARCHAR(255),
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO product (name, brand, madein, price)
VALUES 
    ('iPhone 13', 'Apple', 'USA', 1299),
    ('Galaxy Tab S7', 'Samsung', 'South Korea', 649),
    ('MacBook Pro', 'Apple', 'USA', 1499),
    ('Echo Dot', 'Amazon', 'USA', 49),
    ('Civic', 'Honda', 'Japan', 20950),
    ('Fire TV Stick', 'Amazon', 'USA', 39),
    ('ThinkPad X1 Carbon', 'Dell', 'USA', 1899),
    ('Kindle Paperwhite', 'Amazon', 'USA', 129),
    ('Accord', 'Honda', 'Japan', 24900),
    ('PlayStation 5', 'Sony', 'Japan', 499);
    
INSERT INTO product (name, brand, madein, price)
SELECT 
    CASE FLOOR(RAND()*10) + 1
        WHEN 1 THEN 'iPhone 13'
        WHEN 2 THEN 'Galaxy Tab S7'
        WHEN 3 THEN 'MacBook Pro'
        WHEN 4 THEN 'Echo Dot'
        WHEN 5 THEN 'Civic'
        WHEN 6 THEN 'Fire TV Stick'
        WHEN 7 THEN 'ThinkPad X1 Carbon'
        WHEN 8 THEN 'Kindle Paperwhite'
        WHEN 9 THEN 'Accord'
        WHEN 10 THEN 'PlayStation 5'
    END,
    CASE FLOOR(RAND()*5) + 1
        WHEN 1 THEN 'Apple'
        WHEN 2 THEN 'Samsung'
        WHEN 3 THEN 'Dell'
        WHEN 4 THEN 'Amazon'
        WHEN 5 THEN 'Honda'
    END,
    CASE FLOOR(RAND()*4) + 1
        WHEN 1 THEN 'USA'
        WHEN 2 THEN 'South Korea'
        WHEN 3 THEN 'Japan'
        WHEN 4 THEN 'China'
    END,
    FLOOR(RAND()*4951) + 50
FROM 
    INFORMATION_SCHEMA.COLUMNS 
LIMIT 100;

