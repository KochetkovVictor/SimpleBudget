DELETE FROM income;
DELETE FROM purse;
DELETE FROM receipt;
DELETE FROM shop;
DELETE FROM shopnet;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO shopnet (name, id) VALUES
  ('Пятерочка',201),
  ('Ашан',202),
  ('Виктория',203),
  ('Дикси',204);

INSERT INTO shop (name, adress, shopnetid)  VALUES
  ('Виктория Видное','МО, г.Видное, ул. Березовая, д.9',203),
  ('Ашан Белая Дача','140053 Московская область, г. Котельники, 1-й Покровский проезд, д.5, торговый центр "МЕГА"',202),
  ('Ашан Вегас','142715 Московская обл., Ленинский р-н, пос. Совхоз Имени Ленина 24 км. МКАД, владение 1',202),
  ('Виктория Домодедовская','г. Москва, Ореховый б-р, д. 14, стр. 3',203),
  ('Дикси Видное Березовая 6','МО, г.Видное, ул. Березовая, д.6',204),
  ('Пятерочка Видное Березовая 2','МО, г.Видное, ул. Березовая, д.2',201),
  ('Пятерочка Видное Березовая 7','МО, г.Видное, ул. Березовая, д.7',201);

INSERT INTO users (id, nickname, password, email, firstname, lastname, dateofbirth, register, enabled) VALUES
  (302, 'simpleuser', '$2a$10$QgguxfOdbst6z56QR1xHo.w144UVpv/pNSxajym7zODRlt4PpTeM6', 'che18@yandex.ru', 'Victor', 'Kochetkov', '1979-10-23', '2017-05-14', TRUE ),
  (301, 'adminuser', '$2a$10$2IGdPIH8QNn9.CXLb5FXC.eq3uAOHVvaPhavogusbGX8SfTBS4LQm', 'secretphiz@gmail.com', 'Arutyun', 'Shelenberg', '2010-10-10', '2017-05-14', TRUE);

INSERT INTO user_roles (user_id, role) VALUES
  (301, 'ROLE_ADMIN'),
  (302, 'ROLE_USER');

INSERT INTO purse (description,amount, active, user_id) VALUES
  ('Cash',134785.34,true, 301),
  ('Mastercard Alfa',34000.98,TRUE,301),
  ('Mastercard VTB-Moscow',0,TRUE,302),
  ('Pro100 Card SberBank',0,true,302);


