## Script para criação de tabelas

```sql 
CREATE DATABASE currency_converter;
USE currency_converter;

CREATE TABLE kingdom (
    kingdom_id      INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL,
    race            VARCHAR(50)  NOT NULL,
    specialty  VARCHAR(50)  NOT NULl
);

CREATE TABLE currency (
                          currency_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          base_value DECIMAL (10,2)

);

CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE product_kingdom (
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    FOREIGN KEY (product_id) references product (product_id),
    FOREIGN KEY (kingdom_id) references kingdom (kingdom_id),
    PRIMARY KEY (product_id, kingdom_id)
);

CREATE TABLE product_value (
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    origin_currency INT NOT NULL,
    value DECIMAL (10, 2) NOT NULL,
    date DATE,

    PRIMARY KEY (product_id, kingdom_id, date),
    FOREIGN KEY (product_id) references product (product_id),
    FOREIGN KEY (kingdom_id) references kingdom (kingdom_id),
    FOREIGN KEY (origin_currency) references currency (currency_id)
);



CREATE TABLE currency_by_product_conversion (
    currency_convertion_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    kingdom_id INT NOT NULL,
    product_conversion_rate DECIMAL (10, 2) NOT NULL,
    date DATETIME NOT NULL,

    FOREIGN KEY (product_id, kingdom_id) REFERENCES product_kingdom (product_id, kingdom_id)
);

CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_date DATETIME NOT NULL,
    transaction_type VARCHAR(255),
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
    FOREIGN KEY (product_id, kingdom_id) REFERENCES product_kingdom (product_id, kingdom_id),
    FOREIGN KEY (origin_currency) REFERENCES currency (currency_id),
    FOREIGN KEY (destiny_currency) REFERENCES  currency (currency_id)
);

INSERT INTO currency (name, base_value) VALUES ('OURO REAL', 1.00);
INSERT INTO currency (name, base_value) VALUES ('TIBAR', 2.5);

INSERT INTO kingdom (name, race, specialty) VALUES
                                                     ('Khazad-dûm', 'Anão', 'Forja'),
                                                     ('Minas Tirith', 'Humano', 'Estratégia'),
                                                     ('Isengard', 'Orc', 'Engenharia de Guerra'),
                                                     ('Alderaan', 'Humano', 'Diplomacia'),
                                                     ('Rohan', 'Humano', 'Cavalaria');

INSERT INTO product (name, description) VALUES
                                            ('Espada Élfica', 'Lâmina forjada pelos elfos, leve e afiada.'),
                                            ('Machado Anão', 'Mechado confiável utilizado pelos anões me guerra.'),
                                            ('Silmarill?', 'Não sei Rick, parece falso...'),
                                            ('Armadura de Gondor', 'Proteção resistente usada pelos guardas de Minas Tirith.'),
                                            ('Machado de Guerra', 'Machado bruto forjado em Isengard.'),
                                            ('Sabre de Luz Azul', 'Arma de energia usada por cavaleiros da paz.'),
                                            ('Lança de Rohan', 'Arma longa usada pela cavalaria de Rohan.');

INSERT INTO product_kingdom (product_id, kingdom_id) VALUES
                                                         (1, 1),
                                                         (1, 2),
                                                         (2, 2),
                                                         (2, 5),
                                                         (3, 3),
                                                         (3, 2),
                                                         (4, 4),
                                                         (5, 5),
                                                         (5, 1);

INSERT INTO product_value (product_id, kingdom_id, origin_currency, value, date) VALUES
                                                                                     (1, 1, 1, 800.00, '2025-04-01'),
                                                                                     (1, 1, 1, 99.99, '2025-04-18'),
                                                                                     (1, 1, 1, 500.00, '2025-04-17'),
                                                                                     (1, 2, 2, 320.00, '2025-04-01'),
                                                                                     (1, 2, 2, 200.00, '2025-04-08'),
                                                                                     (2, 2, 1, 1000.00, '2025-04-01'),
                                                                                     (2, 5, 2, 400.00, '2025-04-01'),
                                                                                     (3, 3, 2, 600.00, '2025-04-01'),
                                                                                     (3, 2, 1, 1500.00, '2025-04-01'),
                                                                                     (4, 4, 1, 2000.00, '2025-04-01'),
                                                                                     (5, 5, 2, 500.00, '2025-04-01'),
                                                                                     (5, 1, 1, 1250.00, '2025-04-01');

INSERT INTO currency_by_product_conversion (product_id, kingdom_id, product_conversion_rate, date) VALUES
                                                                                                       (1, 1, 1.00, '2025-04-01 09:00:00'),
                                                                                                       (1, 2, 0.40, '2025-04-01 09:00:00'),
                                                                                                       (2, 2, 1.00, '2025-04-01 09:00:00'),
                                                                                                       (2, 5, 0.40, '2025-04-01 09:00:00'),
                                                                                                       (3, 3, 0.40, '2025-04-01 09:00:00'),
                                                                                                       (3, 2, 1.00, '2025-04-01 09:00:00'),
                                                                                                       (4, 4, 1.00, '2025-04-01 09:00:00'),
                                                                                                       (5, 5, 0.40, '2025-04-01 09:00:00'),
                                                                                                       (5, 1, 1.00, '2025-04-01 09:00:00');


