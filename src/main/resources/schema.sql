CREATE TABLE IF NOT EXISTS company (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name_description VARCHAR(100),
     create_at DATE DEFAULT CURRENT_DATE,
     update_at DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS product(
    id INT AUTO_INCREMENT,
    name_description VARCHAR(100),
    code INT,
    stock INT,
    PRIMARY KEY (code)
);

CREATE TABLE IF NOT EXISTS prices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    price_list INT,
    product_id INT,
    priority INT,
    price DECIMAL(10, 2),
    currency VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product(code),
    FOREIGN KEY (company_id) REFERENCES company(id)
);