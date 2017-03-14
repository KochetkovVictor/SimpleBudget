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

INSERT INTO purse (description,amount, active) VALUES
  ('Cash',0,true),
  ('Mastercard Alfa',0,TRUE ),
  ('Mastercard VTB-Moscow',0,TRUE ),
  ('Pro100 Card SberBank',0,true);
