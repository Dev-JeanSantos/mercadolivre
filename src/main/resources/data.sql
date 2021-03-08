insert into usuario ( email, data_inscricao, senha) values ( 'jeancbsan@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );
insert into usuario ( email, data_inscricao, senha) values ( 'mel@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );
insert into usuario ( email, data_inscricao, senha) values ( 'maria@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );

insert into categoria (nome) values ( 'tecnologia');
insert into categoria (nome, categoria_id) values ( 'Celular', 1);
insert into categoria (nome, categoria_id) values ( 'Galaxy One', 2);

insert into produto (nome, quantidade, valor, descricao, categoria_id, usuario_id) values ( 'Iphone PLUS', 10, 1000.00, 'Excelente Aparelho', 1, 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Cor', 'Branca', 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Tela', '6.4 polegadas', 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Textura', 'liso', 1);
 
 
  