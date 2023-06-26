CREATE USER 'springstudent'@'localhost' IDENTIFIED BY 'springstudent';
GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';
ALTER USER 'springstudent'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springstudent';

drop database if exists spring_boot_asm01;
create database spring_boot_asm01;
use spring_boot_asm01;

drop table if exists role;
create table role(
id bigint,
role_name varchar(255),
primary key(id),
constraint ROLE_UNIQUE_NAME unique(role_name)
);

drop table if exists user;
create table user(
id bigint auto_increment,
address varchar(255),
email varchar(255),
full_name varchar(255),
note varchar(255),
password varchar(128),
phone_number varchar(255),
status bigint,
user_name varchar(255),
created varchar(255),
role_id bigint,
primary key(id),
constraint USER_UNIQUE_USERNAME unique(user_name),
constraint USER_UNIQUE_EMAIL unique(email),
constraint FK_ROLE_ID foreign key (role_id) references role(id)
);

drop table if exists donation;
create table donation(
id bigint auto_increment,
code varchar(255) unique,
created varchar(255),
description varchar(255),
end_date date,
money bigint,
name varchar(255),
organization_name varchar(255),
phone_number varchar(255),
start_date date,
status bigint,
primary key(id),
constraint DONATION_UNIQUE_CODE unique(code)
);

drop table if exists user_donation;
create table user_donation(
id bigint auto_increment,
created varchar(255),
money bigint,
name varchar(255),
date_donation date,
status bigint,
text varchar(255),
donation_id bigint,
user_id bigint,
primary key(id),
constraint FK_DONATION_ID
foreign key (donation_id) references donation(id),
constraint FK_USER_ID
foreign key (user_id) references user(id)
);

-- add some data
-- data for role table
insert into role(id,role_name) values
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

-- data for user
insert into user(id,address,email,full_name,note,password,phone_number,status,user_name,created,role_id) values
(1,'Long An', 'truong@gmail.com','Le Nhat Truong','I am admin 1','123','0912345678', 1,'admin1','create ok',1),
(2,'Can Tho', 'trang@gmail.com','Ly Thi Trang','I am user 1','123','0922345678', 1,'user1','create ok',2),
(3,'HCM City', 'trong@gmail.com','Luu Van Trong','I am user 2','123','0932345678', 1,'user2','create ok',2),
(4,'Ha Noi', 'trung@gmail.com','Nguyen Anh Trung','I am user 3','123','0942345678', 1,'user3','create ok',2),
(5,'Da Nang', 'son@gmail.com','Nguyen Van Son','I am user 4','123','0952345678', 1,'user4','create ok',2),
(6,'Long An', 'Linh@gmail.com','Luu Thi Linh','I am user 5','123','0962345678', 1,'usrer5','create ok',2);

-- data for donation
insert into donation(id,code,created,description,end_date,money,name,organization_name,phone_number,start_date,status) values
(1,'D001','create donation DOO1 ok','I donate D001','2023-10-11', 0,'Donate For A','Oganizarion ABC', '0111111111', '2023-05-20',2),
(2,'D002','create donation DOO2 ok','I donate D002','2024-12-15', 0,'Donate For B','Oganizarion BBC', '0222222222', '2023-06-19',2),
(3,'D003','create donation DOO3 ok','I donate D003','2024-10-10', 0,'Donate For C','Oganizarion CBC', '0333333333', '2023-07-25',2),
(4,'D004','create donation DOO4 ok','I donate D004','2023-12-05', 0,'Donate For D','Oganizarion BBC', '0444444444', '2023-05-27',2),
(5,'D005','create donation DOO5 ok','I donate D005','2025-12-09', 0,'Donate For E','Oganizarion EBC', '0555555555', '2023-05-20',1),
(6,'D006','create donation DOO6 ok','I donate D006','2023-09-25', 0,'Donate For F','Oganizarion FBC', '0666666666', '2023-04-20',1),
(7,'D007','create donation DOO7 ok','I donate D007','2023-10-01', 0,'Donate For E','Oganizarion EBC', '0777777777', '2023-06-25',1),
(8,'D008','create donation DOO8 ok','I donate D008','2024-05-30', 0,'Donate For H','Oganizarion HBC', '0888888888', '2023-06-20',1),
(9,'D009','create donation DOO9 ok','I donate D009','2023-07-25', 0,'Donate For I','Oganizarion IBC', '0999999999', '2023-05-01',1),
(10,'D010','create donation DO10 ok','I donate D010','2023-11-07', 0,'Donate For J','Oganizarion JBC', '0101010101', '2023-05-28',1);
-- SELECT * FROM spring_boot_asm01.user;
-- data for user_donation
insert into user_donation(id,created,money,name,date_donation,status,text,donation_id,user_id) values
(1, 'create donation DOO1 ok',10000000,'Donate For A','2023-05-30',1,'user 1 donate for A',1,2),
(2, 'create donation DOO1 ok',50000000,'Donate For A','2023-06-30',1,'user 2 donate for A',1,3),
(3, 'create donation DOO1 ok',15000000,'Donate For A','2023-07-30',1,'user 3 donate for A',1,4),
(4, 'create donation DOO2 ok',10000000,'Donate For B','2023-08-30',1,'user 4 donate for B',2,5),
(5, 'create donation DOO2 ok',20000000,'Donate For B','2023-09-30',1,'user 5 donate for B',2,6),
(6, 'create donation DOO2 ok',12000000,'Donate For B','2023-10-30',1,'user 1 donate for B',2,2),
(7, 'create donation DOO3 ok',11000000,'Donate For C','2023-05-26',1,'user 5 donate for C',3,6),
(8, 'create donation DOO4 ok',10000000,'Donate For D','2023-07-30',1,'user 2 donate for D',4,3),
(9, 'create donation DOO4 ok',10000000,'Donate For D','2023-05-30',1,'user 4 donate for D',4,5);

