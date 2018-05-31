INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Lucas','Constante','carlo@gmail.com','661315450','c/ Finestrelles 65','Spain','Barcelona','08033','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Grace','Omega','alfa@gmail.com','661912455','c/ Illa de Buda 55','Spain','Madrid','08027','', NOW());


INSERT INTO users (username,password,client_id)VALUES ('lucas',123,1);
INSERT INTO users (username,password,client_id)VALUES ('grace',123,2);

INSERT INTO cars (model,matriculation,pneumatic,type_motor,displacement,client_id) VALUES('audi','8810KKN','Sl9','clasic','Ab67',1);



/* Populate tabla productos */
INSERT INTO products (name, description, price) VALUES('Direction','Revisión de sistemas y mecanismos',100);
INSERT INTO products (name, description, price) VALUES('Brakes','Cambio de Pastillas y Discos',150);
INSERT INTO products (name, description, price) VALUES('Suspension', 'Revisión y cambio de Amortiguadores',120);
INSERT INTO products (name, description, price) VALUES('Pneumatic','Revisión y Cambio de Neumaticos', 80);
INSERT INTO products (name, description, price) VALUES('Lights','Revisión y Cambio de luces',90);
INSERT INTO products (name, description, price) VALUES('Batery','Revisión, Carga, Cambio de Bateria',70);
INSERT INTO products (name, description ,price) VALUES('Levels and Filters','Revisión y cambio de Niveles y Filtros',110);
INSERT INTO products (name, description ,price) VALUES('Air Conditioner','Revisión del Aire Acondicionado',40);
INSERT INTO products (name, description ,price) VALUES('Moons and CrystalCleaner','Revisión y Cambio de Limpia Cristales',70);
INSERT INTO products (name, description ,price) VALUES('Injection','Revisíon y Cambio de Inyectores',120);


/*Mechanic*/
INSERT INTO mechanics (surname, lastname,email,phone,address,country,city,cp,ubication) VALUES('Pablito','Pinche','pinche@gmail.com','63687728','C/ Teide 62','Spain','Barcelona','08906','');

/*plan*/
INSERT INTO plans (name,porcentaje) VALUES('6 months',0.15);
INSERT INTO plans (name,porcentaje) VALUES('1 year',0.25);
INSERT INTO plans (name,porcentaje) VALUES('2 years',0.50);