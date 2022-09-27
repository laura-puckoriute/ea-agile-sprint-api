DROP DATABASE IF EXISTS ea_agile_sprint_FlorianB;

#Change username to yours.
CREATE DATABASE ea_agile_sprint_FlorianB;

USE ea_agile_sprint_FlorianB;

CREATE TABLE `Role` (
	`title` varchar(50),
    `description` varchar(2048),
    `capability` ENUM('Engineering', 'Platforms', 'Data', 'Artificial Intelligence', 'Cyber Security', 'Workday', 'Experience Design', 'Product', 'Delivery', 'Operations', 'Business Development and Marketing', 'Organisational Strategy and Planning', 'People', 'Commercial and Financial Management', 'Business Services Support'),
    `band_level` ENUM('Principal', 'Manager', 'Consultant', 'Senior Associate', 'Associate', 'Trainee', 'Apprentice')
);

