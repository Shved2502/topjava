DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (date_trunc('minute', TIMESTAMP'2020-01-30 10:00:10'), 'Завтрак', 500, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-30 13:00:10'), 'Обед', 1000, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-30 20:00:10'), 'Ужин', 500, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-31 00:00:10'), 'Граничное значение', 100, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-31 10:00:10'), 'Завтрак', 1000, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-31 13:00:10'), 'Обед', 500, 100000),
       (date_trunc('minute', TIMESTAMP'2020-01-31 20:00:10'), 'Ужин', 410, 100000);