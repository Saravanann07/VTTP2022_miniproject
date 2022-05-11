drop schema if exists stocks;

create schema stocks;

use stocks;

create table users (
	user_id INT NOT NULL AUTO_INCREMENT,
    username varchar(32) not null,
    password varchar(256) not null,

    primary key(user_id)
);