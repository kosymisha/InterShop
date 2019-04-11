insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (1, 1, 'bad@bad.com', 'Masha', 'Badun', 'bad', 'https://s3.amazonaws.com/intershop-is3/av_g.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (2, 1, 'isu@isu.com', 'Isultan', 'Absult', 'isu', 'https://s3.amazonaws.com/intershop-is3/isu.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (3, 1, 'kat@kat.com', 'Kate', 'Sesh', 'kat', 'https://s3.amazonaws.com/intershop-is3/sesh.jpg');
insert into user_role (user_id, roles) values (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'SELLER');
insert into user_role (user_id, roles) values (3, 'USER');

insert into bank_card (number_card, first_name_card, last_name_card, month, year)
	values ('1111000011110000', 'kate', 'sesh', 12, 21) ;
update user set card_number = '1111000011110000' where id = 3;