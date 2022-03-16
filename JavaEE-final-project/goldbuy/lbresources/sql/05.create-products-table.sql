
CREATE TABLE products
(
 id  serial PRIMARY KEY ,
 categoryId int NOT NULL ,
 name       varchar(50) NOT NULL ,
 addedDate  date NOT NULL ,
 status     varchar(6) NOT NULL ,
 price      float NOT NULL ,
 userId     int NOT NULL ,
CONSTRAINT fk_caregory
      FOREIGN KEY(categoryId)
	  REFERENCES category(id),
	CONSTRAINT fk_users
      FOREIGN KEY(userId)
	  REFERENCES users(id)
);