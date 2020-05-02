  
INSERT INTO users (username, password, enabled) VALUES
	('user', '{noop}user', true),
	('admin', '{noop}admin', true),
	('restuser', '{noop}restuser', true);
	
INSERT INTO authorities (username, authority) VALUES
	('user', 'ROLE_USER'),
	('admin', 'ROLE_ADMIN'),
	('restuser', 'ROLE_RESTUSER');