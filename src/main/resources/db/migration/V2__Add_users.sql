insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (1, 1, 'bad@bad.com', 'Masha', 'Badun', 'bad', 'https://s3.amazonaws.com/intershop-is3/av_g.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (2, 1, 'isu@isu.com', 'Isultan', 'Absult', 'isu', 'https://s3.amazonaws.com/intershop-is3/isu.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (3, 1, 'kat@kat.com', 'Kate', 'Sesh', 'kat', 'https://s3.amazonaws.com/intershop-is3/sesh.jpg');
insert into user_role (user_id, roles) values (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'SELLER');
insert into user_role (user_id, roles) values (3, 'USER');

insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (1, '1111222211112222', 'masha', 'badun', 12, 21, 1, 1) ;
insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (2, '5555000011115555', 'isu', 'abs', 14, 21, 1, 2) ;
insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (3, '1111000011110000', 'kate', 'sesh', 12, 26, 0, 3) ;

insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (4, '1111000011111234', 'kate', 'sesh', 12, 24, 0, 3) ;
insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (5, '1111000011114321', 'kate', 'sesh', 12, 25, 1, 3) ;
insert into bank_card (id, number_card, first_name_card, last_name_card, month, year, active, user_id)
	              values (6, '1111000011117778', 'kate', 'sesh', 12, 26, 0, 3) ;
