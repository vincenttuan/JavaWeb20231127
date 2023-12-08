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
