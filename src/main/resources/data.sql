INSERT INTO restaurante (id, cep, complemento, nome) VALUES
(1L, '00231524', 'KM 32', 'TREM BÃO DE MINAS'),
(2L, '00231522', 'RUA PEIXOTO DIAS', 'GAÚCHO'),
(3L, '00231523', 'AVENIDA BULLEVARD', 'PIZZARIA BOM SABOR'),
(4L, '00231525', 'RUA CONTINENTAL', 'ALIBABA'),
(5L, '85422651', 'AVENIDA DO BAIÃO 3', 'CASA DO NORTE');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
(1L, '1234567', 'Complemento Endereco Cliente 1', 'Aline'),
(2L, '7891011', 'Complemento Endereco Cliente 2', 'Joao'),
(3L, '1213141', 'Complemento Endereco Cliente 3', 'Margarid a');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1L, true, 'Produto 1', 5.0, 1L),
(2L, true, 'Produto 2', 6.0, 1L),
(3L, true, 'Produto 3', 7.0, 2L);

INSERT INTO sacola (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 0, false, 0.0, 1L),
(2L, 0, false, 0.0, 2L),
(3L, 0, false, 0.0, 3L);