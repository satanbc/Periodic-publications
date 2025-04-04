CREATE TABLE publications (
                              id SERIAL PRIMARY KEY,
                              title VARCHAR(255) NOT NULL,
                              description TEXT,
                              price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE subscriptions (
                               id SERIAL PRIMARY KEY,
                               publication_id INT REFERENCES publications(id),
                               subscriber_name VARCHAR(255) NOT NULL,
                               start_date DATE NOT NULL,
                               end_date DATE NOT NULL
);

CREATE TABLE payments (
                          id SERIAL PRIMARY KEY,
                          subscription_id INT REFERENCES subscriptions(id),
                          amount DECIMAL(10, 2) NOT NULL,
                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
