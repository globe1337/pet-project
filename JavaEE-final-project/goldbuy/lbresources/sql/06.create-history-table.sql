
CREATE TABLE history
(
 id     	  serial PRIMARY KEY ,
 sellingPrice float ,
 sellingDate date ,
 productId    int NOT NULL ,
 customerId   int  ,
CONSTRAINT fk_products
      FOREIGN KEY(productId)
	  REFERENCES products(id),
CONSTRAINT fk_users
      FOREIGN KEY(customerId)
	  REFERENCES users(id)
);
