IF NOT EXISTS (SELECT 1 FROM sys.databases WHERE [name] = N'hexagonalTransferencia') BEGIN CREATE DATABASE hexagonalTransferencia END
USE hexagonalTransferencia
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'conta') BEGIN DROP TABLE conta CREATE TABLE conta (numero int PRIMARY KEY identity(1,1),saldo decimal (10,2),correntista varchar (200)) END

