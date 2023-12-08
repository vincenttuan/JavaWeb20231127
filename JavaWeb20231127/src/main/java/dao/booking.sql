drop table if exists BookingRoom;
drop table if exists Room;

-- 建立 Room
create table if not exists Room(
	roomId int primary key,
    name varchar(50) not null unique
);

-- 建立 BookingRoom
create table if not exists BookingRoom(
	bookingId int auto_increment primary key,
    roomId int not null,
    name varchar(50) not null,
    date varchar(50) not null,
    createTime timestamp default current_timestamp,
    foreign key (roomId) references Room(roomId),
    CONSTRAINT unique_roomId_and_date UNIQUE(roomId, date)
);

-- 建立預設資料
insert into Room (roomId, name) values ('101', '101室');
insert into Room (roomId, name) values ('202', '202室');
insert into Room (roomId, name) values ('303', '303室');
insert into Room (roomId, name) values ('404', '404室');

insert into BookingRoom(roomId, name, date) values(101, 'John', '2023-12-08');
insert into BookingRoom(roomId, name, date) values(101, 'Mary', '2023-12-09');
insert into BookingRoom(roomId, name, date) values(202, 'Jack', '2023-12-10');


