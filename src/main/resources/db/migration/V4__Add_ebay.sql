insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (2, 1, 'pie@pie.com', 'Pierre', 'Omidyar', 'pie', '/img/pie.jpg');
insert into user_role (user_id, roles) values (2, 'SELLER');

insert into shop (id, description, name_shop, photo_url, url, user_id)
  values (1, 'American multinational e-commerce corporation', 'eBay', '/img/ebay.jpg', 'https://www.ebay.com/', 2);