CREATE DATABASE car CHARACTER SET utf8 COLLATE utf8_general_ci; 

USE Car;

CREATE TABLE Brand (
brand_id INT NOT NULL AUTO_INCREMENT,
brand VARCHAR(255),
PRIMARY KEY ( brand_id)
);

CREATE TABLE Model (
model_id INT NOT NULL AUTO_INCREMENT,
brand_id INT NOT NULL,
model VARCHAR(255),
PRIMARY KEY (model_id),
FOREIGN KEY ( brand_id ) REFERENCES Brand ( brand_id ) ON DELETE CASCADE
);

CREATE TABLE Languages (
language_id INT NOT NULL AUTO_INCREMENT, 
locale VARCHAR(255),
PRIMARY KEY (language_id)
);

CREATE TABLE Transmission (
transmission_id INT NOT NULL AUTO_INCREMENT,
language_id INT NOT NULL,
transmission VARCHAR(255), 
PRIMARY KEY (transmission_id,language_id),
FOREIGN KEY (language_id) REFERENCES Languages (language_id) ON DELETE CASCADE
);

CREATE TABLE Category (
category_id INT NOT NULL AUTO_INCREMENT,
language_id INT NOT NULL,
category VARCHAR(255),
PRIMARY KEY (category_id, language_id),
FOREIGN KEY (language_id) REFERENCES Languages (language_id) ON DELETE CASCADE
);

CREATE TABLE State (
state_id INT NOT NULL AUTO_INCREMENT,
language_id INT NOT NULL,
state VARCHAR(255),
PRIMARY KEY (state_id, language_id),
FOREIGN KEY (language_id) REFERENCES Languages (language_id) ON DELETE CASCADE
);

CREATE TABLE User (
user_id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(255),
password VARCHAR(255),
first_name VARCHAR(255),
second_name VARCHAR(255),
phone_number VARCHAR(15),
role ENUM ('Guest', 'User', 'Admin') NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE Car ( 
car_id INT NOT NULL AUTO_INCREMENT,
model_id INT NOT NULL,
transmission_id INT NOT NULL,
category_id INT NOT NULL,
state_id INT NOT NULL,
year_of_produce DATE, 
date_of_creation DATE, 
engine_volume DECIMAL(3,1),
language_id INT NOT NULL,
photo BLOB,
user_id INT NOT NULL,
PRIMARY KEY (car_id),
FOREIGN KEY (model_id) REFERENCES Model (model_id) ON DELETE CASCADE,
FOREIGN KEY (transmission_id) REFERENCES Transmission (transmission_id) ON DELETE CASCADE,
FOREIGN KEY (category_id ) REFERENCES Category (category_id) ON DELETE CASCADE,
FOREIGN KEY (state_id ) REFERENCES State (state_id) ON DELETE CASCADE,
FOREIGN KEY (language_id) REFERENCES Languages (language_id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES User (user_id)  ON DELETE CASCADE
);

INSERT INTO Languages (locale) VALUES ('ru');
INSERT INTO Languages (locale) VALUES ('en');

INSERT INTO State(language_id, state) VALUES (1,'новая'),(1, 'б/у'), (1, 'аварийная');
INSERT INTO State(language_id, state) VALUES (2, 'new'), (2, 'used'), (2, 'emergency');

INSERT INTO Transmission (language_id, transmission) VALUES ('1', 'автомат'), ('1', 'механика'), ('1', 'робот');
INSERT INTO Transmission (language_id, transmission) VALUES ('2', 'automat'), ('2', 'mechanic'), ('2', 'robot');


INSERT INTO Category (language_id, category) VALUES (1, 'легковые'), (1, 'грузовые'), (1, 'джипы');
INSERT INTO Category (language_id, category) VALUES (2, 'sedan'), (2, 'cargo'), (2, 'jeep');

INSERT INTO Brand (brand) VALUES ('BMW'), ('Mercedes'), ('Toyota');

INSERT INTO Model (brand_id, model) VALUES (1, 'm3'), (1, 'x5'),  (2, 'E220'), (2, 'G55'), (2, 'Aktros'), (2, 'MAN'), (3, 'Camry'), (3, 'Land Cruiser'), (3, 'Dyna');

INSERT INTO User (username, password, first_name, second_name, phone_number, role) VALUES ('Admin', '21232f297a57a5a743894a0e4a801fc3', 'Yerkebulan', 'Zhaxylykov', '+77777777777', 'Admin');
INSERT INTO User (username, password, first_name, second_name, phone_number, role) VALUES ('Thanos', '21232f297a57a5a743894a0e4a801fc3', 'Безумный', 'Титан', '+71111111111', 'User');
INSERT INTO User (username, password, first_name, second_name, phone_number, role) VALUES ('Iron Man', '21232f297a57a5a743894a0e4a801fc3', 'Железный', 'Человек', '+70000000000', 'User');
INSERT INTO User (username, password, first_name, second_name, phone_number, role) VALUES ('Halk', '21232f297a57a5a743894a0e4a801fc3', 'Брюс', 'Беннер', '+73333333333', 'User');



INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (1, 1, 1, 1, '2018-08-08', '2018-12-12', '2.0', 1, 2);  
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (2, 3, 3, 2, '2017-07-07', '2019-01-01', '4.4', 1, 2); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id)	VALUES (3, 2, 1, 3, '2000-01-01', '2018-12-12', '2.2', 1, 3); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (4, 4, 6, 4, '2019-01-01', '2019-03-03', '5.5', 2, 4); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (5, 5, 5, 5, '2006-08-08', '2019-02-02', '4.0', 2, 3); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (6, 2, 3, 2, '2000-08-08', '2018-12-12', '3.5', 1, 2); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (7, 1, 1, 2, '2015-05-05', '2018-12-12', '2.5', 1, 3); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (8, 1, 3, 2, '2016-04-04', '2019-02-02', '4.0', 1, 4); 
INSERT INTO Car (model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, user_id) VALUES (9, 6, 2, 2, '2017-07-07', '2018-07-07', '6.0', 1, 2);  

















