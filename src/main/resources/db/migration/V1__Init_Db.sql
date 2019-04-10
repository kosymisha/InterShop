create table category (
  id bigint not null,
  category_name varchar(40) UNIQUE ,
  primary key (id));

create table product (
  id bigint not null AUTO_INCREMENT,
  photo_url varchar(100),
  title varchar(80) not null,
  category_id bigint not null,
  primary key (id),
  foreign key (category_id) references category (id)) ;

create table bank_card (
  number_card VARCHAR(16) NOT NULL ,
  first_name_card VARCHAR(20) NOT NULL ,
  last_name_card VARCHAR(20) NOT NULL ,
  month INTEGER NOT NULL ,
  year INTEGER NOT NULL ,
  PRIMARY KEY (number_card)
);

create table user (
  id bigint not null AUTO_INCREMENT,
  active bit,
  email varchar(20) not null UNIQUE ,
  firstname varchar(20) not null,
  lastname varchar(20),
  password varchar(20) not null,
  photo_url varchar(100),
  card_number VARCHAR(16),
  primary key (id),
  FOREIGN KEY (card_number) REFERENCES bank_card (number_card)
) ;

create table user_role (
  user_id bigint not null,
  roles varchar(10),
  foreign key (user_id) references user (id)) ;

create table shop (
  id bigint not null AUTO_INCREMENT,
  description varchar(200),
  name_shop varchar(20) not null UNIQUE ,
  photo_url varchar(100),
  url varchar(100) not null UNIQUE ,
  user_id bigint not null,
  primary key (id),
  foreign key (user_id) references user (id)) ;

create table advert (
  id bigint not null AUTO_INCREMENT,
  store_id varchar(50),
  currency varchar(5) not null,
  price decimal(19,2) not null,
  product_url varchar(160) ,
  views integer not null,
  description varchar(200),
  available bit NOT NULL ,
  product_id bigint not null,
  shop_id bigint not null,
  primary key (id),
  foreign key (product_id) references product (id),
  foreign key (shop_id) references shop (id));

create table comment (
  id bigint not null AUTO_INCREMENT,
  date datetime not null,
  message varchar(200) not null,
  advert_id bigint,
  user_id bigint not null,
  shop_id bigint,
  primary key (id),
  foreign key (shop_id) references shop (id),
  foreign key (advert_id) references advert (id),
  foreign key (user_id) references user (id)
);

create table orders (
  id BIGINT not null AUTO_INCREMENT,
  date datetime not null,
  paid bit,
  user_id BIGINT not null,
  advert_id BIGINT not null,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (advert_id) REFERENCES advert (id)
);



#create table hibernate_sequence (next_val bigint);
#insert into hibernate_sequence values ( 1 );
#insert into hibernate_sequence values ( 1 );
#insert into hibernate_sequence values ( 1 );
#insert into hibernate_sequence values ( 1 );
#insert into hibernate_sequence values ( 1 );

#alter table advert add constraint FKfmmh5lffpjdfnrstc88gok352 foreign key (product_id) references product (id);
#alter table advert add constraint FKrh7sfynt8tifki9h7r6ghrwt4 foreign key (shop_id) references shop (id);
#alter table comment add constraint FKeesiagm3hwe8l0oosrb17cjyn foreign key (advert_id) references advert (id);
#alter table comment add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references user (id);
#alter table comment add constraint FKi254finscqq0av8vlprmtx2an foreign key (shop_id) references shop (id);
#alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id);
#alter table shop add constraint FKj97brjwss3mlgdt7t213tkchl foreign key (user_id) references user (id);
#alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);