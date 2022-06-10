DROP DATABASE IF EXISTS nominas;
CREATE DATABASE nominas
CHARACTER SET utf8
COLLATE utf8_spanish_ci;

USE nominas;

CREATE TABLE trabajador(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(40) NOT NULL,
	nif VARCHAR(9) NOT NULL UNIQUE,
	nafss VARCHAR(12) NOT NULL UNIQUE
);

CREATE TABLE puesto(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	convenio VARCHAR(200) NOT NULL,
	grupo INT(4) NOT NULL,
	categoria VARCHAR(5) NOT NULL
);

CREATE TABLE nomina(
	id INT(11) AUTO_INCREMENT,
	idtrabajador INT(11),
	rutapdf VARCHAR(200),
	mesanyo VARCHAR(7) NOT NULL,
	PRIMARY KEY(id, idtrabajador),
	CONSTRAINT fk_tra_nom FOREIGN KEY(idtrabajador)
	REFERENCES trabajador(id) ON UPDATE CASCADE
);

CREATE TABLE plus(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL
);

CREATE TABLE trabaja(
	idtrabajador INT(11),
	idpuesto INT(11),
	contrato ENUM('TEMPORAL','INDEFINIDO') DEFAULT 'INDEFINIDO',
	mesanyo VARCHAR(7),
	horastotal INT(4) DEFAULT 150,
	horasextra INT(4) DEFAULT 0,
	irpf INT(4) DEFAULT 12,
	prorrata TINYINT(1) DEFAULT 1,
	PRIMARY KEY(idtrabajador, idpuesto, mesanyo),
	CONSTRAINT fk_tra_pue_t FOREIGN KEY(idtrabajador)
	REFERENCES trabajador(id) ON UPDATE CASCADE,
	CONSTRAINT fk_tra_pue_p FOREIGN KEY(idpuesto)
	REFERENCES puesto(id) ON UPDATE CASCADE
);

CREATE TABLE recibe(
	idtrabajador INT(11),
	idplus INT(11),
	valor DECIMAL(10,2) NOT NULL,
	mesanyo VARCHAR(7) NOT NULL,
	PRIMARY KEY(idtrabajador, idplus),
	CONSTRAINT id_tra_plu_t FOREIGN KEY(idtrabajador)
	REFERENCES trabajador(id) ON UPDATE CASCADE,
	CONSTRAINT id_tra_plu_p FOREIGN KEY(idplus)
	REFERENCES plus(id) ON UPDATE CASCADE
);

INSERT INTO trabajador(nombre,nif,nafss)
VALUES ('Felipe Perez Garcia','89652418M','569842314578'),
('Alejandro Ubeda Miralles','54216874B','754236548748'),
('Angel Riquelme Armando','65247856L','584231789655'),
('Maria Garcia Hernandez','78056945L','963215975464'),
('Elena Pascual Ramon','95378435D','842679134974'),
('Pedro Benito Gil','88560248H','002549678548'),
('Paula Gomez Garcia','77804562L','234512567213'),
('Javier Berenguer Manzanera','60315950M','080023547895'),
('Jennifer Leal Blanc','54655802A','220354786542'),
('Ferran Villa Capdevila','96542302N','584200876421'),
('Marina Torres Gil','69366501L','550047863566'),
('Gervasio Ubeda Guil','52687412K','854268741252'),
('Evaristo Garrote Fino','54345871G','658741259873'),
('Juan Carlos Borbon','75982564R','794613258465');

INSERT INTO puesto(convenio, grupo, categoria)
VALUES ('Oficinas y despachos','1','3'),
('Oficinas y despachos','4','2a'),
('Oficinas y despachos','2','1'),
('Oficinas y despachos','6','2'),
('Oficinas y despachos','3','1b'),
('Oficinas y despachos','5','3');

INSERT INTO plus(nombre)
VALUES ('Ayuda especial'),
('Plus de transporte'),
('Plus de teletrabajo'),
('Dieta media'),
('Dieta completa');

INSERT INTO trabaja(idtrabajador, idpuesto, mesanyo)
VALUES ('1','3','01-2023'),
('2','1','01-2023'),
('3','3','01-2023'),
('4','6','01-2023'),
('5','4','01-2023'),
('6','4','01-2023'),
('7','5','01-2023'),
('8','6','01-2023'),
('9','1','01-2023'),
('10','1','01-2023'),
('11','2','01-2023'),
('12','3','01-2023'),
('13','3','01-2023'),
('14','5','01-2023');

INSERT INTO recibe(idtrabajador, idplus, valor, mesanyo)
VALUES ('2','1','1','01-2023'),
('6','2','30','01-2023'),
('8','3','30','01-2023'),
('11','4','30','01-2023'),
('13','5','30','01-2023');
