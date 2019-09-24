CREATE TABLE IF NOT EXISTS `order` (
	id INT AUTO_INCREMENT NOT NULL,
	external_order_id VARCHAR(30),
	customer VARCHAR(30) NOT NULL,
	invoice_number VARCHAR(30),
    price DECIMAL(19,4),
	due_date DATE NOT NULL,
	description TEXT,
	create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS material (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS item (
    id INT AUTO_INCREMENT NOT NULL,
    order_id INT NOT NULL,
    material_id INT NOT NULL,
    width FLOAT NOT NULL,
    height FLOAT NOT NULL,
    depth FLOAT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    note TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id)
    REFERENCES `order`(id),
    FOREIGN KEY (material_id)
    REFERENCES material(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS operation (
    id INT AUTO_INCREMENT NOT NULL,
    item_id INT NOT NULL,
    name VARCHAR(30),
    status VARCHAR(30),
    PRIMARY KEY (id),
    FOREIGN KEY (item_id)
    REFERENCES item(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS attachment (
    id INT AUTO_INCREMENT NOT NULL,
    path VARCHAR(30) NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id)
    REFERENCES `order`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
