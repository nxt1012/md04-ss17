drop database if exists demo_thymeleaf;
create database demo_thymeleaf;
use demo_thymeleaf;

create table user
(
    id       int primary key auto_increment,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    phone int,
    role enum('ADMIN', 'USER')
);

insert into user (name, email, password, phone, role)
values ('Nguyễn Minh Triết', 'trietnm@gmail.com', '123456', 123456789, 'ADMIN'),
       ('Phạm Minh Chính', 'chinhpm@gmail.com', '123456', 'USER'),
       ('Nguyễn Phú Trọng', 'trongpt@gmail.com', '123456', 'USER'),
       ('Dương Đức Anh', 'anhdd@gmail.com', '123456', 'USER');
