use culture_etude;

create table livro (
id int auto_increment primary key,
nome varchar (50) not null,
autor varchar (50) not null,
paginas int not null,
data date,
descricao text not null,
genero varchar (20),
estoque int not null)
default character set utf8mb4
collate utf8mb4_general_ci
ENGINE=InnoDB;

create table filme (
id int auto_increment primary key,
nome varchar (50) not null,
diretor varchar (40) not null,
data date,
descricao text not null,
genero varchar (20),
duracao date not null)
default character set utf8mb4
collate utf8mb4_general_ci
ENGINE=InnoDB;

create table usuario(
 id int auto_increment not null,
 usuario varchar(20) not null unique,
 senha varchar(32) not null unique,
 filme varchar(40),
 livro varchar(40),
 primary key(id)
) default character set utf8mb4
collate utf8mb4_general_ci
ENGINE=InnoDB;

create table carrinho (
id int auto_increment primary key,
user varchar (50) not null,
tipo varchar (40) not null,
nome varchar (40))
default character set utf8mb4
collate utf8mb4_general_ci
ENGINE=InnoDB;

create index i on filme(nome);
create index i2 on livro(nome);
create index i3 on usuario(usuario);

ALTER TABLE carrinho ADD CONSTRAINT fk_usuario FOREIGN KEY (user) REFERENCES usuario (usuario);
ALTER TABLE usuario DROP tipo;
ALTER TABLE usuario DROP nome;
ALTER TABLE usuario DROP imagem;
ALTER TABLE carrinho ADD COLUMN imagem longblob;

CREATE ROLE usuario@'%';

CREATE USER 'administrador'@'localhost' IDENTIFIED BY 'admin0123';
GRANT SELECT ON culture_etude.* TO 'administrador'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'usuario0123';
GRANT DELETE, SELECT,INSERT ON culture_etude.usuario TO usuario;
FLUSH PRIVILEGES;

GRANT UPDATE ON culture_etude.usuario TO usuario;
FLUSH PRIVILEGES;

GRANT SELECT ON culture_etude.filme TO usuario;
FLUSH PRIVILEGES;

GRANT SELECT ON culture_etude.carrinho TO usuario;
FLUSH PRIVILEGES;

GRANT INSERT ON culture_etude.carrinho TO usuario;
FLUSH PRIVILEGES;

GRANT DELETE ON culture_etude.carrinho TO usuario;
FLUSH PRIVILEGES;

GRANT SELECT ON culture_etude.livro TO usuario;
FLUSH PRIVILEGES;

select * from filme;
select * from livro;
select * from usuario;
select * from carrinho;

desc usuario;
desc filme;

ALTER TABLE usuario RENAME COLUMN filme TO tipo;
ALTER TABLE usuario RENAME COLUMN livro TO nome;

select * from livro where autor like '%%';

ALTER TABLE livro Drop estoque;

ALTER TABLE filme MODIFY imagem longblob;
ALTER TABLE livro MODIFY imagem longblob;
ALTER TABLE usuario MODIFY imagem longblob;
ALTER TABLE filme MODIFY duracao time;

UPDATE nome_da_tabela SET coluna = valor WHERE condicao;

truncate usuario;