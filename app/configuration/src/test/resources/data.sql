DROP TABLE conta
CREATE TABLE conta ( numero int PRIMARY KEY identity(1,1), saldo decimal (10,2), correntista varchar (200) )
insert into conta values (100, 'Fernando Banco teste')
insert into conta values (100, 'Anny Banco teste')
