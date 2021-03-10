insert into usuario ( email, data_inscricao, senha) values ( 'jeancbsan@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );
insert into usuario ( email, data_inscricao, senha) values ( 'mel@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );
insert into usuario ( email, data_inscricao, senha) values ( 'maria@gmail.com' ,'2021-02-26T03:55:15', '$2a$10$0T.lLhVYihj25.YsaqLzfeJjBgzkmqlRQbhbtV5flHpeOXCjkzPG6' );

insert into categoria (nome) values ( 'tecnologia');
insert into categoria (nome, categoria_id) values ( 'Celular', 1);
insert into categoria (nome, categoria_id) values ( 'Galaxy One', 2);

insert into produto (nome, quantidade, valor, descricao, categoria_id, usuario_id) values ( 'Iphone PLUS', 50, 1000.00, 'Excelente Aparelho', 1, 1);
insert into produto (nome, quantidade, valor, descricao, categoria_id, usuario_id) values ( 'TV LED', 12, 1400.00, 'MELHOR EM ALTA DEFINIÇÃO', 2, 2);
insert into produto (nome, quantidade, valor, descricao, categoria_id, usuario_id) values ( 'PS5', 1, 3000.00, 'A MELHOR EXPERIENCIA EM JOGOS', 2, 1);

insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Cor', 'Branca', 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Tela', '6.4 polegadas', 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'Textura', 'liso', 1);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'ESPESSURA DA TELA', '3 CM', 2);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'PIXELS', 'AUTO ILUMINADOS', 2);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'TENSOES', 'BIVOLT', 2);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'PROCESSADOR', 'DA NASA', 3);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'DESIGNER', 'UNICO NO UNIVERSO', 3);
insert into caracteristica_produto (nome, descricao, produto_id) values ( 'RAPIDES', 'VELOCIDADE TOTAL', 3);

insert into imagem_produto (link, produto_id) values ( 'www.produtoa.com.br', 1);
insert into imagem_produto (link, produto_id) values ( 'www.produtob.com.br', 1);
insert into imagem_produto (link, produto_id) values ( 'www.produtoc.com.br', 1);
insert into imagem_produto (link, produto_id) values ( 'www.produtoa.com.br', 2);
insert into imagem_produto (link, produto_id) values ( 'www.produtob.com.br', 2);
insert into imagem_produto (link, produto_id) values ( 'www.produtoc.com.br', 2);
insert into imagem_produto (link, produto_id) values ( 'www.produtoa.com.br', 3);
insert into imagem_produto (link, produto_id) values ( 'www.produtob.com.br', 3);
insert into imagem_produto (link, produto_id) values ( 'www.produtoc.com.br', 3);

insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas unidadades restam?', '2021-03-08 20:39:52.501381', 1, 1);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas cores tem esse modelo?', '2021-03-08 20:39:52.501381', 1, 1);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Tem bluetooh?', '2021-03-08 20:39:52.501381', 1, 1);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas unidadades restam?', '2021-03-08 20:39:52.501381', 2, 2);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas cores tem esse modelo?', '2021-03-08 20:39:52.501381', 2, 2);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Tem bluetooh?', '2021-03-08 20:39:52.501381', 2, 2);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas unidadades restam?', '2021-03-08 20:39:52.501381', 3, 3);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Quantas cores tem esse modelo?', '2021-03-08 20:39:52.501381', 3, 3);
insert into pergunta (titulo, momento_criacao, produto_id, usuario_id) values ( 'Tem bluetooh?', '2021-03-08 20:39:52.501381', 3, 3);

insert into opiniao (titulo, descricao, nota, produto_id, usuario_id) values ( 'Muito Bom!', 'Adorei o designer, muito bonito', 5, 1, 2);
insert into opiniao (titulo, descricao, nota, produto_id, usuario_id) values ( 'Show!', 'Valeu a pena cada centavo investido', 5, 1, 3);
insert into opiniao (titulo, descricao, nota, produto_id, usuario_id) values ( 'Perfeito.', 'Indico pra todos', 4, 1, 1);
insert into opiniao (titulo, descricao, nota, produto_id, usuario_id) values ( 'Mais ou menos!', 'Deveria vim com sorround', 4, 1, 1);
insert into opiniao (titulo, descricao, nota, produto_id, usuario_id) values ( 'Mais ou menos!', 'Deveria vim com sorround', 5, 2, 1);


