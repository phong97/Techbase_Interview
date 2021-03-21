/* init role data */
INSERT INTO `role` (`name`) VALUES
('director'),
('manager'),
('staff');

/* init admin account */
INSERT INTO `profile` (`first_name`, `last_name`, `date_of_birth`, `gender`, `phone_number`) VALUES
('Techbase', 'Director', '2000-01-01', 0, '0123456789');

INSERT INTO `user` (`email`, `password`, `status`, `profile_id`) VALUES
('techbase@gmail.com', '$2y$10$5V6h8EQvr8.0DHd7gVEBHO7MVDKN708uVhN1utXR3gLu53iHhs0sW', 0, 1);

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1);