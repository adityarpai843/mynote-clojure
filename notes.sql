show databases;

create database notes;

use notes;

create table note (id int not null AUTO_INCREMENT primary key,text varchar(500) not null);

desc note;

select * from note;

drop table note;

delete from note; 