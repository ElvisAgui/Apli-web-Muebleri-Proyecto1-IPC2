Mapeo fisico Base De Datos


    ********Muebleria******
    
CREATE DATABASE Muebleria;

CREATE TABLE pieza (nombre VARCHAR(10) NOT NULL, costo DECIMAL(8,2) NOT NULL, cantidad INT NOT NULL, CONSTRAINT PK_estudiante PRIMARY KEY (nombre));
alter table pieza modify nombre varchar (25);


CREATE TABLE mueble (nombre VARCHAR(25) NOT NULL, costo DECIMAL(8,2), precio_venta DECIMAL(8,2) NOT NULL, CONSTRAINT PK_mueble PRIMARY KEY (nombre));

CREATE TABLE pieza_mueble (nombre_mueble VARCHAR(25) NOT NULL, nombre_pieza VARCHAR(25) NOT NULL, cantidad INT NOT NULL, id INT NOT NULL, CONSTRAINT PK_id PRIMARY KEY (id), CONSTRAINT FK_nombre_mueble FOREIGN KEY (nombre_mueble) REFERENCES mueble(nombre),CONSTRAINT FK_nombre_pieza FOREIGN KEY (nombre_pieza) REFERENCES pieza(nombre));
ALTER TABLE pieza_mueble MODIFY COLUMN id INT auto_increment;

CREATE TABLE usuario (contraseña VARCHAR(15) NOT NULL, nombre VARCHAR(25) NOT NULL, rol TINYINT NOT NULL, CONSTRAINT PK_usuario PRIMARY KEY (contraseña));
ALTER TABLE usuario ADD COLUMN activo BOOLEAN NOT NULL;

CREATE TABLE ensamble (id CHAR(4) NOT NULL, mueble VARCHAR(25) NOT NULL, ensamblador VARCHAR(15) NOT NULL, Fecha DATE NOT NULL, CONSTRAINT PK_ensamble PRIMARY KEY (id), CONSTRAINT FK_mueble_ensamble FOREIGN KEY (mueble) REFERENCES mueble(nombre), CONSTRAINT FK_ensamble_ensamblador FOREIGN KEY (ensamblador) REFERENCES usuario(contraseña));
ALTER TABLE ensamble ADD COLUMN ganancia DECIMAL(8,2) NOT NULL;
ALTER TABLE ensamble ADD COLUMN en_sala BOOLEAN NOT NULL;

CREATE TABLE cliente (nit VARCHAR(15) NOT NULL, nombre VARCHAR(25) NOT NULL, direccion VARCHAR(30) NOT NULL, CONSTRAINT PK_cliente PRIMARY KEY (nit));
alter table cliente modify direccion varchar (50);

CREATE TABLE venta (mueble_ensamblado CHAR(4) NOT NULL, cliente VARCHAR(15) NOT NULL, vendedor VARCHAR(15) NOT NULL, ganacia DECIMAL(8,2) NOT NULL, fecha DATE NOT NULL, correlativo VARCHAR(15) NOT NULL, CONSTRAINT PK_venta PRIMARY KEY (mueble_ensamblado), CONSTRAINT FK_cliente_venta FOREIGN KEY (cliente) REFERENCES cliente(nit), CONSTRAINT FK_vendedor FOREIGN KEY (vendedor) REFERENCES usuario(contraseña));
ALTER TABLE venta ADD FOREIGN KEY (mueble_ensamblado) REFERENCES ensamble (id);
ALTER TABLE venta ADD COLUMN nombre_mueble VARCHAR(25) NULL;

CREATE TABLE devolucion (mueble_vendido CHAR(4) NOT NULL, cliente VARCHAR(15) NOT NULL, recepcionista VARCHAR(15) NOT NULL, pedida DECIMAL(8,2) NOT NULL, fecha DATE NOT NULL, correlativo VARCHAR(15) NOT NULL, CONSTRAINT PK_devolucion PRIMARY KEY (mueble_vendido), CONSTRAINT FK_mueble_devolucion FOREIGN KEY (mueble_vendido) REFERENCES venta (mueble_ensamblado), CONSTRAINT FK_cliente_devolucion FOREIGN KEY (cliente) REFERENCES cliente (nit), CONSTRAINT FK_recepcionista FOREIGN KEY (recepcionista) REFERENCES usuario (contraseña));
