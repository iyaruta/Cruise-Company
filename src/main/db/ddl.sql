create table Cruise
(
	id bigint auto_increment
		primary key,
	ship_id bigint not null,
	name varchar(50) not null,
	deleted tinyint(1) default '0' not null
)
;

create index cruise_to_ship_fk
	on Cruise (ship_id)
;

create table Excursion
(
	id bigint auto_increment
		primary key,
	port_id bigint not null,
	name varchar(50) not null,
	details varchar(250) null,
	price decimal not null
)
;

create index Excursion_Port_id_fk
	on Excursion (port_id)
;

create table Excursion_to_user
(
	excursion_id bigint not null,
	user_id bigint not null,
	constraint Excursion_to_user_Excursion_id_fk
		foreign key (excursion_id) references Excursion (id)
)
;

create index Excursion_to_user_Excursion_id_fk
	on Excursion_to_user (excursion_id)
;

create index Excursion_to_user_User_id_fk
	on Excursion_to_user (user_id)
;

create table Port
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null
)
;

alter table Excursion
	add constraint Excursion_Port_id_fk
		foreign key (port_id) references Port (id)
;

create table Ship
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null,
	passengers int default '0' not null,
	crew int default '0' not null
)
;

alter table Cruise
	add constraint cruise_to_ship_fk
		foreign key (ship_id) references Ship (id)
;

create table Ticket
(
	id bigint auto_increment
		primary key,
	cruise_id bigint not null,
	ticket_class_id bigint not null,
	price decimal null,
	constraint Ticket_Cruise_id_fk
		foreign key (cruise_id) references Cruise (id)
)
;

create index Ticket_Cruise_id_fk
	on Ticket (cruise_id)
;

create index Ticket_Ticket_Class_id_fk
	on Ticket (ticket_class_id)
;

create table Ticket_Class
(
	id bigint auto_increment
		primary key,
	ship_id bigint not null,
	type varchar(50) not null,
	count bigint not null,
	bonus varchar(250) null,
	constraint Ticket_Class_Ship_id_fk
		foreign key (ship_id) references Ship (id)
)
;

create index Ticket_Class_Ship_id_fk
	on Ticket_Class (ship_id)
;

alter table Ticket
	add constraint Ticket_Ticket_Class_id_fk
		foreign key (ticket_class_id) references Ticket_Class (id)
;

create table User
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null,
	role int not null,
	password varchar(50) not null,
	constraint User_name_uindex
		unique (name)
)
;

alter table Excursion_to_user
	add constraint Excursion_to_user_User_id_fk
		foreign key (user_id) references User (id)
;

create table User_to_ticket
(
	user_id bigint not null,
	ticket_id bigint not null,
	constraint User_to_ticket_User_id_fk
		foreign key (user_id) references User (id),
	constraint User_to_ticket_Ticket_id_fk
		foreign key (ticket_id) references Ticket (id)
)
;

create index User_to_ticket_Ticket_id_fk
	on User_to_ticket (ticket_id)
;

create index User_to_ticket_User_id_fk
	on User_to_ticket (user_id)
;

create table Waypoint
(
	id bigint auto_increment
		primary key,
	cruise_id bigint not null,
	port_id bigint not null,
	arrival timestamp null,
	departure timestamp null,
	constraint Waypoint_Cruise_id_fk
		foreign key (cruise_id) references Cruise (id),
	constraint Waypoint_Port_id_fk
		foreign key (port_id) references Port (id)
)
;

create index Waypoint_Cruise_id_fk
	on Waypoint (cruise_id)
;

create index Waypoint_Port_id_fk
	on Waypoint (port_id)
;