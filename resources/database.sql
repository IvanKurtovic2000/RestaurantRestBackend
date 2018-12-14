/* Drop existing tables */
DROP TABLE PRODUCT;


CREATE TABLE PRODUCT (
  id int not null AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) not null,
  description TEXT not null,
  price double not null,
  category VARCHAR(255) not null,
  creationdate TIMESTAMP
);


INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, CATEGORY, CREATIONDATE)
VALUES ('Schnitzel', 'Schnitzel mit Beilagen aus der Region', 8, 'Classik', CURRENT_TIMESTAMP),
 	   ('Hamburger', 'Hamburger mit Beilagen aus der Region', 6, 'Classik', CURRENT_TIMESTAMP),
       ('Pizza', 'Pizza Salami', 6, 'Classik', CURRENT_TIMESTAMP),
       ('Nudeln', 'Nudeln mit Soße', 7, 'Classik', CURRENT_TIMESTAMP);