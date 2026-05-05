CREATE TABLE IF NOT EXISTS category(
	category_id SERIAL PRIMARY KEY,
	kind VARCHAR(100) NOT NULL
	);

CREATE TABLE IF NOT EXISTS car(
	car_id SERIAL PRIMARY KEY,
	model VARCHAR (50) NOT NULL,
	cost_per_day NUMERIC (10, 2) NOT NULL,
	horse_power INT NOT NULL,
	seats INT NOT NULL,
	category_id INT NOT NULL,
	CONSTRAINT r_car_category
		FOREIGN KEY (category_id)
		REFERENCES category(category_id)
		ON DELETE RESTRICT
	);


CREATE TABLE IF NOT EXISTS client(
	client_id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(50) NOT NULL,
	gender VARCHAR (50) NOT NULL,
	address VARCHAR (50) NOT NULL,
	email VARCHAR (50) UNIQUE NOT NULL,
	phone VARCHAR (50) NOT NULL
);

CREATE TABLE IF NOT EXISTS rent(
	rent_id SERIAL PRIMARY KEY,
	days INT NOT NULL,
	date_at DATE NOT NULL,
	client_id INT NOT NULL,
	car_id INT NOT NULL,
	CONSTRAINT r_client_rent
		FOREIGN KEY (client_id)
		REFERENCES client(client_id)
		ON DELETE CASCADE,
	CONSTRAINT r_car_rent
		FOREIGN KEY (car_id)
		REFERENCES car(car_id)
		ON DELETE CASCADE
);