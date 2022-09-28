DROP DATABASE IF EXISTS ea_agile_sprint_FlorianB;

#Change username to yours.
CREATE DATABASE ea_agile_sprint_FlorianB;

USE ea_agile_sprint_FlorianB;

CREATE TABLE `Role` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title` varchar(50),
    `description` varchar(2048),
    `link` varchar(512),
    `capability` ENUM('Engineering', 'Platforms', 'Data', 'Artificial Intelligence', 'Cyber Security', 'Workday', 'Experience Design', 'Product', 'Delivery', 'Operations', 'Business Development and Marketing', 'Organisational Strategy and Planning', 'People', 'Commercial and Financial Management', 'Business Services Support'),
    `band_level` ENUM('Principal', 'Manager', 'Consultant', 'Senior Associate', 'Associate', 'Trainee', 'Apprentice')
);

INSERT INTO `Role` (`title`, `description`, `link`, `capability`, `band_level`) VALUES ('Software Engineer',
'As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1',
'Engineering', 'Trainee');
INSERT INTO `Role` (`title`, `description`, `link`, `capability`, `band_level`) VALUES ('Technical Architect', 'As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, with room to learn, develop and grow.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1', 'Engineering', 'Consultant');
INSERT INTO `Role` (`title`, `description`, `link`, `capability`, `band_level`) VALUES ('Data Solution Architect', 'As a Data Solution Architect (Manager) in Kainos, you’ll be responsible for a multi- skilled agile teams to design and deliver contemporary data solutions. You will be a quality orientated pragmatist, where you balance trade-offs to successfully deliver complex solutions. You will be viewed as an authority figure for data technology solutions, providing strong technical and thought leadership.','https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FData%2FJob%20profile%20%2D%20Data%20Solution%20Architect%20%28M%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FData&p=true&ga=1', 'Data', 'Manager');

