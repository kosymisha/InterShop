insert into user (id, active, email, firstname, lastname, password, photo_url)
  values (4, 1, 'pie@pie.com', 'Pierre', 'Omidyar', 'pie', 'https://s3.amazonaws.com/intershop-is3/pie.jpg');
insert into user_role (user_id, roles) values (4, 'SELLER');

insert into shop (id, description, name_shop, photo_url, url, user_id)
  values (1, 'American multinational e-commerce corporation', 'eBay', 'https://s3.amazonaws.com/intershop-is3/ebay.jpg', 'https://www.ebay.com/', 4);