DROP TABLE IF EXISTS person;
  
CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  full_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  phone_number VARCHAR(250) NOT NULL
);