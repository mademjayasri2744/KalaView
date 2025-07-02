CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(225) NOT NULL,
    lastName VARCHAR(225),
    email VARCHAR(225) NOT NULL,
    phoneNo VARCHAR(13) NOT NULL,
    password VARCHAR(1000) NOT NULL,
    address LONGTEXT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE artwork (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(225) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    category VARCHAR(225) NOT NULL,
    label VARCHAR(225) NOT NULL,
    price INT NOT NULL,
    likes INT NOT NULL,
    imgUrl VARCHAR(2000) NOT NULL,
    owner_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES user(id)
);

CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(225) NOT NULL,
    price INT NOT NULL,
    address VARCHAR(5000) NOT NULL,
    status VARCHAR(225) NOT NULL,
    ordered_at DATETIME NOT NULL,
    user_id INT NOT NULL,
    artwork_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (artwork_id) REFERENCES artwork(id)
);

CREATE TABLE workshop (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(1000) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    mode VARCHAR(225) NOT NULL,
    organizer_id INT NOT NULL,
    datetime DATETIME NOT NULL,
    venue VARCHAR(2000) NOT NULL,
    total_seats INT NOT NULL,
    registered_seats INT,
    imgUrl VARCHAR(1000) NOT NULL,
    status VARCHAR(225) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (organizer_id) REFERENCES user(id)
);

CREATE TABLE exhibition (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(1000) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    mode VARCHAR(225) NOT NULL,
    datetime DATETIME NOT NULL,
    venue VARCHAR(2000) NOT NULL,
    total_seats INT NOT NULL,
    registered_seats INT,
    imgUrl VARCHAR(1000) NOT NULL,
    status VARCHAR(225) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE exhibitionRegister (
    id INT NOT NULL AUTO_INCREMENT,
    confirm VARCHAR(225) NOT NULL,
    user_id INT NOT NULL,
    exhibition_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (exhibition_id) REFERENCES exhibition(id)
);

CREATE TABLE workshopRegister (
   id INT NOT NULL AUTO_INCREMENT,
   confirm VARCHAR(225) NOT NULL,
   user_id INT NOT NULL,
   workshop_id INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (user_id) REFERENCES user(id),
   FOREIGN KEY (workshop_id) REFERENCES workshop(id)
);
