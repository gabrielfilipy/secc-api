CREATE TABLE movimentacao (
  id int NOT NULL AUTO_INCREMENT,
  sigla_subscritor varchar(40) NOT NULL,
  lotacao varchar(100) NOT NULL,
  cargo varchar(255) NOT NULL,
  tipo_movimentacao varchar(15) NOT NULL,
  data_criacao datetime NOT NULL,
  data_finalizacao datetime DEFAULT NULL,
  documento_id bigint not null,
  
  PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;
	
alter table movimentacao add constraint fk_movimentacao_documento
foreign key (documento_id) references documento (id);