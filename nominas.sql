DROP DATABASE IF EXISTS nominas;
CREATE DATABASE nominas
CHARACTER SET utf8
COLLATE utf8_spanish_ci;

USE nominas;

CREATE TABLE trabajador(
	CodTra INT(11) AUTO_INCREMENT PRIMARY KEY,
	NomTra VARCHAR(40) NOT NULL,
	NifTra VARCHAR(9) NOT NULL UNIQUE,
	NafSSTra VARCHAR(12) NOT NULL UNIQUE
);

CREATE TABLE empresa(
	CodEmp INT(11) AUTO_INCREMENT PRIMARY KEY,
	NomEmp VARCHAR(50) NOT NULL,
	CifEmp VARCHAR(9) NOT NULL UNIQUE,
	DirEmp VARCHAR(100),
	CCCEmp VARCHAR(11) NOT NULL UNIQUE
);

CREATE TABLE trabaja(
	CodTra INT(11),
	CodEmp INT(11),
	Convenio VARCHAR(100),
	GrupoCot VARCHAR(50) NOT NULL,
	CatProf VARCHAR(100) NOT NULL,
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
	N_Horas INT(3),
	PRIMARY KEY(CodTra,CodEmp,MesAnyo),
	CONSTRAINT fk_tra_hor FOREIGN KEY(CodTra)
	REFERENCES trabajador(CodTra) ON UPDATE CASCADE,
	CONSTRAINT fk_emp_hor FOREIGN KEY(CodEmp)
	REFERENCES empresa(CodEmp) ON UPDATE CASCADE
);

INSERT INTO trabajador(NomTra,NifTra,NafSSTra)
VALUES ('Felipe Perez Garcia','89652418M','569842314578'),
('Alejandro Ubeda Miralles','54216874B','754236548745'),
('Angel Riquelme Armando','65247856L','584231789654'),
('Maria Garcia Hernández','78056945L','632159754642'),
('Elena Pascual Ramon','95378435D','842679134975'),
('Pedro Benito Gil','88560248H','002549678542'),
('Paula Gomez Garcia','77804562L','234512567216'),
('Javier Berenguer Manzanera','60315950M','800235478951'),
('Jennifer Leal Blanc','54655802A','220354786542'),
('Ferran Villa Capdevila','96542302N','5842008764231'),
('Marina Torres Gil','69366501L','500478635665'),
('Gervasio Ubeda Guil','52687412K','854268741256'),
('Evaristo Garrote Fino','54345871G','658741259874'),
('Juan Carlos Borbon','75982564R','794613258465'),
('Luz Cuesta Mogollon','46971325H','974652307419'),
('Fermin Galarga Gutierrez','46825719U','016257419852'),
('Florentino Perez Rodriguez','82974630D','066574108997'),
('Andres Iniesta Lujan','45682175L','774586302125'),
('Isabel Diaz Ayuso','74196582P','827364098126'),
('Juan Cuesta Presidente','98723498U','997465801452'),
('Josep Guardiola Sala','92736512E','987346238761'),
('Cristiano Ronaldo Siu','82574690P','876234058107'),
('Iker Jimenez Ruiz','24574598D','874596325741'),
('Kiko Rivera Pantoja','23487462M','745821698201'),
('Benita Torres Fuertes','485216074P','745290354127');


INSERT INTO empresa(NomEmp,CifEmp,DirEmp,CCCEmp)
VALUES ('CocaCola','H85423645','Calle Severo Ochoa','01521486315'),
('Iberdrola','G52348756','Calle Santa Barbara','523157801453'),
('Nestle','M32184623','Calle Azorin','642148953412'),
('Colacao','H24568713','Calle Ramon y Cajal','843897452536'),
('Microsoft','M74825698','Calle del Valle','458126874521'),
('Chupachups','J78541268','Calle Falsa','873092834098'),
('Tarradellas','P83765412','Calle Espetec','752148963574'),
('Rumasa','G74582359','Calle Abeja','746982145982'),
('Mercadona','E98734021','Calle Hacendado','472016980310'),
('Brugal','H934023459','Calle Concola','128745963257');
