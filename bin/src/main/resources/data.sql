insert into users (username, password, enabled) values
	('user', '$2a$10$g6zS1e93G7T9Id3vMGDvGe03KULJgX9r6EAJZNtmFeP.5nW/KwXnS', true),
	('admin', '$2a$10$I6tkPivOCiLczhoh9Bt4pOefM8WdhIz45WtanWRS.fZeqUlhgY9KO', true);
	
insert into authorities (username, authority) values
	('user', 'ROLE_USER'),
	('admin', 'ROLE_ADMIN');
	

