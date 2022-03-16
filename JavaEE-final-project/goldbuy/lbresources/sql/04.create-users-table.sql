
CREATE TABLE users
(
 id      serial  PRIMARY KEY,
 name       varchar(50) NOT NULL ,
 phoneNumber int NOT NULL ,
 mail        varchar(50) NOT NULL ,
 credentialsId int not null,
	  CONSTRAINT fk_credentials
      FOREIGN KEY(credentialsId)
	  REFERENCES credentials(id)
);
