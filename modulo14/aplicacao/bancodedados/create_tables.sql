CREATE TABLE IF NOT EXISTS projeto ( 
	projeto_id           serial  NOT NULL  ,
	projeto_nome         varchar(255)    ,
	projeto_descricao    text    ,
	projeto_inicio       timestamp    ,
	projeto_fim          timestamp    ,
	projeto_status       boolean    ,
	CONSTRAINT pk_projeto PRIMARY KEY ( projeto_id )
 );

CREATE TABLE IF NOT EXISTS recurso ( 
	recurso_id           serial  NOT NULL  ,
	recurso_nome         varchar(255)    ,
	recurso_funcao       varchar(255)    ,
	CONSTRAINT pk_recurso PRIMARY KEY ( recurso_id )
 );

CREATE TABLE IF NOT EXISTS status_tarefa ( 
	status_tarefa_id     serial    ,
	status_descricao     varchar(255)    ,
	CONSTRAINT unq_status_tarefa_status_tarefa_id UNIQUE ( status_tarefa_id ) 
 );

CREATE TABLE IF NOT EXISTS tarefa ( 
	tarefa_id            serial  NOT NULL  ,
	tarefa_titulo        varchar(255)    ,
	tarefa_descricao     text    ,
	tarefa_inicio        timestamp    ,
	tarefa_fim           timestamp    ,
	status_tarefa_id     integer    ,
	recurso_id           integer  NOT NULL  ,
	projeto_id           integer  NOT NULL  ,
	CONSTRAINT pk_tarefa PRIMARY KEY ( tarefa_id )
 );

CREATE TABLE IF NOT EXISTS gerente_projeto ( 
	projeto_id           integer  NOT NULL  ,
	recurso_id           integer  NOT NULL  
 );

CREATE TABLE IF NOT EXISTS usuario ( 
	user_id              serial  NOT NULL  ,
	user_nome            varchar(255)    ,
	user_email           varchar(255)    ,
	user_password        varchar(255)    ,
	PRIMARY KEY ( user_id )
 );

ALTER TABLE gerente_projeto ADD CONSTRAINT fk_projeto FOREIGN KEY ( projeto_id ) REFERENCES projeto( projeto_id );

ALTER TABLE gerente_projeto ADD CONSTRAINT fk_recurso FOREIGN KEY ( recurso_id ) REFERENCES recurso( recurso_id );

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_status FOREIGN KEY ( status_tarefa_id ) REFERENCES status_tarefa( status_tarefa_id );

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_recurso FOREIGN KEY ( recurso_id ) REFERENCES recurso( recurso_id );

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_projeto FOREIGN KEY ( projeto_id ) REFERENCES projeto( projeto_id );

COMMENT ON COLUMN projeto.projeto_id IS 'Chave-primária';

COMMENT ON COLUMN projeto.projeto_nome IS 'Nome do projeto';

COMMENT ON COLUMN projeto.projeto_descricao IS 'Descrição do projeto';

COMMENT ON COLUMN projeto.projeto_inicio IS 'Data de início do projeto';

COMMENT ON COLUMN projeto.projeto_fim IS 'Data de finalização do projeto';

COMMENT ON COLUMN projeto.projeto_status IS 'Status do projeto - ativo|inativo';

COMMENT ON COLUMN recurso.recurso_id IS 'Chave-primária';

COMMENT ON COLUMN recurso.recurso_nome IS 'Nome do colaborador';

COMMENT ON COLUMN recurso.recurso_funcao IS 'Função do colaborador';

COMMENT ON TABLE status_tarefa IS 'Armazena os status possíveis de uma tarefa: pendente|em andamento|etc';

COMMENT ON COLUMN status_tarefa.status_tarefa_id IS 'Chave-primária';

COMMENT ON COLUMN status_tarefa.status_descricao IS 'Descrição do status';

COMMENT ON COLUMN tarefa.tarefa_titulo IS 'Título da tarefa';

COMMENT ON COLUMN tarefa.tarefa_descricao IS 'Descrição da tarefa';

COMMENT ON COLUMN tarefa.tarefa_inicio IS 'Data de início da tarefa';

COMMENT ON COLUMN tarefa.tarefa_fim IS 'Data de finalização da tarefa';

COMMENT ON COLUMN tarefa.status_tarefa_id IS 'Chave estrangeira - status da tarefa';

COMMENT ON COLUMN tarefa.recurso_id IS 'Chave-estrangeira - Recurso responsável pela tarefa';

COMMENT ON TABLE gerente_projeto IS 'Armazena a relação entre gerente e projeto';

COMMENT ON COLUMN gerente_projeto.projeto_id IS 'Chave-estrangeira - Projeto';

COMMENT ON COLUMN gerente_projeto.recurso_id IS 'Chave-estrangeira - Recurso';