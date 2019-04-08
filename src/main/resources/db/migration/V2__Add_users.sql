insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (1, 1, 'bad@bad.com', 'Masha', 'Badun', 'bad', '/img/7313c80e-3144-4ff9-ba27-e94610a7f485.av_g.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (2, 1, 'isu@isu.com', 'Isultan', 'Absult', 'isu', '/img/889dfe31-31ff-4d12-8bd1-6fc0c86a3c26.isu.jpg');
insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (3, 1, 'kat@kat.com', 'Kate', 'Sesh', 'kat', '/img/254a7668-a808-4ce6-97f5-fc75c7062494.sesh.jpg');
insert into user_role (user_id, roles) values (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'SELLER');
insert into user_role (user_id, roles) values (3, 'USER');