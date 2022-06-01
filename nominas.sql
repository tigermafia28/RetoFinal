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
	CodEmp INT(11) AUTO_INCREMENT PRIMARY KEY,
	NomEmp VARCHAR(50) NOT NULL,
	CifEmp VARCHAR(9) NOT NULL UNIQUE,
	DirEmp VARCHAR(100),
	CCCEmp VARCHAR(10000) NOT NULL UNIQUE
);

CREATE TABLE trabaja(
	CodTra INT(11),
	CodEmp INT(11),
	Convenio VARCHAR(100),
	GrupoCot VARCHAR(50) NOT NULL,
	CatProf VARCHAR(100) NOT NULL,
	TipoContrato ENUM('TEMPORAL','INDEFINIDO') DEFAULT 'INDEFINIDO',
	FecInTra DATE NOT NULL,
	FecSalTra DATE,
	PRIMARY KEY(CodTra,CodEmp),
	CONSTRAINT fk_tra_traemp FOREIGN KEY(CodTra)
	REFERENCES trabajador(CodTra) ON UPDATE CASCADE,
	CONSTRAINT fk_emp_traemp FOREIGN KEY(CodEmp)
	REFERENCES empresa(CodEmp) ON UPDATE CASCADE
);	

CREATE TABLE nomina(
	CodNom INT(11) AUTO_INCREMENT,
	CodTra INT(11),
	SalBase DECIMAL(10,2) NOT NULL,
	Prorrata DECIMAL(10,2),
	AyudaEsp DECIMAL(10,2),
	PlusTrans DECIMAL(10,2),
	PlusTele DECIMAL(10,2),
	DietaMed DECIMAL(10,2),
	DietaComp DECIMAL(10,2),
	TotalDev DECIMAL(10,2),
	ContCom DECIMAL(10,2),
	Desempleo DECIMAL(10,2),
	FP DECIMAL(10,2),
	HorasExtraFM DECIMAL(10,2),
	OtrasHoras DECIMAL(10,2),
	TotalApor DECIMAL(10,2),
	IRPF DECIMAL(10,2),
	Anticipo DECIMAL(10,2),
	ValorEspecie DECIMAL(10,2),
	OtrasDed DECIMAL(10,2),
	TotalDed DECIMAL(10,2),
	Neto DECIMAL(10,2),
	BCCC DECIMAL(10,2),
	BCCP DECIMAL(10,2),
	BHE DECIMAL(10,2),
	BHEFM DECIMAL(10,2),
	PRIMARY KEY(CodNom,CodTra),
	CONSTRAINT fk_tra_nom FOREIGN KEY(CodTra)
	REFERENCES trabajador(CodTra) ON UPDATE CASCADE
);

CREATE TABLE horas(
	CodTra INT(11),
	CodEmp INT(11),
	MesAnyo VARCHAR(7),
	N_HorasEX INT(3),
	N_HorasTot INT(11),
	N_DiasTrab INT(11),
	PRIMARY KEY(CodTra,CodEmp,MesAnyo),
	CONSTRAINT fk_tra_hor FOREIGN KEY(CodTra)
	REFERENCES trabajador(CodTra) ON UPDATE CASCADE,
	CONSTRAINT fk_emp_hor FOREIGN KEY(CodEmp)
	REFERENCES empresa(CodEmp) ON UPDATE CASCADE
);

INSERT INTO trabajador(NomTra,NifTra,NafSSTra)
VALUES ('Felipe Perez Garcia','89652418M','569842314578'),
('Alejandro Ubeda Miralles','54216874B','754236548748'),
('Angel Riquelme Armando','65247856L','584231789655'),
('Maria Garcia Hernández','78056945L','963215975464'),
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



INSERT INTO empresa(NomEmp,CifEmp,DirEmp,CCCEmp)
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

INSERT INTO trabaja(CodTra,CodEmp,Convenio,GrupoCot,CatProf,TipoContrato,FecInTra,FecSalTra)
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

