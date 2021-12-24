INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.com', '{noop}password'),
       ('Admin', 'admin@mail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (name)
VALUES ('First'),
       ('Second');

INSERT INTO menus (dish_name, price, restaurant_id)
VALUES ('breakfast', 10, 1),
       ('launch', 20, 1),
       ('dinner', 30, 1),
       ('one breakfast', 10, 2),
       ('one launch', 20, 2),
       ('one dinner', 30, 2);

INSERT INTO votes(restaurant_id, created, user_id)
VALUES (1, 'now()', 1),
       (1, 'now()', 2),
       (2, 'now()', 1);
