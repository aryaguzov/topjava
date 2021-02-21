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

INSERT INTO meals(date_time, description, calories, user_id)
VALUES ('2021-02-18 12:00', 'Обед', 1000, 100000),
       ('2021-02-18 18:00', 'Ужин', 250, 100001),
       ('2021-02-19 08:00', 'Завтрак', 200, 100000),
       ('2021-02-19 08:30', 'Завтрак', 200, 100001),
       ('2021-02-20 10:00', 'Ужин', 200, 100000),
       ('2021-02-20 16:00', 'Полдник', 200, 100001);
