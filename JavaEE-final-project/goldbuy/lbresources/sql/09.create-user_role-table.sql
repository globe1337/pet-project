CREATE TABLE user_role (
  userId int NOT NULL,
  roleId int NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY(userId)
	REFERENCES users(id),
  CONSTRAINT fk_role FOREIGN KEY(roleId)
	REFERENCES roles(id));


