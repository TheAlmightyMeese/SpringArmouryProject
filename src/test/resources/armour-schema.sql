drop table if exists `armour` CASCADE;
create table
			`armour`
			(
			 id integer AUTO_INCREMENT,
			 ac integer not null,
			 name varchar(255),
			 stealth_dis_adv boolean not null,
			 value integer not null,
			 weight integer not null,
			 primary key (id)
			 );