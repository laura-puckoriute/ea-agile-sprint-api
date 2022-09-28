DROP DATABASE IF EXISTS ea_agile_sprint_FlorianB;

#Change username to yours.
CREATE DATABASE ea_agile_sprint_FlorianB;

USE ea_agile_sprint_FlorianB;

CREATE TABLE `Role` (
	`id` smallint PRIMARY KEY,
	`title` varchar(50),
    `description` varchar(2048),
    `capability` ENUM('Engineering', 'Platforms', 'Data', 'Artificial Intelligence', 'Cyber Security', 'Workday', 'Experience Design', 'Product', 'Delivery', 'Operations', 'Business Development and Marketing', 'Organisational Strategy and Planning', 'People', 'Commercial and Financial Management', 'Business Services Support'),
    `band_level` ENUM('Principal', 'Manager', 'Consultant', 'Senior Associate', 'Associate', 'Trainee', 'Apprentice')
);

INSERT INTO `Role` VALUES (1, 'Software Engineer', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1', 'Engineering', 'Trainee');
INSERT INTO `Role` VALUES (2, 'Technical Architect', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1', 'Engineering', 'Consultant');
INSERT INTO `Role` VALUES (3, 'Data Solution Architect', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FData%2FJob%20profile%20%2D%20Data%20Solution%20Architect%20%28M%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FData&p=true&ga=1', 'Data', 'Manager');


