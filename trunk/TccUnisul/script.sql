database = TccUnisul


create table bancos(id serial primary key, nome varchar(50) not null);
insert into bancos(nome) values ('Santander');
insert into bancos(nome) values ('Bradesco');
insert into bancos(nome) values ('Banco do Brasil');
insert into bancos(nome) values ('Itaú');
insert into bancos(nome) values ('Caixa Econômica');
insert into bancos(nome) values ('HSBC');

create table tipos_lancamentos (id serial primary key, tipo_lancamento varchar(50) not null);
insert into tipos_lancamentos (tipo_lancamento) values ('Entrada');
insert into tipos_lancamentos (tipo_lancamento) values ('Saída');

create table pessoas(id serial primary key, nome_pessoa varchar(50) not null, cpf int not null, senha varchar(30) not null);
insert into pessoas(nome_pessoa, cpf, senha) values ('Usuario 1', 123456789, '123456');

create table contas (id serial primary key, id_banco int references bancos(id), 
numero_conta int not null, numero_agencia int not null, id_pessoa int references pessoas(id));

create table contas_correntes (id int references contas(id) primary key);

create table lancamentos (id serial primary key, id_conta_corrente int references contas_correntes(id), data date not null, 
id_tipo_lancamento int references tipos_lancamentos(id), motivo varchar(100) not null, valor float not null);

create table transferencias (id_conta_destino int references contas_correntes(id), id int references lancamentos(id) primary key);

create table contas_lancamentos (id_conta_corrente int references contas_correntes(id), id_lancamento int references lancamentos(id));