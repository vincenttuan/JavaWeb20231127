
drop table if exists sales_item;
drop table if exists user;
drop table if exists product;

-- 建立 user table
create table if not exists user(
	id int primary key auto_increment,
    username varchar(50),
    password varchar(255),
    salt varchar(255),
    email varchar(50)
);

-- 建立 product table
create table if not exists product(
	id int primary key auto_increment,
    name varchar(50),
    image_name varchar(50),
    price int,
    stock int
);

-- 建立 sales_item table
create table if not exists sales_item(
	id int primary key auto_increment,
    product_id int,
    product_name varchar(50),
    total_price int,
    total_amount int,
    user_id int,
    foreign key (product_id) references product(id),
    foreign key (user_id) references user(id)
);

-- 新增 user 資料紀錄(利用 SecurityUtils.java 產生預設資料)
insert into user(username, password, salt, email) values('john', 'CvYZAzr78PYhyTxDs2CJ91hhauo3+KUITuoK9ewWQ90=', 'dkBTXgJMWWdcPcOwC2/icg==', 'john@gmail.com');
insert into user(username, password, salt, email) values('jack', 'f4GwKKP/uMoYNkS3zO38rdqTF8psWW+ITIUcy+R/LaY=', 'pIDHnorSH7B/VcPdSVmZyQ==', 'jack@gmail.com');
insert into user(username, password, salt, email) values('rose', 'LKaCzacdQIni+M+99r2EgqiD7TLiPCvacrm2dGhMh7U=', 'injLTEco9KdKXgfD7QwncQ==', 'rose@gmail.com');

-- 新增 product 資料紀錄
insert into product(name, image_name, price, stock) values("台灣啤酒", "beer", 50, 5);
insert into product(name, image_name, price, stock) values("可口可樂", "cola", 25, 10);
insert into product(name, image_name, price, stock) values("柚子茶", "crapefruit", 20, 7);
insert into product(name, image_name, price, stock) values("提神飲料", "energy", 30, 10);
insert into product(name, image_name, price, stock) values("檸檬茶", "lemon", 20, 10);
insert into product(name, image_name, price, stock) values("橘子汽水", "orange", 35, 10);
insert into product(name, image_name, price, stock) values("覆盆子茶", "raspberry", 45, 10);
insert into product(name, image_name, price, stock) values("氣泡水", "soda", 15, 10);

-- 新增 sales_item 資料紀錄
insert into sales_item(product_id, product_name, total_price, total_amount, user_id) values(1, "beer", 50, 1, 1);
insert into sales_item(product_id, product_name, total_price, total_amount, user_id) values(2, "cola", 50, 2, 3);
insert into sales_item(product_id, product_name, total_price, total_amount, user_id) values(8, "soda", 45, 3, 1);



