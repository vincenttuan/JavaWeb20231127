
drop table if exists sales_item;
drop table if exists user;
drop table if exists product;

-- 建立 user table
create table if not exists user(
	id int primary key,
    username varchar(50),
    password varchar(50),
    email varchar(50)
);

-- 建立 product table
create table if not exists product(
	id int primary key,
    name varchar(50),
    image_name varchar(50),
    price int,
    stock int
);

-- 建立 sales_item table
create table if not exists sales_item(
	id int primary key,
    product_id int,
    product_name varchar(50),
    total_price int,
    total_amount int,
    user_id int,
    foreign key (product_id) references product(id),
    foreign key (user_id) references user(id)
);