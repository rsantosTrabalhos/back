CREATE TABLE categoria 
(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outros');



CREATE TABLE tipo_pet 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO tipo_pet VALUES (NULL, 'Peixes');
INSERT INTO tipo_pet VALUES (NULL, 'Anfíbios');
INSERT INTO tipo_pet VALUES (NULL, 'Repteis');
INSERT INTO tipo_pet VALUES (NULL, 'Aves');
INSERT INTO tipo_pet VALUES (NULL, 'Mamíferos');

CREATE TABLE porte 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO porte VALUES (NULL, 'Pequeno');
INSERT INTO porte VALUES (NULL, 'Médio');
INSERT INTO porte VALUES (NULL, 'Grande');

CREATE TABLE pessoa 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(200) NOT NULL,
    rg VARCHAR(20) NOT NULL,
    cpf VARCHAR(30) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO pessoa VALUES (NULL, 'Ruan Pedro Henrique Nogueira', '291053142', '87435546950');
INSERT INTO pessoa VALUES (NULL, 'Patrícia Fernanda de Paula', '508389094', '68002558669');
INSERT INTO pessoa VALUES (NULL, 'Osvaldo Kaique Ramos', '230876122', '22154022707');
INSERT INTO pessoa VALUES (NULL, 'Emilly Gabriela Laís Silveira', '316633112', '46495664010');
INSERT INTO pessoa VALUES (NULL, 'Anthony Guilherme Gomes', '450778277', '13883748706');

CREATE TABLE atendente 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_pessoa BIGINT(20) NULL,
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO atendente VALUES (NULL, 1);
INSERT INTO atendente VALUES (NULL, 2);

CREATE TABLE endereco 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cidade VARCHAR(300) NOT NULL,
    logradouro VARCHAR(300) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    estado VARCHAR(5) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    ponto_referencia VARCHAR(300) NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO endereco VALUES (NULL, 'Manaus', 'Beco Jesus Salvador', '779', 'Coroado', 'AM', '69082631', '');
INSERT INTO endereco VALUES (NULL, 'Natal', 'Rua Desportista Raimundo Nonato Pereira', '693', 'Pajuçara', 'RN', '59133315', '');
INSERT INTO endereco VALUES (NULL, 'Palmas', 'Quadra 407 Sul Avenida NS 7', '407', 'Plano Diretor Sul', 'TO', '77015690', '');
INSERT INTO endereco VALUES (NULL, 'Campo Grande', 'Rua Pacoti', '722', 'Jardim Vida Nova', 'MS', '79017781', '');
INSERT INTO endereco VALUES (NULL, 'Cuiabá', 'Rua das Rosas', '463', 'São Francisco', 'MT', '78088690', '');

CREATE TABLE cliente 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	total_pataz int NULL,
    email VARCHAR(300) NOT NULL,
    data_cadastro datetime NOT NULL,
    id_atendente BIGINT(20) NOT NULL,
	id_pessoa BIGINT(20) NOT NULL,
	id_endereco BIGINT(20) NOT NULL,
	FOREIGN KEY (id_atendente) REFERENCES atendente(id),
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id),
    FOREIGN KEY (id_endereco) REFERENCES endereco(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO cliente VALUES (NULL, NULL, 'osvaldo@osvaldo.com', now(), 1, 3, 1);
INSERT INTO cliente VALUES (NULL, NULL, 'emilly@emilly.com', now(), 2, 4, 2);
INSERT INTO cliente VALUES (NULL, NULL, 'anthony@anthony.com', now(), 2, 5, 3);

CREATE TABLE pet 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(300) NOT NULL,
    alergia VARCHAR(300) NULL,
    raca VARCHAR(300) NULL,
    descricao VARCHAR(300) NULL,
    autoriza_foto BIT NOT NULL,
    data_cadastro DATETIME NOT NULL,
    id_tipo_pet BIGINT(20) NOT NULL,
    id_atendente BIGINT(20) NOT NULL,
	id_porte BIGINT(20) NOT NULL,
	id_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (id_tipo_pet) REFERENCES tipo_pet(id),
	FOREIGN KEY (id_atendente) REFERENCES atendente(id),
    FOREIGN KEY (id_porte) REFERENCES porte(id),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO pet VALUES (NULL, 'Pingo', '', '', 'Preto', 1, now(), 5, 1, 1, 3);
INSERT INTO pet VALUES (NULL, 'Oliver', '', '', 'Com manchas pretas', 1, now(), 5, 2, 2, 2);
INSERT INTO pet VALUES (NULL, 'Felpudo', '', '', 'Pouco arisco', 1, now(), 5, 1, 3, 1);

CREATE TABLE servico 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tempo VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    patazReceber int NULL,
    patazRealizar int NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO servico VALUES (NULL, '1H', 100.00, '10', '1000');
INSERT INTO servico VALUES (NULL, '1H', 200.00, '20', '2000');
INSERT INTO servico VALUES (NULL, '30M', 50.00, '10', '1000');

CREATE TABLE produto 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(300) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    especificacoes VARCHAR(300) NULL,
    estoque int NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    id_atendente BIGINT(20) NOT NULL,
	FOREIGN KEY (id_atendente) REFERENCES atendente(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO produto VALUES (NULL, 'Shampoo', 'Best', 'Nutrição de qualidade', 10, 20.00, now(), 1);
INSERT INTO produto VALUES (NULL, 'Ração Zul', 'Zul', '', 10, 10.00, now(), 1);

CREATE TABLE servico_produto 
(
	id_produto BIGINT(20) NOT NULL,
    id_servico BIGINT(20) NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_servico) REFERENCES servico(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO servico_produto VALUES (1, 1);

CREATE TABLE tipo_pessoa 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO tipo_pessoa VALUES (NULL, 'Atendente');
INSERT INTO porte VALUES (NULL, 'Administrador');
INSERT INTO porte VALUES (NULL, 'Veterinário');

CREATE TABLE tipo_pessoa_servico 
(
	id_tipo_pessoa BIGINT(20) NOT NULL,
    id_servico BIGINT(20) NOT NULL,
    FOREIGN KEY (id_tipo_pessoa) REFERENCES tipo_pessoa(id),
    FOREIGN KEY (id_servico) REFERENCES servico(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO servico_produto VALUES (1, 1);

CREATE TABLE ordem_servico 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	avaliacao INT NULL,
	data_realizacao DATETIME NOT NULL,
	data_cadastro DATETIME NOT NULL,
	is_realizado BIT NULL,
    is_email_enviado BIT NULL,
	id_atendente BIGINT(20) NULL,
	id_pet BIGINT(20) NULL,
	id_servico BIGINT(20) NULL,
	FOREIGN KEY (id_atendente) REFERENCES atendente(id),
	FOREIGN KEY (id_pet) REFERENCES pet(id),
    FOREIGN KEY (id_servico) REFERENCES servico(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO ordem_servico VALUES (NULL, NULL, date_add(now(), interval 5 hour), now(), NULL, NULL, 1, 1, 1);
INSERT INTO ordem_servico VALUES (NULL, NULL, date_add(now(), interval 5 hour), now(), NULL, NULL, 2, 2, 1);
INSERT INTO ordem_servico VALUES (NULL, 10, date_add(now(), interval -10 day), date_add(now(), interval -10 day), 1, NULL, 2, 2, 1);
INSERT INTO ordem_servico VALUES (NULL, 8, date_add(now(), interval -10 day), date_add(now(), interval -10 day), 1, NULL, 1, 1, 2);
INSERT INTO ordem_servico VALUES (NULL, 9, date_add(now(), interval -15 day), date_add(now(), interval -15 day), 1, NULL, 1, 1, 2);

CREATE TABLE veterinario 
(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	registro_conselho VARCHAR(30) NOT NULL,
	id_pessoa BIGINT(20) NULL,
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE veterinario_tipo_pet 
(
	id_veterinario BIGINT(20) NOT NULL,
    id_tipo_pet BIGINT(20) NOT NULL,
    FOREIGN KEY (id_veterinario) REFERENCES veterinario(id),
    FOREIGN KEY (id_tipo_pet) REFERENCES tipo_pet(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


delimiter $$
create procedure `salvar_veterinario`(/*Pessoa*/
									  in nome VARCHAR(200),
                                      in rg VARCHAR(20),
                                      in cpf VARCHAR(30),
                                      /*Veterinario*/
                                      in registro_conselho VARCHAR(30),
                                      in id_tipo_pet BIGINT(20))

begin
	
    insert into pessoa values (null, nome, rg, cpf);
    set @id_pessoa = last_insert_id();
    
    insert into veterinario values (null, registro_conselho, @id_pessoa);
    set @id_veterinario = last_insert_id();
    
    insert into veterinario_tipo_pet values (@id_veterinario, id_tipo_pet);
    
    
end $$
delimiter;

call salvar_veterinario ('Rodrigo', '144525', '15456641', '15151', 1);

delimiter $$
create procedure `atualizar_veterinario`(/*Pessoa*/
									  in p_id BIGINT(20),
                                      in p_nome VARCHAR(200),
                                      in p_rg VARCHAR(20),
                                      in p_cpf VARCHAR(30),
                                      /*Veterinario*/
                                      in p_id_veterinario BIGINT(20),
                                      in p_registro_conselho VARCHAR(30),
                                      in p_id_tipo_pet BIGINT(20))

begin
	
    update  pessoa 
    set 	nome = p_nome, 
			rg = p_rg, 
            cpf = p_cpf
    where 	id = p_id;       
    
    
    update  veterinario 
    set 	registro_conselho = p_registro_conselho
    where 	id = p_id_veterinario;
    
    
    update 	veterinario_tipo_pet 
    set 	id_tipo_pet = p_id_tipo_pet
    where 	id_veterinario = p_id_veterinario;
    
    
end $$
delimiter;

create view `view_rel_servicos_por_profissional_mes` 
	AS 
    select 		pcli.nome, 
				c.email, 
                os.data_realizacao, 
                p.nome as profissional,
                month(os.data_realizacao) as mes,
                year(os.data_realizacao) as ano,
                os.is_realizado
	from 		ordem_servico os
	join 		atendente a
	on 			os.id_atendente = a.id
	join 		pessoa p
	on 			a.id_pessoa = p.id
	join 		pet pet
	on 			os.id_pet = pet.id
	join 		cliente c 
	on 			pet.id_cliente = c.id
	join 		pessoa pcli
	on 			c.id_pessoa = pcli.id;
	
	
create view `view_rel_avaliacoes_profissional_periodo` 
	AS 
    select 		pcli.nome, 
				os.data_realizacao, 
                date(os.data_realizacao) as 'data',
                p.nome as profissional, 
                os.avaliacao,
                os.is_realizado
	from 		ordem_servico os
	join 		atendente a
	on 			os.id_atendente = a.id
	join 		pessoa p
	on 			a.id_pessoa = p.id
	join 		pet pet
	on 			os.id_pet = pet.id
	join 		cliente c 
	on 			pet.id_cliente = c.id
	join 		pessoa pcli
	on 			c.id_pessoa = pcli.id
	order by 	pcli.nome asc;
	
create view `view_rel_cli_servicos_mes` 
	as
    select 		p.nome,
				os.data_realizacao,
                s.patazReceber,
                c.total_pataz,
                month(os.data_realizacao) as mes,
                year(os.data_realizacao) as ano,
                os.is_realizado
    from 		ordem_servico os
    join 		servico s 
    on			os.id_servico = s.id
    join 		pet pet
    on 			os.id_pet = pet.id
    join 		cliente c
    on 			pet.id_cliente = c.id
    join 		pessoa p
    on 			c.id_pessoa = p.id;
	

CREATE TABLE usuario 
(
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao 
(
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao 
(
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario values (1, 'Administrador', 'admin@doggis.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario values (2, 'Maria Silva', 'maria@doggis.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao values (8, 'ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO usuario_permissao values (1, 1);
INSERT INTO usuario_permissao values (1, 2);
INSERT INTO usuario_permissao values (1, 3);
INSERT INTO usuario_permissao values (1, 4);
INSERT INTO usuario_permissao values (1, 5);
INSERT INTO usuario_permissao values (1, 6);
INSERT INTO usuario_permissao values (1, 7);
INSERT INTO usuario_permissao values (1, 8);

-- maria
INSERT INTO usuario_permissao values (2, 2);
INSERT INTO usuario_permissao values (2, 5);
INSERT INTO usuario_permissao values (2, 8);