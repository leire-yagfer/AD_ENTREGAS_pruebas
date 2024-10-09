DROP DATABASE IF EXISTS tienda_cosmetica;
CREATE DATABASE tienda_cosmetica;
USE tienda_cosmetica;

CREATE TABLE Productos (
                           id_producto INT PRIMARY KEY AUTO_INCREMENT,
                           nombreProducto VARCHAR(100) NOT NULL,
                           categoria VARCHAR(50),
                           precio DECIMAL(10, 2) NOT NULL,
                           stock INT NOT NULL
);

CREATE TABLE Clientes (
                          id_cliente INT PRIMARY KEY AUTO_INCREMENT,
                          nombre_cliente VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Ventas (
                        id_venta INT PRIMARY KEY AUTO_INCREMENT,
                        id_cliente INT,
                        id_producto INT,
                        fecha_venta DATE NOT NULL,
                        cantidad INT NOT NULL,
                        total DECIMAL(10, 2) NOT NULL,
                        FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
                        FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);



INSERT INTO Productos (nombreProducto, categoria, precio, stock)
VALUES
    ('Crema hidratante', 'Cuidado facial', 19.99, 50),
    ('Sérum antiarrugas', 'Cuidado facial', 29.95, 30),
    ('Labial mate', 'Maquillaje', 9.99, 100);


INSERT INTO Clientes (nombre_cliente, email)
VALUES
    ('Ana Pérez', 'ana.perez@mail.com'),
    ('Carlos García', 'carlos.garcia@mail.com');


INSERT INTO Ventas (id_cliente, id_producto, fecha_venta, cantidad, total)
VALUES
    (1, 1, '2024-09-30', 2, 39.98),  -- Ana Pérez compró 2 cremas hidratantes
    (2, 2, '2024-09-30', 1, 29.99);  -- Carlos García compró 1 sérum antiarrugas