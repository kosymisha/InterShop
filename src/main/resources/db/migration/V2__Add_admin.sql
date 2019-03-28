insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (1, 1, 'bad@bad.com', 'Masha', 'Badun', 'bad', '/img/7313c80e-3144-4ff9-ba27-e94610a7f485.av_g.jpg');
insert into user_role (user_id, roles) values (1, 'ADMIN');