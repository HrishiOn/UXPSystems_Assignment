CREATE TABLE USER_DETAILS (
    Id int NOT NULL AUTO_INCREMENT,
    userName varchar (255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    status varchar(255) DEFAULT 'Deactivated'
);