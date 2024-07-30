INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10001, 12345678901, 'Cliente 11', '11987654321', 'cliente1@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10002, 23456789012, 'Cliente 2', '11987654322', 'cliente2@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10003, 34567890123, 'Cliente 3', '11987654323', 'cliente3@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10004, 45678901234, 'Cliente 4', '11987654324', 'cliente4@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10005, 56789012345, 'Cliente 5', '11987654325', 'cliente5@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10006, 67890123456, 'Cliente 6', '11987654326', 'cliente6@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10007, 78901234567, 'Cliente 7', '11987654327', 'cliente7@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10008, 89012345678, 'Cliente 8', '11987654328', 'cliente8@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (10009, 90123456789, 'Cliente 9', '11987654329', 'cliente9@email.com');
INSERT INTO cliente (id, cpf, nome, telefone, email) VALUES (100010, 12345678909, 'Cliente 10', '11987654320', 'cliente10@email.com');

INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, tipo) VALUES (10001, 'Marca 1', 'Modelo 1', 2001, 10000.0, 100.0, 'ABC-1234', 'Carro', 0);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, tipo) VALUES (10002, 'Marca 2', 'Modelo 2', 2002, 20000.0, 200.0, 'DEF-5678', 'Carro', 1);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, tipo) VALUES (10003, 'Marca 3', 'Modelo 3', 2003, 30000.0, 300.0, 'GHI-9012', 'Carro', 2);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, tipo) VALUES (10004, 'Marca 4', 'Modelo 4', 2004, 40000.0, 400.0, 'JKL-3456', 'Carro', 0);

INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, carga) VALUES (10005, 'Marca 5', 'Modelo 5', 2005, 50000.0, 500.0, 'MNO-7890', 'Caminhao', 1000);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, carga) VALUES (10006, 'Marca 6', 'Modelo 6', 2006, 60000.0, 600.0, 'PQR-1234', 'Caminhao', 2000);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, carga) VALUES (10007, 'Marca 7', 'Modelo 7', 2007, 70000.0, 700.0, 'STU-5678', 'Caminhao', 3000);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, carga) VALUES (10008, 'Marca 8', 'Modelo 8', 2008, 80000.0, 800.0, 'VWX-9012', 'Caminhao', 4000);

INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, cilindrada) VALUES (10009, 'Marca 9', 'Modelo 9', 2009, 90000.0, 900.0, 'YZA-3456', 'Moto', 100);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, cilindrada) VALUES (100010, 'Marca 10', 'Modelo 10', 2010, 100000.0, 1000.0, 'BCD-7890', 'Moto', 200);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, cilindrada) VALUES (100011, 'Marca 11', 'Modelo 11', 2011, 110000.0, 1100.0, 'EFG-1234', 'Moto', 300);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, cilindrada) VALUES (100012, 'Marca 12', 'Modelo 12', 2012, 120000.0, 1200.0, 'HIJ-5678', 'Moto', 400);

INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, capacidade_Passageiros) VALUES (100013, 'Marca 13', 'Modelo 13', 2013, 130000.0, 1300.0, 'KLM-9012', 'Onibus', 50);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, capacidade_Passageiros) VALUES (100014, 'Marca 14', 'Modelo 14', 2014, 140000.0, 1400.0, 'NOP-3456', 'Onibus', 60);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, capacidade_Passageiros) VALUES (100015, 'Marca 15', 'Modelo 15', 2015, 150000.0, 1500.0, 'QRS-7890', 'Onibus', 70);
INSERT INTO veiculo (id, marca, modelo, ano_De_Fabricacao, valor_Do_Bem, valor_Diaria, placa, dtype, capacidade_Passageiros) VALUES (100016, 'Marca 16', 'Modelo 16', 2016, 160000.0, 1600.0, 'TUV-1234', 'Onibus', 80);


INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, data_devolucao_real, total_aluguel, baixo) VALUES (10001, 10001, 10001, '2022-01-01T00:00:00', '2022-01-10T00:00:00', '2022-01-10T00:00:00', 1000.0, true);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, data_devolucao_real, total_aluguel, baixo) VALUES (10002, 10002, 10002, '2022-02-01T00:00:00', '2022-02-10T00:00:00', '2022-02-10T00:00:00', 2000.0, true);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, data_devolucao_real, total_aluguel, baixo) VALUES (10003, 10003, 10003, '2022-03-01T00:00:00', '2022-03-10T00:00:00', '2022-03-10T00:00:00', 3000.0, true);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, data_devolucao_real, total_aluguel, baixo) VALUES (10004, 10004, 10004, '2022-04-01T00:00:00', '2022-04-10T00:00:00', '2022-04-10T00:00:00', 4000.0, true);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, data_devolucao_real, total_aluguel, baixo) VALUES (10005, 10005, 10005, '2022-05-01T00:00:00', '2022-05-10T00:00:00', '2022-05-10T00:00:00', 5000.0, true);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, total_aluguel) VALUES (10006, 10006, 10006, '2022-06-01T00:00:00', '2022-06-10T00:00:00',  6000.0);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, total_aluguel) VALUES (10007, 10007, 10007, '2022-07-01T00:00:00', '2022-07-10T00:00:00',  7000.0);
INSERT INTO aluguel (id, cliente_id, veiculo_id, data_inicio, data_fim, total_aluguel) VALUES (10008, 10008, 10008, '2022-08-01T00:00:00', '2022-08-10T00:00:00',  8000.0);