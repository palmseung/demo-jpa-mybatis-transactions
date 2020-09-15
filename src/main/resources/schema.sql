create table if not exists account (
    id bigint not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
);
create table if not exists member (
   id bigint not null auto_increment,
   username varchar(255),
   password varchar(255),
   primary key (id)
);