## Script para criação de tabelas

```sql 
CREATE TABLE kingdom (
    kingdom_id              INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL,
    race            VARCHAR(50)  NOT NULL,
    specialization  VARCHAR(50)  NOT NULL
);
CREATE TABLE product (
    product_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    kingdom_id INT,
    PRIMARY KEY (product_id, kingdom_id),
    FOREIGN KEY (kingdom_id) REFERENCES kingdom (kingdom_id)
);

CREATE TABLE currency (
    currency_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    value DECIMAL (10,2) NOT NULL
);

create table currency_conversion (
    currency_convertion_id INT PRIMARY KEY AUTO_INCREMENT,
    origin_currency_id INT NOT NULL,
    destiny_currency_id INT NOT NULL,
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    conversion_rate DECIMAL (10, 2) NOT NULL,
    date DATETIME NOT NULL,

    FOREIGN KEY (origin_currency_id) REFERENCES currency(currency_id),
    FOREIGN KEY (destiny_currency_id) REFERENCES currency(currency_id)
);

CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_date DATETIME NOT NULL,
    total_value DECIMAL (10,2) NOT NULL
);

CREATE TABLE product_transaction (
    product_transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_id INT NOT NULL,
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    origin_currency INT NOT NULL,
    destiny_currency INT NOT NULL,
    quantity INT NOT NULL,
    total_value DECIMAL (10, 2) NOT NULL,

    FOREIGN KEY (transaction_id) REFERENCES transaction (transaction_id),
    FOREIGN KEY (product_id, kingdom_id) REFERENCES product(product_id, kingdom_id),
    FOREIGN KEY (origin_currency) REFERENCES currency (currency_id),
    FOREIGN KEY (destiny_currency) REFERENCES  currency (currency_id)
);
