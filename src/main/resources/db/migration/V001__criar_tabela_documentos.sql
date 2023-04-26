CREATE TABLE documento ( 
  id bigint NOT NULL AUTO_INCREMENT,
  sigla varchar(40) NOT NULL,
  resp_assinatura varchar(150) NOT NULL,
  cadastrante varchar(150) NOT NULL,
  interessado varchar(250) DEFAULT NULL,
  assunto varchar(250) DEFAULT NULL,
  num_referencia varchar(255) DEFAULT NULL,
  texto_memorando text DEFAULT NULL,
  data_criacao datetime DEFAULT NULL,
  data_assinatura datetime DEFAULT NULL,
  data_exclusao datetime DEFAULT NULL,
  id_catalogo int DEFAULT NULL,
  tipo_aquisicao varchar(50) DEFAULT NULL,
  meus_textos_padroes varchar(250) DEFAULT NULL,
  substituto tinyint(1) DEFAULT NULL,
  personalizar tinyint(1) DEFAULT NULL,
  modelo varchar(255) NOT NULL,
  
  PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;