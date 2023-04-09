DELETE
FROM gift_certificate
WHERE gift_certificate_id > -1;

DELETE
FROM tag
WHERE tag_id > -1;

INSERT INTO gift_certificate
VALUES (1, 'name', 'description', 123312.12, now(), now(), '40 0:00:00'),
       (2, 'cosmetics', 'gift certificate for cosmetics stores', 150.15, now(), now(), '15 00:00:00'),
       (3, 'auto', 'gift certificate for car service stations', 1000, '2018-08-29T06:12:15.156', now(), '90 0:00:00'),
       (4, 'flower', 'gift certificate for flower shops', 75.8, now(), now(), '10 0:00:00'),
       (5, 'cloth', 'gift certificate for clothing stores', 100, '2015-04-15T06:10:15.156', now(), '45 0:00:00');

INSERT INTO tag
VALUES (1, '01 april'),
       (2, '21 april'),
       (3, '01 may'),
       (4, '9 may'),
       (5, '1 september'),
       (6, '1 january'),
       (7, '14 february'),
       (8, '8 march');

INSERT INTO certificate_tag
VALUES (1, 1),
       (1, 2),
       (2, 7),
       (2, 8),
       (3, 1),
       (3, 5),
       (3, 6),
       (4, 4),
       (4, 5),
       (4, 7),
       (4, 8),
       (5, 2),
       (5, 5),
       (5, 6),
       (5, 7),
       (5, 8);