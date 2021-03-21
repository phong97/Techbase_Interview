/* generate manager data */
INSERT INTO `profile` (`id`,`first_name`, `last_name`, `date_of_birth`, `gender`, `phone_number`)
VALUES (2,'manager', '1', '2000-01-01', 0, '0123456789'),
       (3,'manager', '2', '2000-01-01', 0, '0123456789'),
       (4,'manager', '3', '2000-01-01', 0, '0123456789');

INSERT INTO `user` (`id`, `email`, `password`, `status`, `profile_id`)
VALUES (2,'manager001@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 2),
       (3,'manager002@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 3),
       (4,'manager003@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 4);

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES (2, 2),
       (3, 2),
       (4, 2);

/* generate department data */
INSERT INTO `department` (`id`, `name`, status, `manager_id`)
VALUES (1, 'Information Technology', 0, 2),
       (2, 'Sale', 0, 3),
       (3, 'Human Resourch', 0, 4);

/* generate member data */
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `date_of_birth`, `gender`, `phone_number` )
VALUES (5, 'member', '1', '2000-01-01', 1, '0123456789'),
       (6, 'member', '2', '2000-01-01', 1, '0123456789'),
       (7, 'member', '3', '2000-01-01', 1, '0123456789'),
       (8, 'member', '4', '2000-01-01', 1, '0123456789'),
       (9, 'member', '5', '2000-01-01', 1, '0123456789'),
       (10, 'member', '6', '2000-01-01', 1, '0123456789'),
       (11, 'member', '7', '2000-01-01', 1, '0123456789'),
       (12, 'member', '8', '2000-01-01', 1, '0123456789'),
       (13, 'member', '9', '2000-01-01', 1, '0123456789'),
       (14, 'member', '10', '2000-01-01', 1, '0123456789'),
       (15, 'member', '11', '2000-01-01', 1, '0123456789'),
       (16, 'member', '12', '2000-01-01', 1, '0123456789'),
       (17, 'member', '13', '2000-01-01', 1, '0123456789'),
       (18, 'member', '14', '2000-01-01', 1, '0123456789'),
       (19, 'member', '15', '2000-01-01', 1, '0123456789'),
       (20, 'member', '16', '2000-01-01', 1, '0123456789'),
       (21, 'member', '17', '2000-01-01', 1, '0123456789'),
       (22, 'member', '18', '2000-01-01', 1, '0123456789'),
       (23, 'member', '19', '2000-01-01', 1, '0123456789'),
       (24, 'member', '20', '2000-01-01', 1, '0123456789'),
       (25, 'member', '21', '2000-01-01', 1, '0123456789');

INSERT INTO `user` (`id`, `email`, `password`, `status`, `profile_id`)
VALUES (5, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 5),
       (6, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 6),
       (7, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 7),
       (8, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 8),
       (9, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 9),
       (10, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 10),
       (11, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 11),
       (12, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 12),
       (13, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 13),
       (14, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 14),
       (15, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 15),
       (16, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 16),
       (17, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 17),
       (18, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 18),
       (19, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 19),
       (20, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 20),
       (21, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 21),
       (22, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 22),
       (23, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 23),
       (24, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 24),
       (25, 'member@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 25);


INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES (5, 3),
       (6, 3),
       (7, 3),
       (8, 3),
       (9, 3),
       (10, 3),
       (11, 3),
       (12, 3),
       (13, 3),
       (14, 3),
       (15, 3),
       (16, 3),
       (17, 3),
       (18, 3),
       (19, 3),
       (20, 3),
       (21, 3),
       (22, 3),
       (23, 3),
       (24, 3),
       (25, 3);

/* generate team data */
INSERT INTO `team` (`id`, `name`, `status`, `department_id`)
VALUES (1, 'JAVA', 0, 1),
       (2, '.NET', 0, 1),
       (3, 'Sales', 0, 2),
       (4, 'HR', 0, 3);

INSERT INTO `team_user` (`team_id`, `user_id`) VALUES
(1, 8),
(1, 9),
(1, 10),
(2, 11),
(2, 12),
(2, 13),
(3, 8),
(3, 7),
(3, 6),
(4, 20),
(4, 15),
(4, 17);