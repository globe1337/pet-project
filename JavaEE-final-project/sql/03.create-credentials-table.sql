DROP TABLE IF EXISTS credentials;
CREATE TABLE credentials
(
 id serial  PRIMARY KEY ,
 password    varchar(50) NOT NULL ,
 login        varchar(50) NOT NULL );
