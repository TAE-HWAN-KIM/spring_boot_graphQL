DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
  
  
  
  -------------- 책 -----------------------
  DROP TABLE IF EXISTS BOOKS;
  
  CREATE TABLE BOOKS(
  bookId INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  pageCount  INT,
  authorId VARCHAR(250) DEFAULT NULL
);

-------------작성자 ----------------------
  DROP TABLE IF EXISTS AUTHORS;
  
  CREATE TABLE AUTHORS(
  authorId INT AUTO_INCREMENT  PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) DEFAULT NULL
);


----------------사용자--------------------
  DROP TABLE IF EXISTS USER;
  
  CREATE TABLE USER(
  userId INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);