IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'conta') BEGIN DROP TABLE conta END
CREATE TABLE conta (numero int PRIMARY KEY identity(1,1),saldo decimal (10,2), correntista int, ativa int, data_atual date, limite decimal (10,2))
insert into conta (saldo, correntista, ativa, limite) values (2000, 1, 1, 1000)
insert into conta (saldo, correntista, ativa, limite) values (100, 2, 1, 1000)
insert into conta (saldo, correntista, ativa, limite) values (100, 3, 0, 1000)
