CREATE DATABASE desafio_bridge;
USE desafio_bridge;

CREATE TABLE Calculadora_primos(
	id					INTEGER AUTO_INCREMENT PRIMARY KEY,
    numero				INT NOT NULL,
    quantidade_primos	INT NOT NULL,
    tempo_utilizado		LONG NOT NULL
);
