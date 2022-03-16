DROP TABLE IF EXISTS productConfig;
CREATE TABLE productConfig
(
 id        serial PRIMARY KEY ,
 maxPrice  int NOT NULL ,
 productId int NOT NULL ,
 minPrice  int NOT NULL ,
 priceStep int NOT NULL ,
 frequency varchar(50) NOT NULL ,
CONSTRAINT fk_products
      FOREIGN KEY(productId)
	  REFERENCES products(id) );