IF NOT EXISTS (SELECT 1 FROM sys.databases WHERE [name] = N'hexagonalTransferencia') BEGIN CREATE DATABASE hexagonalTransferencia END
--USE hexagonalTransferencia
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'conta') BEGIN DROP TABLE conta END
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'conta') BEGIN CREATE TABLE conta (numero int PRIMARY KEY identity(1,1), balance decimal (10,2), accountHolder int, ativo int, data_atual date, limite decimal (10,2)) END

