DROP DATABASE IF EXISTS ea_agile_sprint_FlorianB;

#Change username to yours.
CREATE DATABASE ea_agile_sprint_FlorianB;

USE ea_agile_sprint_FlorianB;

CREATE TABLE `Capability` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(50)
);

CREATE TABLE `Band_Level` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(50)
);

CREATE TABLE `Job_Family` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(50)
);

CREATE TABLE `Competency` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(50)
);

CREATE TABLE `Responsibility` (
	`band_levelID` smallint,
    `competencyID` smallint,
    `responsibility_name` varchar(50),
    `description` varchar (500),
    FOREIGN KEY (`band_levelID`) REFERENCES `Band_Level`(`id`),
    FOREIGN KEY (`competencyID`) REFERENCES `Competency`(`id`)
);

CREATE TABLE `Role` (
	`id` smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title` varchar(50),
    `description` varchar(2048),
    `responsibilities` varchar(2048),
    `link` varchar(512),
    `capabilityID` smallint,
    `band_levelID` smallint,
    `job_familyID` smallint,
     FOREIGN KEY (`capabilityID`) REFERENCES `Capability`(`id`),
     FOREIGN KEY (`band_levelID`) REFERENCES `Band_Level`(`id`),
     FOREIGN KEY (`job_familyID`) REFERENCES `Job_Family`(`id`)
);

INSERT INTO `Job_Family` (`title`) VALUES ('Strategy and Planning');
INSERT INTO `Job_Family` (`title`) VALUES ('Engineering');
INSERT INTO `Job_Family` (`title`) VALUES ('HCM');
INSERT INTO `Job_Family` (`title`) VALUES ('Financials');
INSERT INTO `Job_Family` (`title`) VALUES ('Data');
INSERT INTO `Job_Family` (`title`) VALUES ('Integrations');
INSERT INTO `Job_Family` (`title`) VALUES ('Change & User Adoption');
INSERT INTO `Job_Family` (`title`) VALUES ('Adaptive Planning');
INSERT INTO `Job_Family` (`title`) VALUES ('Product Services');
INSERT INTO `Job_Family` (`title`) VALUES ('Product Development');
INSERT INTO `Job_Family` (`title`) VALUES ('Extend');
INSERT INTO `Job_Family` (`title`) VALUES ('Spend Management');

INSERT INTO `Capability` (`title`) VALUES ('Engineering');
INSERT INTO `Capability` (`title`) VALUES ('Platforms');
INSERT INTO `Capability` (`title`) VALUES ('Data');
INSERT INTO `Capability` (`title`) VALUES ('Artificial Intelligence');
INSERT INTO `Capability` (`title`) VALUES ('Cyber Security');
INSERT INTO `Capability` (`title`) VALUES ('Workday');
INSERT INTO `Capability` (`title`) VALUES ('Experience Design');
INSERT INTO `Capability` (`title`) VALUES ('Product');
INSERT INTO `Capability` (`title`) VALUES ('Delivery');
INSERT INTO `Capability` (`title`) VALUES ('Operations');
INSERT INTO `Capability` (`title`) VALUES ('Business Development and Marketing');
INSERT INTO `Capability` (`title`) VALUES ('Organisational Strategy and Planning');
INSERT INTO `Capability` (`title`) VALUES ('People');
INSERT INTO `Capability` (`title`) VALUES ('Commercial and Financial Management');
INSERT INTO `Capability` (`title`) VALUES ('Business Services Support');

INSERT INTO `Competency` (`title`) VALUES ('Personal Performance');
INSERT INTO `Competency` (`title`) VALUES ('Working With Others');
INSERT INTO `Competency` (`title`) VALUES ('Setting Direction, Development and Accountability');
INSERT INTO `Competency` (`title`) VALUES ('Supporting and Delivering the Strategy');
INSERT INTO `Competency` (`title`) VALUES ('Commerciality and Risk');
INSERT INTO `Competency` (`title`) VALUES ('Communication and Influence');


INSERT INTO `Band_Level` (`title`) VALUES ('Principal');
INSERT INTO `Band_Level` (`title`) VALUES ('Manager');
INSERT INTO `Band_Level` (`title`) VALUES ('Consultant');
INSERT INTO `Band_Level` (`title`) VALUES ('Senior Associate');
INSERT INTO `Band_Level` (`title`) VALUES ('Associate');
INSERT INTO `Band_Level` (`title`) VALUES ('Trainee');
INSERT INTO `Band_Level` (`title`) VALUES ('Apprentice');

INSERT INTO `Responsibility` VALUES (6, 1, 'Developing self-awareness', 'Understands others strengths and areas for development. Recognisingdiversity and its value within self andteam.Proactively uses wellbeing tools to support self-regulation.');
INSERT INTO `Responsibility` VALUES (6, 1, 'Managing yourself', 'Able to identify own goals and discusses these with People Manager. Understands the need to work conscientiously to achieve tasks on schedule.');
INSERT INTO `Responsibility` VALUES (6, 2, 'Mobilises self and othersto drive self-improvement', 'Shares learning with the team and othercolleagues, contributingto the team’s understanding.');
INSERT INTO `Responsibility` VALUES (6, 2, 'Champions continuous improvement', 'Maintainsconsistent performance, challengingself and others to be positive and focused on achieving results,despite setbacks.Support others to work in a way that ismutually supportive');

INSERT INTO `Role` (`title`, `description`, `link`, `responsibilities`,`capabilityID`, `band_levelID`, `job_familyID`) VALUES ('Software Engineer',
'•Completed or are currently studying a relevant third level IT qualification
•Familiar with some programming languages and implementation environments.
•Some understanding of the software development lifecycle from your studies or relevant work experience, and the relevance of different tools at different stages of the development lifecycle
•Able to make effective decisions with the support of team members, within fast-moving delivery environment.
•Have an open attitude to sharing knowledge and information.
•Ideally have some experience of working in a collaborative team environment
•Good communication skills with the ability to communicate issues to other technical people and, sometimes, non-technical people
•Good problem solving and analytical skills.
•We all work in teams here in Kainos – a proven ability of strong team skills, including taking direction from others, is crucial.
•Ability to carry out responsibilities in accordance with company policies, procedures and processes.•Ability to deliver tasks within a given timeframe. ',
'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1',
'As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.',
1, 6, 2);
INSERT INTO `Role` (`title`, `description`, `link`, `responsibilities`, `capabilityID`, `band_levelID`, `job_familyID`) VALUES ('Technical Architect',
'•Experience delivering software designs for multi-tiered modern software applications.
•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.
•Understands non-functional concerns for customers and has experience incorporating these into the application design.
•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.
•Able to simply and clearly communicate technical design in conversation, documentation and presentations.
•Able to make effective decisions within fast-moving delivery.
•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community. ',
'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1',
'As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, with room to learn, develop and grow.',
1, 3, 2);
INSERT INTO `Role` (`title`, `description`, `link`, `responsibilities`, `capabilityID`, `band_levelID`, `job_familyID`) VALUES ('Data Solution Architect',
'•Experience of technical ownership for a component, sub-system or product (data ingestion, data streaming), including architecture, estimation, product planning and story creation
•Proficient in client interaction including communication of technical decisions to non-technical audiences
•Experience of applying standards for design (patterns), development (style guides), and operational readiness (automation, deployment)
•Experience of designing, building and productionising modern, distributed data-intensive applications
•Experienced in Java, Scala or Python and comfortable with multiple programming paradigms (functional, OO)
•Experience of deploying contemporary data stores such as object stores, document stores, key-value store, search, RDBMS, graph.
•Software development experience with data-processing platforms from vendors such as AWS, Azure, GCP, Databricks
•Comfortable with data integration techniques such as messaging, queuing, change data capture or data virtualisation',
'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FData%2FJob%20profile%20%2D%20Data%20Solution%20Architect%20%28M%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FData&p=true&ga=1',
'As a Data Technical Architect (Consultant) in Kainos, you’ll be responsible for designing and delivering technical components as part of a larger data solution. You will work closely with Solution Architects and Customer Architects to integrate these components into quality data solutions Your responsibilities will include:
•Successfully implementing functional and non-functional designs
•Working closely with customer architects to agree functional and non-functional designs, including estimated effort, technical implications, and complexity.
•Setting technical standards and works with engineers to ensure standards are upheld
•Managing and estimating timelines underpinning technical component delivery
•Contributing or owning technical solution design as part of a pre-sales process
•Making a significant contribution to the data analysis community and wider data and analytics capability
•Managing, coaching and developing a small number of staff, with a focus on managing employee performance and assisting in their career development. You’ll also provide direction and leadership for your team as you solve challenging problems together',
3, 2, 3);

