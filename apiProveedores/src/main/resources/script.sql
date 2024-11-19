-- Crear la tabla proveedor
CREATE TABLE IF NOT EXISTS proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_alta DATE,
    id_cliente INT
);

-- Insertar datos de ejemplo
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Coca-cola', '2024-11-18', 5);
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Pepsi', '2024-11-18', 5);
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Redbull', '2024-11-18', 6);
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Fanta', '2024-11-18', 7);
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Casera', '2024-11-18', 8);
INSERT INTO proveedor (nombre, fecha_alta, id_cliente) VALUES ('Trina', '2024-11-18', 6);
