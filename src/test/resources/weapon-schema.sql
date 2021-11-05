drop table if exists `weapon` CASCADE;
create table
		`weapon`
(
	id integer AUTO_INCREMENT,
	damage varchar(255),
	gold_value integer not null,
	name varchar(255),
	type varchar(255),
	primary key (id)
);