drop table if exists actor_ref_service;
drop table if exists user;
drop table if exists service;
drop table if exists actor;

-- 建立 Actor
create table if not exists actor(
	a_id int primary key,
    actorname varchar(50)
);

-- 建立 user table
create table if not exists user(
	u_id int primary key,
    username varchar(50),
    password varchar(50),
    a_id int,
    foreign key (a_id) references actor(a_id)
);

-- 建立 Service table
create table if not exists service(
	s_id int primary key,
    servicename varchar(50),
    serviceurl varchar(50)
);

-- 建立 Service 與 Actor 之間的關聯表 table
create table if not exists actor_ref_service(
	a_id int not null,
    s_id int not null,
    foreign key (a_id) references actor(a_id),
    foreign key (s_id) references service(s_id),
    CONSTRAINT unique_sid_and_aid UNIQUE(a_id, s_id)
);

-- 初始資料
insert into actor(a_id, actorname) values(1, "outer");
insert into actor(a_id, actorname) values(2, "inner");
insert into user(u_id, username, password, a_id) values(1, 'John', '1234', 1);
insert into user(u_id, username, password, a_id) values(2, 'Mary', '5678', 2);
insert into user(u_id, username, password, a_id) values(3, 'Jack', '1111', 1);
insert into user(u_id, username, password, a_id) values(4, 'Rose', '2222', 1);
insert into service(s_id, servicename, serviceurl) values(1, '服務資訊', '/service/info');
insert into service(s_id, servicename, serviceurl) values(2, '會計帳務', '/service/acounting');
insert into service(s_id, servicename, serviceurl) values(3, '密碼修改', '/service/password');
insert into service(s_id, servicename, serviceurl) values(4, '會員資料', '/service/profile');
insert into actor_ref_service(a_id, s_id) values(1, 1);
insert into actor_ref_service(a_id, s_id) values(1, 3);
insert into actor_ref_service(a_id, s_id) values(1, 4);
insert into actor_ref_service(a_id, s_id) values(2, 2);
insert into actor_ref_service(a_id, s_id) values(2, 3);

-- john 有哪些服務可以使用
select u.username, a.actorname, s.s_id, s.servicename, s.serviceurl
from user u
left join actor a on u.a_id = a.a_id
left join actor_ref_service r on u.a_id = r.a_id
left join service s on r.s_id = s.s_id
where u.username = 'John'