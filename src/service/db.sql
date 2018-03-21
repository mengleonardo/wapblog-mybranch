create database if not exists blogdb;
use blogdb;

drop table if exists blog;
drop table if exists user;
create table user(
  id int not null auto_increment,
  email varchar(50),
  username varchar(50),
  pwd varchar(50),
  primary key(id)
);

create table blog(
  id int not null auto_increment,
  title varchar(200),
  content text,
  user_id int,
  create_time datetime default current_timestamp,
  primary key(id),
  foreign key(user_id) references user(id)
);


insert into user(id, email, username, pwd) values(1001, "cyang@mum.edu", "cyang", "123456");
insert into blog(id, title, content, user_id) values(2001, "This is my First Blog", "Hi there!", 1001);
