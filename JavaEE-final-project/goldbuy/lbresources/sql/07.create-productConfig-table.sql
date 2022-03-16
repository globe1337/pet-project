
CREATE TABLE productConfig
(
 id        serial PRIMARY KEY ,
 maxPrice  float NOT NULL ,
 productId int NOT NULL ,
 minPrice  float NOT NULL ,
 priceStep float NOT NULL ,
 frequency varchar(50) NOT NULL ,
CONSTRAINT fk_products
      FOREIGN KEY(productId)
	  REFERENCES products(id) );