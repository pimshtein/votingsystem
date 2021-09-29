INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.com', '{noop}password'),
       ('Admin', 'admin@mail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', (SELECT id FROM users WHERE email = 'user@mail.com')),
       ('ADMIN', (SELECT id FROM users WHERE email = 'admin@mail.com')),
       ('USER', (SELECT id FROM users WHERE email = 'admin@mail.com'));

INSERT INTO restaurants (name)
VALUES ('U Svety'),
       ('U Natashi'),
       ('U Iriny');

INSERT INTO menus (dish_name, price, restaurant_id)
VALUES ('breakfast', 10, (SELECT id FROM restaurants WHERE name = 'U Svety')),
       ('launch', 20, (SELECT id FROM restaurants WHERE name = 'U Svety')),
       ('dinner', 30, (SELECT id FROM restaurants WHERE name = 'U Svety')),
       ('one breakfast', 10, (SELECT id FROM restaurants WHERE name = 'U Natashi')),
       ('one launch', 20, (SELECT id FROM restaurants WHERE name = 'U Natashi')),
       ('one dinner', 30, (SELECT id FROM restaurants WHERE name = 'U Natashi')),
       ('two breakfast', 10, (SELECT id FROM restaurants WHERE name = 'U Iriny')),
       ('two launch', 20, (SELECT id FROM restaurants WHERE name = 'U Iriny'));

INSERT INTO votes(restaurant_id, created, user_id)
VALUES ((SELECT id FROM restaurants WHERE name = 'U Svety'), 'now()', (SELECT id FROM users WHERE email = 'user@mail.com')),
       ((SELECT id FROM restaurants WHERE name = 'U Svety'), 'now()', (SELECT id FROM users WHERE email = 'admin@mail.com')),
       ((SELECT id FROM restaurants WHERE name = 'U Natashi'), 'now()', (SELECT id FROM users WHERE email = 'user@mail.com'));
