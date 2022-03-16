DROP TABLE IF EXISTS users;
CREATE TABLE users
(
 id      serial  PRIMARY KEY,
 name       varchar(50) NOT NULL ,
 phoneNumber int NOT NULL ,
 mail        varchar(50) NOT NULL ,
 roleId      int  not null,
 credentialsId int not null,
	CONSTRAINT fk_roles
      FOREIGN KEY(roleId)
	  REFERENCES roles(id),
	  CONSTRAINT fk_credentials
      FOREIGN KEY(credentialsId)
	  REFERENCES credentials(id)
);
