-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-------------------------------------------------------------------------------

-- Hay que tener en cuenta que las contraseñas se guardan cifradas (Bcrypt: hash con salt aleatorio) en la base de datos.
-- Como contraseña cifrada correspondiente a "pa2122" puede usarse :
-- $2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('usuario1', '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC', 'userName1', 'lastName1','usuario1@pa-gei.udc.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('usuario2', '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC', 'userName2', 'lastName2','usuario2@pa-gei.udc.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('usuario3', '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC', 'userName3', 'lastName3','usuario3@pa-gei.udc.es', 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
VALUES ('test', '$2a$10$TNW56LmClyJ6fif80DWifuTzXeGQFSlxsTUyg5redd.CGssl0Pnj.', 'test', 'test','test@pa-gei.udc.es', 0);

INSERT INTO Category (name) VALUES ('Portátiles');
INSERT INTO Category (name) VALUES ('Pantallas');

INSERT INTO Product(userId, categoryId, name, description, publicationDate, expirationDate, initialPrice, currentPrice, shippingInfo, winnerBidId)
VALUES (1, 1, 'Portátil 1', 'descripcion portatil 1', '2020-01-01 00:00:00','2021-01-01 00:00:00', 10.5, 10.5, 'envio portatil 1', null);

INSERT INTO Product(userId, categoryId, name, description, publicationDate, expirationDate, initialPrice, currentPrice, shippingInfo, winnerBidId)
VALUES (1, 1, 'Portátil 2', 'descripcion portatil 2', '2020-01-01 00:00:00','2022-09-01 00:00:00', 10, 10, 'envio portatil 2', null);

INSERT INTO Product(userId, categoryId, name, description, publicationDate, expirationDate, initialPrice, currentPrice, shippingInfo, winnerBidId)
VALUES (1, 2, 'Pantalla 1', 'descripcion pantalla 1', '2020-01-01 00:00:00','2022-10-01 00:00:00', 100, 100, 'envio pantalla 1', null);

INSERT INTO Product(userId, categoryId, name, description, publicationDate, expirationDate, initialPrice, currentPrice, shippingInfo, winnerBidId)
VALUES (1, 2, 'Pantalla 2', 'descripcion pantalla 2', '2020-01-01 00:00:00','2022-11-01 00:00:00', 150, 160, 'envio pantalla 2', null);
