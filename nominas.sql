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
	categoria VARCHAR(5) NOT NULL,
	grupo INT(4) NOT NULL
);

CREATE TABLE nomina(
	id INT(11) AUTO_INCREMENT,
	idtrabajador INT(11),
	rutapdf VARCHAR(200),
	rutaxml VARCHAR(200),
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
	diainicio INT(4) NOT NULL,
	diafinal INT(4) NOT NULL,
	contrato ENUM('TEMPORAL','INDEFINIDO') DEFAULT 'INDEFINIDO',
	mesanyo VARCHAR(7),
	horas INT(4) DEFAULT 8,
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
	PRIMARY KEY(idtrabajador, idplus),
	CONSTRAINT id_tra_plu_t FOREIGN KEY(idtrabajador)
	REFERENCES trabajador(id) ON UPDATE CASCADE,
	CONSTRAINT id_tra_plu_p FOREIGN KEY(idplus)
	REFERENCES plus(id) ON UPDATE CASCADE
);
