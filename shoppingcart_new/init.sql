use shoppingdb;
	

	INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
	INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');
	insert into category(category_id,description)values(1,'dress');
	insert into category(category_id,description)values(2,'electronics');
	
	insert into user(id,email,is_active,name,password,phone,role,username) values(1,'tes@edu',1,'TestUser',123,12313,'USER','testuser');
	
	insert into user(id,email,is_active,name,password,phone,role,username) values(2,'admin@edu',1,'TestAdmin',123,12313,'ADMIN','testadmin');
	
	insert into product(product_id,description,price,stock,category_id)values(1,'Product1',100,10,1);
	insert into product(product_id,description,price,stock,category_id)values(2,'Product2',50,10,2);
	insert into product(product_id,description,price,stock,category_id)values(3,'Product3',200,50,1);
