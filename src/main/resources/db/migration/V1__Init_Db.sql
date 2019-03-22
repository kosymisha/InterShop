create table announcement (
  id bigint not null,
  currency varchar(5) not null,
  price double precision not null,
  product_url varchar(100) UNIQUE ,
  views integer not null,
  product_id bigint not null,
  shop_id bigint not null,
  primary key (id)) engine=MyISAM;

create table category (
  id bigint not null,
  category_name varchar(20) UNIQUE ,
  primary key (id)) engine=MyISAM;

create table comment (
  id bigint not null,
  date datetime not null,
  message varchar(50) not null,
  announcement_id bigint,
  user_id bigint not null,
  shop_id bigint,
  primary key (id)) engine=MyISAM;

create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table product (
  id bigint not null,
  description varchar(200),
  photo_url varchar(100),
  title varchar(50) not null,
  category_id bigint not null,
  primary key (id)) engine=MyISAM;

create table shop (
  id bigint not null,
  description varchar(200),
  name_shop varchar(20) not null UNIQUE ,
  photo_url varchar(100),
  url varchar(100) not null UNIQUE ,
  user_id bigint not null,
  primary key (id)) engine=MyISAM;

create table user (
  id bigint not null,
  active bit,
  email varchar(20) not null UNIQUE ,
  firstname varchar(20) not null,
  lastname varchar(20),
  password varchar(20) not null,
  photo_url varchar(100),
  primary key (id)) engine=MyISAM;

create table user_role (
  user_id bigint not null,
  roles varchar(10)) engine=MyISAM;

alter table announcement add constraint FKfmmh5lffpjdfnrstc88gok352 foreign key (product_id) references product (id);
alter table announcement add constraint FKrh7sfynt8tifki9h7r6ghrwt4 foreign key (shop_id) references shop (id);
alter table comment add constraint FKeesiagm3hwe8l0oosrb17cjyn foreign key (announcement_id) references announcement (id);
alter table comment add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references user (id);
alter table comment add constraint FKi254finscqq0av8vlprmtx2an foreign key (shop_id) references shop (id);
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id);
alter table shop add constraint FKj97brjwss3mlgdt7t213tkchl foreign key (user_id) references user (id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);