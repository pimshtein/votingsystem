INSERT INTO users (id, name, email, password)
VALUES (1, 'User', 'user@yandex.ru', '{noop}password'),
       (2, 'Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (id, name)
VALUES (1, 'U Svety'),
       (2, 'U Natashi'),
       (3, 'U Iriny');

INSERT INTO menus (id, dish_name, price, restaurant_id)
VALUES (1, 'breakfast', 10, 1),
       (2, 'launch', 20, 1),
       (3, 'dinner', 30, 1),
       (4, 'one breakfast', 10, 2),
       (5, 'one launch', 20, 2),
       (6, 'one dinner', 30, 2),
       (7, 'two breakfast', 10, 3),
       (8, 'two launch', 20, 3);

INSERT INTO votes(id, restaurant_id, created, user_id)
VALUES (1, 1, 'now()', 1),
       (2, 1, 'now()', 2),
       (3, 2, 'now()', 1);
