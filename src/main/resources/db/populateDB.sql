DELETE FROM user_roles;
DELETE FROM voting;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM restaurants;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('Admin', 'admin@gmail.com', '{noop}admin'),
  ('UserDelete', 'delete@gmail.com', '{noop}delete');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);


INSERT INTO restaurants (name)
VALUES ('Italian food'),
       ('Cheburek'),
       ('Peking duck'),
       ('Canteen'),
       ('Japan'),
       ('restaurant_without_menu'),
       ('restaurant_for_deleting');

INSERT INTO meals (name)
VALUES ('buckwheat porridge with mushrooms'),
       ('rice porridge with vegetables'),
       ('noodle soup'),
       ('greek salad'),
       ('sushi'),
       ('wasabi'),
       ('tea'),
       ('cheburek'),
       ('peking duck'),
       ('cutlet'),
       ('bread'),
       ('coffee'),
       ('spaghetti'),
       ('lagman'),
       ('meal_for_deleting');

INSERT INTO menu (restaurant_id,meal_id,date_menu,price)
VALUES ((SELECT id FROM restaurants WHERE name='Italian food'),(SELECT id FROM meals WHERE name='spaghetti'),CURRENT_DATE - INTERVAL '1' DAY,200),
       ((SELECT id FROM restaurants WHERE name='Italian food'),(SELECT id FROM meals WHERE name='cutlet'),CURRENT_DATE - INTERVAL '1' DAY,200),
       ((SELECT id FROM restaurants WHERE name='Cheburek'),(SELECT id FROM meals WHERE name='cheburek'),CURRENT_DATE - INTERVAL '1' DAY,70),
       ((SELECT id FROM restaurants WHERE name='Cheburek'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE - INTERVAL '1' DAY,30),
       ((SELECT id FROM restaurants WHERE name='Peking duck'),(SELECT id FROM meals WHERE name='peking duck'),CURRENT_DATE - INTERVAL '1' DAY,500),
       ((SELECT id FROM restaurants WHERE name='Peking duck'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE - INTERVAL '1' DAY,29),
       ((SELECT id FROM restaurants WHERE name='Canteen'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE - INTERVAL '1' DAY,60),
       ((SELECT id FROM restaurants WHERE name='Canteen'),(SELECT id FROM meals WHERE name='noodle soup'),CURRENT_DATE - INTERVAL '1' DAY,150),
       ((SELECT id FROM restaurants WHERE name='Japan'),(SELECT id FROM meals WHERE name='sushi'),CURRENT_DATE - INTERVAL '1' DAY,400),
       ((SELECT id FROM restaurants WHERE name='Japan'),(SELECT id FROM meals WHERE name='wasabi'),CURRENT_DATE - INTERVAL '1' DAY,100),
       ((SELECT id FROM restaurants WHERE name='Italian food'),(SELECT id FROM meals WHERE name='spaghetti'),CURRENT_DATE,201),
       ((SELECT id FROM restaurants WHERE name='Italian food'),(SELECT id FROM meals WHERE name='cutlet'),CURRENT_DATE,201),
       ((SELECT id FROM restaurants WHERE name='Cheburek'),(SELECT id FROM meals WHERE name='cheburek'),CURRENT_DATE,71),
       ((SELECT id FROM restaurants WHERE name='Cheburek'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE,31),
       ((SELECT id FROM restaurants WHERE name='Peking duck'),(SELECT id FROM meals WHERE name='peking duck'),CURRENT_DATE,501),
       ((SELECT id FROM restaurants WHERE name='Peking duck'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE,31),
       ((SELECT id FROM restaurants WHERE name='Canteen'),(SELECT id FROM meals WHERE name='tea'),CURRENT_DATE,61),
       ((SELECT id FROM restaurants WHERE name='Canteen'),(SELECT id FROM meals WHERE name='noodle soup'),CURRENT_DATE,151),
       ((SELECT id FROM restaurants WHERE name='Japan'),(SELECT id FROM meals WHERE name='sushi'),CURRENT_DATE,401),
       ((SELECT id FROM restaurants WHERE name='Japan'),(SELECT id FROM meals WHERE name='wasabi'),CURRENT_DATE,101);


INSERT INTO voting (user_id,date_voting,restaurant_id)
VALUES ((SELECT id FROM users WHERE name='Admin'),CURRENT_DATE - INTERVAL '1' DAY,(SELECT id FROM restaurants WHERE name='Cheburek')),
       ((SELECT id FROM users WHERE name='User'),CURRENT_DATE - INTERVAL '1' DAY,(SELECT id FROM restaurants WHERE name='Peking duck'));

INSERT INTO date_list (date)
VALUES ('2019-07-01'),
       ('2019-07-02'),
       ('2019-07-03'),
       ('2019-07-04'),
       ('2019-07-05'),
       ('2019-07-06'),
       ('2019-07-07'),
       ('2019-07-08'),
       ('2019-07-09'),
       ('2019-07-10'),
       ('2019-07-11'),
       ('2019-07-12'),
       ('2019-07-13'),
       ('2019-07-14'),
       ('2019-07-15'),
       ('2019-07-16'),
       ('2019-07-17'),
       ('2019-07-18'),
       ('2019-07-19'),
       ('2019-07-20'),
       ('2019-07-21'),
       ('2019-07-22'),
       ('2019-07-23'),
       ('2019-07-24'),
       ('2019-07-25'),
       ('2019-07-26'),
       ('2019-07-27'),
       ('2019-07-28'),
       ('2019-07-29'),
       ('2019-07-30'),
       ('2019-07-31'),
       ('2019-08-01'),
       ('2019-08-02'),
       ('2019-08-03'),
       ('2019-08-04'),
       ('2019-08-05'),
       ('2019-08-06'),
       ('2019-08-07'),
       ('2019-08-08'),
       ('2019-08-09'),
       ('2019-08-10'),
       ('2019-08-11'),
       ('2019-08-12'),
       ('2019-08-13'),
       ('2019-08-14'),
       ('2019-08-15'),
       ('2019-08-16'),
       ('2019-08-17'),
       ('2019-08-18'),
       ('2019-08-19'),
       ('2019-08-20'),
       ('2019-08-21');
