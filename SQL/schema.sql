drop schema if exists stocks;

create schema stocks;

use stocks;

create table users (
    username varchar(32) not null,
    password varchar(256) not null,

    primary key(username)
);