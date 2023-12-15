//INSERTANDO EN TABLA USERS
INSERT INTO users(nombre, password, apellido, email, rol) VALUES('Ignacio', '$2a$10$tfkB.Uc8hBeQBMf.Y/j/8.Ubf7gsuseI6W7lTlz3fhtPJebVDr83G', 'Zuñiga', 'izuniga@ciisa.cl', 'ADMIN');
INSERT INTO users(nombre, password, apellido, email, rol) VALUES('Camila', '$2a$10$tfkB.Uc8hBeQBMf.Y/j/8.Ubf7gsuseI6W7lTlz3fhtPJebVDr83G', 'Castillo', 'ccastillo@enac.cl', 'USER');
INSERT INTO users(nombre, password, apellido, email, rol) VALUES('Bryan', '$2a$10$tfkB.Uc8hBeQBMf.Y/j/8.Ubf7gsuseI6W7lTlz3fhtPJebVDr83G', 'Becerra', 'bryan.becerra@ciisa.cl','ADMIN');
//INSERT INTO users(nombre, password, apellido, email, rut, rol) VALUES('', '','','', '', '');


//INSERTANDO EN TABLA CATEGORIA
INSERT INTO categorias(nombre, descripcion) VALUES('Categoria de prueba', 'Descripsao de categoria prueba');
INSERT INTO categorias(nombre, descripcion) VALUES('Juguetería', 'Todo tipo de juguetes');
INSERT INTO categorias(nombre, descripcion) VALUES('Calzado', 'zapatillas, zapatos, botas, chanclas, etc');
INSERT INTO categorias(nombre, descripcion) VALUES('Tecnología', 'computadores, celulares, videojuegos, audífonos, parlantes, relojes, tablets, etc');
INSERT INTO categorias(nombre, descripcion) VALUES('Línea blanca', 'lavadoras, cocina, horno, refrigeradores, etc');
INSERT INTO categorias(nombre, descripcion) VALUES('Decoración', 'Lámparas, futones, cuadros, alfombras, pinturas, etc');
INSERT INTO categorias(nombre, descripcion) VALUES('Ropa', 'poleras, polerones, chaquetas, camisetas, corbatas, ropa de bebé, etc');
INSERT INTO categorias(nombre, descripcion) VALUES('Carnes', 'mariscos y pescados, pollo y pavo, carne de cerdo, carne de res, pescados, etc');



//INSERTANDO EN TABLA ESTADOS
INSERT INTO estados(nombre) VALUES('Disponible');
INSERT INTO estados(nombre) VALUES('Agotado');



//INSERTANDO EN TABLA PRODUCTOS

INSERT INTO productos(nombre, valor, stock, descripcion) VALUES('Buzz Lightyear figura de acción','130000','20','figura de acción del increíble astronauta de la película de toy story! flipas!');
INSERT INTO productos(nombre, valor, stock, descripcion) VALUES('Jordan 3','240000','15','Zapatillas jordan flipantes');
INSERT INTO productos(nombre, valor, stock, descripcion) VALUES('iPhone 15 PRO','1000000','30','Dispositivo celular de última generación');
//INSERT INTO productos(nombre, valor, stock, descripcion) VALUES('','','','');




