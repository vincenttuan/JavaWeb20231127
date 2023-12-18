-- name sex salary age
-- John M 50000 30
drop table if exists employee;
create table if not exists employee(
	id int auto_increment primary key,
    name varchar(50) unique,
    sex varchar(2),
    salary int,
    age int,
    createtime timestamp default current_timestamp
);