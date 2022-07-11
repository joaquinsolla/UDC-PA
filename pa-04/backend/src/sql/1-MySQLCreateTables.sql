DROP TABLE Bid;
DROP TABLE Product;
DROP TABLE Category;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) COLLATE latin1_bin NOT NULL,
    CONSTRAINT CategoryPK PRIMARY KEY (id),
    CONSTRAINT CategoryNameUniqueKey UNIQUE (name)
) ENGINE = InnoDB;

CREATE INDEX CategoryIndexByName ON Category (name);

CREATE TABLE Product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    categoryId BIGINT NOT NULL,
    name VARCHAR(60) COLLATE latin1_bin NOT NULL,
    description VARCHAR(500) NOT NULL,
    publicationDate DATETIME NOT NULL,
    expirationDate DATETIME NOT NULL,
    initialPrice DECIMAL(11,2) NOT NULL,
    currentPrice DECIMAL(11,2) NOT NULL,
    shippingInfo VARCHAR(500) NOT NULL,
    winnerBidId BIGINT,
    CONSTRAINT ProductPK PRIMARY KEY (id),
    CONSTRAINT ProductUserIdFK FOREIGN KEY (userId)
        REFERENCES User (id),
    CONSTRAINT ProductCategoryIdFK FOREIGN KEY (categoryId)
        REFERENCES Category (id)
) ENGINE = InnoDB;

CREATE INDEX ProductIndexByExpirationDate ON Product (expirationDate);

CREATE TABLE Bid (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    productId BIGINT NOT NULL,
    date DATETIME NOT NULL,
    maxBid DECIMAL(11,2) NOT NULL,
    CONSTRAINT BidPK PRIMARY KEY (id),
    CONSTRAINT BidUserIdFK FOREIGN KEY (userId)
        REFERENCES User (id),
    CONSTRAINT BidProductIdFK FOREIGN KEY (productId)
        REFERENCES Product (id)
) ENGINE = InnoDB;

CREATE INDEX BidIndexByDate ON Bid (date);
