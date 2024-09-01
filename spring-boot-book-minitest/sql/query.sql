create database book;
use book;

create table book
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255)   NOT NULL,
    author  VARCHAR(255)   NOT NULL,
    price   DECIMAL(10, 2) NOT NULL,
    image   VARCHAR(255)   NULL,
    type_id INT            NOT NULL
);

select * from book;

create table type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

select * from type;

ALTER TABLE book
    ADD CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES type (id);

SELECT type.name, count(book.id) as number
FROM type
         LEFT JOIN book on type.id = type_id
GROUP BY type.name;