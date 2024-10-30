DROP DATABASE IF EXISTS Concesionario;
CREATE DATABASE Concesionario;
USE Concesionario;

CREATE TABLE Coche (
    matricula VARCHAR(15) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL
);