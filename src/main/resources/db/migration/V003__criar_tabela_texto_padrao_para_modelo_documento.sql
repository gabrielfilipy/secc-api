CREATE TABLE texto_padrao (
  id int NOT NULL AUTO_INCREMENT,
  sigla_pessoa varchar(150) NOT NULL,
  modelo varchar(150) NOT NULL,
  titulo varchar(255) NOT NULL,
  interessado varchar(250) DEFAULT NULL,
  assunto varchar(250) DEFAULT NULL,
  num_referencia varchar(255) DEFAULT NULL,
  texto_memorando text DEFAULT NULL,
  
  PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;
	