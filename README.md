## Script para criação de tabelas

```sql 
CREATE DATABASE currency_converter;
USE currency_converter;

CREATE TABLE kingdoms (
    kingdom_id      INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL,
    race            VARCHAR(50)  NOT NULL,
    specialty  VARCHAR(50)  NOT NULl
);

CREATE TABLE currencies (
            currency_id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE products_by_kingdoms (
    product_kingdom_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    value DECIMAL (10, 2) NOT NULL,
    origin_currency_id INT NOT NULL,
    product_conversion_rate DECIMAL (10, 2) NOT NULL DEFAULT 0.0,
    FOREIGN KEY (product_id) REFERENCES products (product_id),
    FOREIGN KEY (kingdom_id) REFERENCES kingdoms (kingdom_id),
    FOREIGN KEY (origin_currency_id) REFERENCES currencies (currency_id),
    CONSTRAINT UNIQUE (product_id,kingdom_id,origin_currency_id)
);

CREATE TABLE currency_conversion_rate (
    currency_conversion_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    origin_currency_id INT NOT NULL,
    destiny_currency_id INT NOT NULL,
    date DATE,
    conversion_rate DECIMAL (10, 2),

    FOREIGN KEY (origin_currency_id) REFERENCES currencies (currency_id),
    FOREIGN KEY (destiny_currency_id) REFERENCES currencies (currency_id),
    CONSTRAINT UNIQUE (origin_currency_id,destiny_currency_id,date)
);

CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    product_kingdom_id INT NOT NULL,
    destiny_currency_id INT NOT NULL,
    transaction_date DATE NOT NULL,
    transaction_type VARCHAR(255),
    product_quantity INT NOT NULL,

    FOREIGN KEY (product_kingdom_id) REFERENCES products_by_kingdoms (product_kingdom_id),
    FOREIGN KEY (destiny_currency_id) REFERENCES currencies (currency_id)

);


INSERT INTO currencies (name) VALUES ('OURO REAL');
INSERT INTO currencies (name) VALUES ('TIBAR');

INSERT INTO kingdoms (name, race, specialty) VALUES
    ('Khazad-dûm', 'Anão', 'Forja'),
    ('Minas Tirith', 'Humano', 'Estratégia'),
    ('Isengard', 'Orc', 'Engenharia de Guerra'),
    ('Alderaan', 'Humano', 'Diplomacia'),
    ('Rohan', 'Humano', 'Cavalaria');

INSERT INTO products (name, description) VALUES
    ('Espada Élfica', 'Lâmina forjada pelos elfos, leve e afiada.'),
    ('Machado Anão', 'Mechado confiável utilizado pelos anões me guerra.'),
    ('Silmarill?', 'Não sei Rick, parece falso...'),
    ('Armadura de Gondor', 'Proteção resistente usada pelos guardas de Minas Tirith.'),
    ('Machado de Guerra', 'Machado bruto forjado em Isengard.'),
    ('Sabre de Luz Azul', 'Arma de energia usada por cavaleiros da paz.'),
    ('Lança de Rohan', 'Arma longa usada pela cavalaria de Rohan.');

INSERT INTO products_by_kingdoms (product_id, kingdom_id, value, origin_currency_id, product_conversion_rate) VALUES
    (2, 1, 1500.00, 1, 0.0),
    (4, 2, 1000.00, 1, 0.0),
    (1, 2, 800.00, 1, 0.0),
    (5, 3, 600.00, 2, 0.0),
    (6, 4, 2000.00, 1, 0.0),
    (7, 5, 400.00, 2, 0.0),
    (4, 5, 900.00, 2, 0.0);

INSERT INTO currency_conversion_rate (origin_currency_id, destiny_currency_id, date, conversion_rate) VALUES
    (1, 2, '2025-04-01', 0.40);

-- Ouro Real → Tibar
INSERT INTO currency_conversion_rate (origin_currency_id, destiny_currency_id, date, conversion_rate) VALUES
    (1, 2, '2025-04-11', 2.48),
    (1, 2, '2025-04-12', 2.51),
    (1, 2, '2025-04-13', 2.50),
    (1, 2, '2025-04-14', 2.47),
    (1, 2, '2025-04-15', 2.52),
    (1, 2, '2025-04-16', 2.49),
    (1, 2, '2025-04-17', 2.53),
    (1, 2, '2025-04-18', 2.50),
    (1, 2, '2025-04-19', 2.54);

-- Tibar → Ouro Real (inverso das taxas acima, arredondado)
INSERT INTO currency_conversion_rate (origin_currency_id, destiny_currency_id, date, conversion_rate) VALUES
    (2, 1, '2025-04-11', 0.40),
    (2, 1, '2025-04-12', 0.40),
    (2, 1, '2025-04-13', 0.40),
    (2, 1, '2025-04-14', 0.41),
    (2, 1, '2025-04-15', 0.40),
    (2, 1, '2025-04-16', 0.40),
    (2, 1, '2025-04-17', 0.40),
    (2, 1, '2025-04-18', 0.40),
    (2, 1, '2025-04-19', 0.39);