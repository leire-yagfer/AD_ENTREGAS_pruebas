DROP DATABASE IF EXISTS GestionHotelesApi;
CREATE DATABASE GestionHotelesApi;
USE GestionHotelesApi;

-- Tabla de hotel
CREATE TABLE HOTEL (
                       id_hotel INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       descripcion TEXT,
                       categoria INT NOT NULL,
                       piscina BOOLEAN NOT NULL,
                       localidad VARCHAR(100) NOT NULL
);

-- Tabla de habitación
CREATE TABLE HABITACION (
                            id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
                            tamano FLOAT NOT NULL,
                            personas BOOLEAN NOT NULL, -- 0 es 1 persona y 1 son 2 personas
                            precio_noche DECIMAL(10, 2) NOT NULL,
                            incluye_desayuno BOOLEAN NOT NULL,
                            ocupada BOOLEAN NOT NULL,
                            id_hotel INT NOT NULL,
                            FOREIGN KEY (id_hotel) REFERENCES HOTEL(id_hotel) ON DELETE CASCADE
);

CREATE TABLE USUARIO (
                         username VARCHAR(100) PRIMARY KEY,
                         pswd VARCHAR(100)
);


-- Insertar datos en la tabla HOTEL
INSERT INTO HOTEL (nombre, descripcion, categoria, piscina, localidad)
VALUES
    ('Hotel Sol', 'Hotel de lujo con vistas al mar', 5, TRUE, 'Madrid'),
    ('Hotel Luna', 'Hotel moderno en el centro urbano', 4, FALSE, 'Barcelona'),
    ('Hotel Estrella', 'Hotel económico y acogedor', 3, TRUE, 'Sevilla'),
    ('Hotel Paraíso', 'Resort con piscina y spa', 5, TRUE, 'Málaga'),
    ('Hotel Montaña', 'Rústico y cómodo en las montañas', 4, FALSE, 'Granada');

-- Insertar datos en la tabla HABITACION
INSERT INTO HABITACION (tamano, personas, precio_noche, incluye_desayuno, ocupada, id_hotel)
VALUES
    (20.5, TRUE, 120.00, TRUE, FALSE, 1), -- Habitación en Hotel Sol
    (15.0, FALSE, 90.00, FALSE, TRUE, 1), -- Habitación en Hotel Sol
    (18.0, TRUE, 75.50, TRUE, TRUE, 2), -- Habitación en Hotel Luna
    (25.0, TRUE, 150.00, TRUE, FALSE, 3), -- Habitación en Hotel Estrella
    (30.0, FALSE, 200.00, TRUE, FALSE, 4); -- Habitación en Hotel Paraíso

-- Insertar datos en la tabla USER
INSERT INTO USUARIO (username, pswd) VALUES
    ("juan", "juan");