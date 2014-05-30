database name = dinnerChief

create table enderecos (id serial primary key, rua varchar(250), numero varchar(15), complemento varchar(25), bairro varchar(70), cidade varchar(50), cep varchar(15), latitude varchar(50), longitude varchar(50));

create table clientes (id serial primary key, nome varchar(50) not null, telefone varchar(25), email varchar(50), flag_excluido boolean, id_endereco int references enderecos(id));
ALTER TABLE clientes ADD COLUMN flag_excluido boolean;
ALTER TABLE clientes ALTER COLUMN flag_excluido SET DEFAULT false;
UPDATE clientes SET flag_excluido = false;

create table produtos (id serial primary key, nome varchar(100) not null, descricao varchar(250), preco float not null, flag_excluido boolean);
ALTER TABLE produtos ADD COLUMN flag_excluido boolean;
ALTER TABLE produtos ALTER COLUMN flag_excluido SET DEFAULT false;
UPDATE produtos SET flag_excluido = false;
insert into produtos (nome, preco, flag_excluido) values ('X-Salada', 8.50, false);
insert into produtos (nome, preco, flag_excluido) values ('X-Bacon', 8.50, false);

create table statusPedido (id serial primary key, descricao varchar(50));
insert into statusPedido (descricao) values ('Em aberto');
insert into statusPedido (descricao) values ('Em andamento');
insert into statusPedido (descricao) values ('Pronto para entrega');
insert into statusPedido (descricao) values ('Finalizado');
insert into statusPedido (descricao) values ('Cancelado');


create table pedidos (id serial primary key, id_cliente int references clientes(id), valorTotalPedido float not null, data date not null, 
id_statusPedido int references statusPedido(id), observacao varchar(250), retiradoLocal boolean, lotePedido_id int references lotePedido(id));
ALTER TABLE pedidos DROP COLUMN data;
ALTER TABLE pedidos ADD COLUMN data timestamp with time zone;

create table produto_pedido (id serial primary key, produto_id int references produtos(id), pedido_id int references pedidos(id), quantidade int);

create table entregadores (id serial primary key, nome varchar(50) not null, telefone varchar(25), capacidadeEntrega varchar(50), flag_excluido boolean, id_endereco int references enderecos(id));
ALTER TABLE entregadores ADD COLUMN flag_excluido boolean;
ALTER TABLE entregadores ALTER COLUMN flag_excluido SET DEFAULT false;
UPDATE entregadores SET flag_excluido = false;

create table statusLote (id serial primary key, descricao varchar(50)); 
insert into statusLote (descricao) values ('Em aberto');
insert into statusLote (descricao) values ('Saiu para entrega');
insert into statusLote (descricao) values ('Finalizado');

create table lotePedido (id serial primary key, id_entregador int references entregadores(id), id_statusLote int references statusLote(id));
ALTER TABLE lotePedido ADD COLUMN id_statusLote int references statusLote(id);

