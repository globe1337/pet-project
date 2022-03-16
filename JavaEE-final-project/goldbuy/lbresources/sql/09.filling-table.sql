insert into roles(name) values('Admin');
insert into roles(name) values('Moderator');
insert into roles(name) values('User');

insert into credentials(password,login) values('12345','root');
insert into credentials(password,login) values('11111','ogurec');
insert into credentials(password,login) values('user','user');

insert into users(name,phonenumber,mail,roleId,credentialsId) values('Oleg',123123,'oleg@gmail.com',1,1);
insert into users(name,phonenumber,mail,roleId,credentialsId) values('Vasya',65556,'vasya@gmail.com',2,2);
insert into users(name,phonenumber,mail,roleId,credentialsId) values('Petya',767544,'petyag@gmail.com',3,3);

insert into category(name) values('phone');
insert into category(name) values('car');
insert into category(name) values('furniture');
insert into category(name) values('TVs');
insert into category(name) values('electronics');

insert into products(categoryId,name,addedDate,status,price,userId) values(1,'iphone 6','2021-09-10','active',400,3);
insert into products(categoryId,name,addedDate,status,price,userId) values(4,'samsung','2021-08-10','active',500,2);
insert into products(categoryId,name,addedDate,status,price,userId) values(2,'audi','2021-02-10','active',5400,1);

insert into productConfig(maxprice,productId,minprice,priceStep,frequency) values(600,1,200,20,5);
insert into productConfig(maxprice,productId,minprice,priceStep,frequency) values(800,2,450,50,10);
insert into productConfig(maxprice,productId,minprice,priceStep,frequency) values(600,3,4900,10,4);

insert into history(productId) values(1);
insert into history(productId) values(2);
insert into history(productId) values(3);

insert into user_role(userId,roleId) values(1,1);
insert into user_role(userId,roleId) values(1,2);
insert into user_role(userId,roleId) values(1,3);
insert into user_role(userId,roleId) values(2,2);
insert into user_role(userId,roleId) values(2,3);
insert into user_role(userId,roleId) values(3,3);
