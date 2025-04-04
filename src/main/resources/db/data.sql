INSERT INTO publications (title, description, price) VALUES
('Tech Magazine', 'A magazine about technology and innovations', 19.99),
('Sports Weekly', 'A weekly publication about sports', 15.50);

INSERT INTO subscriptions (publication_id, subscriber_name, start_date, end_date) VALUES
(1, 'John Doe', '2025-01-01', '2025-12-31'),
(2, 'Jane Smith', '2025-03-01', '2025-08-31');

INSERT INTO payments (subscription_id, amount) VALUES
(1, 19.99),
(2, 15.50);
