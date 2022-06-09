DROP DATABASE IF EXISTS nominas;
CREATE DATABASE nominas
CHARACTER SET utf8
COLLATE utf8_spanish_ci;

USE nominas;

CREATE TABLE trabajador(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nomtra VARCHAR(40) NOT NULL,
	niftra VARCHAR(9) NOT NULL UNIQUE,
	nafsstra VARCHAR(12) NOT NULL UNIQUE
);

CREATE TABLE empresa(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nomemp VARCHAR(50) NOT NULL,
	cifemp VARCHAR(9) NOT NULL UNIQUE,
	diremp VARCHAR(100),
	cccemp VARCHAR(10000) NOT NULL UNIQUE
);

CREATE TABLE trabaja(
	codtra INT(11),
	codemp INT(11),
	convenio VARCHAR(100),
	grupocot VARCHAR(50) NOT NULL,
	catprof VARCHAR(100) NOT NULL,
	tipocontrato ENUM('TEMPORAL','INDEFINIDO') DEFAULT 'INDEFINIDO',
	fecintra DATE NOT NULL,
	fecsaltra DATE,
	PRIMARY KEY(codtra,codemp),
	CONSTRAINT fk_tra_traemp FOREIGN KEY(codtra)
	REFERENCES trabajador(id) ON UPDATE CASCADE,
	CONSTRAINT fk_emp_traemp FOREIGN KEY(codemp)
	REFERENCES empresa(id) ON UPDATE CASCADE
);	

CREATE TABLE nomina(
	id INT(11) AUTO_INCREMENT,
	codtra INT(11),
	salbase DECIMAL(10,2) NOT NULL,
	prorrata DECIMAL(10,2),
	ayudaesp DECIMAL(10,2),
	plustrans DECIMAL(10,2),
	plustele DECIMAL(10,2),
	dietamed DECIMAL(10,2),
	dietacomp DECIMAL(10,2),
	totaldev DECIMAL(10,2),
	contcom DECIMAL(10,2),
	desempleo DECIMAL(10,2),
	fp DECIMAL(10,2),
	horasextrafm DECIMAL(10,2),
	otrashoras DECIMAL(10,2),
	totalapor DECIMAL(10,2),
	irpf DECIMAL(10,2),
	anticipo DECIMAL(10,2),
	valorespecie DECIMAL(10,2),
	otrasded DECIMAL(10,2),
	totalded DECIMAL(10,2),
	neto DECIMAL(10,2),
	bccc DECIMAL(10,2),
	bccp DECIMAL(10,2),
	bhe DECIMAL(10,2),
	bhefm DECIMAL(10,2),
	PRIMARY KEY(id,codtra),
	CONSTRAINT fk_tra_nom FOREIGN KEY(codtra)
	REFERENCES trabajador(id) ON UPDATE CASCADE
);

CREATE TABLE horas(
	idtra INT(11),
	codemp INT(11),
	mesanyo VARCHAR(7),
	n_horasex INT(3),
	n_horastot INT(11),
	PRIMARY KEY(idtra,codemp,mesanyo),
	CONSTRAINT fk_tra_hor FOREIGN KEY(idtra)
	REFERENCES trabajador(id) ON UPDATE CASCADE,
	CONSTRAINT fk_emp_hor FOREIGN KEY(codemp)
	REFERENCES empresa(id) ON UPDATE CASCADE
);

CREATE TABLE pluses(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50)
);

CREATE TABLE recibe(
	id INT(11),
	codtra INT(11),
	mesanyo VARCHAR(7),
	valor Decimal(10,2),
	PRIMARY KEY(id,codtra,mesanyo),
	CONSTRAINT fk_pl_re FOREIGN KEY(id)
	REFERENCES pluses(id) ON UPDATE CASCADE,
	CONSTRAINT fk_tra_re FOREIGN KEY(codtra)
	REFERENCES trabajador(id) ON UPDATE CASCADE
);



INSERT INTO trabajador(nomtra,niftra,nafsstra)
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



INSERT INTO empresa(nomemp,cifemp,diremp,cccemp)
VALUES ('CocaCola','H85423645','Calle Severo Ochoa','01521486315'),
('Iberdrola','G52348756','Calle Santa Barbara','52315780145'),
('Metales bacanos','M32184623','Calle Azorin','64214895341'),
('Colacao','H24568713','Calle Ramon y Cajal','84389745253'),
('Microsoft','M74825698','Calle del Valle','45812687452'),
('Chapas bonitas','J78541268','Calle Falsa','87309283409'),
('Tarradellas','P83765412','Calle Espetec','52148963574'),
('Heavy Metal','G74582359','Calle Abeja','74698214598'),
('Asero pesado','E98734021','Calle Hacendado','47201698031'),
('Brugal','H93402345','Calle Concola','12874596325');

INSERT INTO trabaja(codtra,codemp,convenio,grupocot,catprof,tipocontrato,fecintra,fecsaltra)
VALUES ('1','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','2','Titulado Medio','INDEFINIDO','1999-02-08',NULL),
('2','6','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','1','Analista de sistemas','TEMPORAL','2006-12-22',NULL),
('3','4','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','4','Tecnico Analista','TEMPORAL','2003-05-03','2017-02-04'),
('4','9','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','3','Jefe de primera Administrativo','TEMPORAL','1996-09-08',NULL),
('5','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','3','Encargado Laboratorio','INDEFINIDO','2001-06-10',NULL),
('6','8','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','2','Diplomado Universitario','TEMPORAL','2005-11-01','2012-09-24'),
('7','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','1','Titulado Superior','INDEFINIDO','2000-12-05',NULL),
('8','3','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','4','Maestro de Taller','INDEFINIDO','2004-07-25','2019-08-22'),
('9','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','5','Oficial de Primera','TEMPORAL','2009-10-07',NULL),
('10','3','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','2','ATS','TEMPORAL','2012-04-04',NULL),
('11','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','3','Delineante de Primera','INDEFINIDO','2002-08-10','2022-04-11'),
('12','6','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','5','Tecnico de organizacion de primera','INDEFINIDO','1999-10-08',NULL),
('13','1','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','6','Operador de maquinas basica','TEMPORAL','2008-04-11',NULL),
('14','9','CONVENIO COLECTIVO PARA LA INDUSTRIA DEL METAL','7','Vigilante','TEMPORAL','2018-08-09',NULL);

INSERT INTO horas(idtra,codemp,mesanyo,n_horasex,n_horastot)
VALUES ('1','1','07-2012','15','150'),
('2','6','10-2008','0','100'),
('3','4','09-2015','50','150'),
('4','9','01-2002','8','150'),
('5','1','05-2022','0','80'),
('6','8','02-2010','23','150'),
('7','1','06-2022','0','80'),
('8','3','03-2018','10','150'),
('9','1','06-2020','5','150'),
('10','3','04-2022','0','80'),
('11','1','12-2021','40','150'),
('12','6','12-2000','20','130'),
('13','1','11-2021','0','150'),
('14','9','10-2020','25','80');

INSERT INTO pluses(nombre)
VALUES('Ayuda Especial'),
('Plus de transporte'),
('Plus de teletrabajo'),
('Dieta media'),
('Diesta completa');

INSERT INTO recibe(id,codtra,mesanyo,valor)
VALUES('1','1','07-2012','1'),
('2','1','07-2012','20'),
('5','1','07-2012','20'),
('2','2','10-2008','12'),
('3','3','09-2015','20'),
('2','6','02-2010','20'),
('5','6','02-2012','10');