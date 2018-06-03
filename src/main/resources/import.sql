/*Populate table clients*/
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Lucas','Constante','carlo@gmail.com','661315450','c/ Finestrelles 65','Spain','Barcelona','08033','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Moha','Omega','alfa@gmail.com','661912455','c/ Illa de Buda 55','Spain','Madrid','08027','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Grace','Toa','grac@gmail.com','63442455','c/ La Mina 25','Spain','San Sebastian','08906','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Cristian','Andrade','carlo@gmail.com','661315450','c/ Pau Claris 65','Spain','Victoria','08033','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Nerea','Cadena','alfa@gmail.com','661912455','c/ Just Oliveras','Spain','Madrid','08027','', NOW());
INSERT INTO clients (surname,lastname,email,phone,address,country,city,cp,photo,create_at) VALUES ('Noelia','Bassa','bassa@gmail.com','66543455','c/ Rua 25','Spain','Valencia','08906','', NOW());

INSERT INTO users (username,password,client_id)VALUES ('lucas',123,1);
INSERT INTO users (username,password,client_id)VALUES ('moha',123,2);
INSERT INTO users (username,password,client_id)VALUES ('admin',123,3);
INSERT INTO users (username,password,client_id)VALUES ('cris',123,4);
INSERT INTO users (username,password,client_id)VALUES ('nerea',123,5);
INSERT INTO users (username,password,client_id)VALUES ('noelia',123,6);


/*Populate table cars*/
INSERT INTO cars (model,matriculation,pneumatic,type_motor,displacement,client_id) VALUES('audi','8810KKN','Sl9','clasic','Ab67',1);



/* Populate tabla products */
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

/* Populate tabla Mechanic */
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Alex','Vinicio','alex@gmail.com','697543271','Passeig Manuel Girona 9','Spain','Barcelona','08034',41.3902045,2.121148,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Adrian','Donaire','adrian@gmail.com','612254791','Rambla de Volart 52','Spain','Tarragona','08034',40.5425712,-3.6391764999999623,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Rafael','Donosti','rafael@gmail.com','633237321','Carrer dels Jocs Florals 71','Spain','Leida','08043',41.4167147,2.1772022000000106,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Marc','Cisneros','marc@gmail.com','602399808','Passeig de Sant Joan 140','Spain','Mataro','08034',40.4089769,-3.7145907999999963,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Jordi','Gallardo','jordi@gmail.com','675173534','Carrer de Sèneca 7','Spain','Cornella','08034',37.415118,-5.950617999999963,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Stalin','Maldonado','stalin@gmail.com','931435579','Calle Maladeta 7','Spain','Hospitalet','08034',41.5678408,2.088619300000005,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Roberto','Perez','robert@gmail.com','658608365','Carrer de Corró 183','Spain','Granollers','08034',41.6141006,2.2897620999999617,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Marcos','Zamaro','marcos@gmail.com','677279405','Carretera de Barcelona 36','Spain','San Cugat','08034',41.31843,2.024189999999976,NOW());
INSERT INTO mechanics (surname,lastname,email,phone,address,country,city,cp,latitude,longitude,create_at) VALUES('Marcelo','Wilkinson','marcelo@gmail.com','647563436','Calle de Gaztambide 55','Spain','Barcelona','08034',40.3407404,-3.7134062000000085,NOW());


/*plan*/
INSERT INTO plans (name,porcentaje) VALUES('6 months',0.15);
INSERT INTO plans (name,porcentaje) VALUES('1 year',0.25);
INSERT INTO plans (name,porcentaje) VALUES('2 years',0.50);