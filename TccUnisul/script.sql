database name = dinnerChief

create table enderecos (id serial primary key, rua varchar(250), numero varchar(15), complemento varchar(25), bairro varchar(70), cidade varchar(50), cep varchar(15), latitude varchar(50), longitude varchar(50));

create table clientes (id serial primary key, nome varchar(50) not null, telefone varchar(25), email varchar(50), id_endereco int references enderecos(id));
ALTER TABLE clientes ADD COLUMN flag_excluido boolean;
ALTER TABLE clientes ALTER COLUMN flag_excluido SET DEFAULT false;

create table produtos (id serial primary key, nome varchar(100) not null, descricao varchar(250), preco float not null);
insert into produtos (nome, preco, flag_excluido) values ('X-Salada', 8.50, false);
insert into produtos (nome, preco, flag_excluido) values ('X-Bacon', 8.50, false);
ALTER TABLE produtos ADD COLUMN flag_excluido boolean;
ALTER TABLE produtos ALTER COLUMN flag_excluido SET DEFAULT false;

create table statusPedido (id serial primary key, descricao varchar(50));
insert into statusPedido (descricao) values ('Em aberto');
insert into statusPedido (descricao) values ('Em andamento');
insert into statusPedido (descricao) values ('Pronto para entrega');
insert into statusPedido (descricao) values ('Finalizado');
insert into statusPedido (descricao) values ('Cancelado');


create table pedidos (id serial primary key, id_cliente int references clientes(id), valorTotalPedido float not null, data date not null, 
id_statusPedido int references statusPedido(id), observacao varchar(250), retiradoLocal boolean);

create table produto_pedido (id serial primary key, produto_id int references produtos(id), pedido_id int references pedidos(id), quantidade int);
