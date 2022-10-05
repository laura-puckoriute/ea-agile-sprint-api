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
    `responsibility_name` varchar(70),
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

# Competency 1: Personal Performance
# Principle
INSERT INTO `Responsibility` VALUES (1, 1, 'Developing self-awareness', 'Reflects on own interactions with a wide and diverse range of individuals and groups from within and beyond immediate service/organisation. Challenges and refreshes own values, beliefs, leadership styles and approaches. Overtly role models the giving and receiving of feedback.');
INSERT INTO `Responsibility` VALUES (1, 1, 'Managing yourself', 'Successfully manages a range of personal and organisational demands and pressures. Demonstrates tenacity and resilience. Overcomes setbacks where goals cannot be achieved and quickly refocuses. Is visible and accessible to others.');
INSERT INTO `Responsibility` VALUES (1, 1, 'Continuing personal development', 'Acts as an exemplar for others in managing continuous personal development. Facilitates the development of a learning culture. Identifies tangible, objective business improvements and benefits when planning development activities and identifies and tracks these through metrics, to demonstrate impact on business results.');
INSERT INTO `Responsibility` VALUES (1, 1, 'Acting with integrity', 'Creates an open, honest, and inclusive culture in accordance with clear principles and values. Ensures equity of access to services and creates an environment where people from all backgrounds can excel. ');

# Manager
INSERT INTO `Responsibility` VALUES (2, 1, 'Developing self-awareness', 'Reflects on how factors such as own values, prejudices and emotions influences own judgement, behaviour, and self-belief. Uses feedback from appraisals and other sources to consider personal impact and changes behaviour. Understands personal sources of stress and wellbeing.');
INSERT INTO `Responsibility` VALUES (2, 1, 'Managing yourself', 'Plans and manages own time effectively and fulfils work requirements and commitments to a high standard, without compromising own health and wellbeing. Remains calm and focused under pressure. ');
INSERT INTO `Responsibility` VALUES (2, 1, 'Continuing personal development', 'Proactively manages self and career and identifies personal learning needs plan and completes a broad range of formal and informal learning opportunities by taking responsibility for own personal development and seeking opportunities for learning. Strives to put learning into practice. Clearly demonstrates that leadership and technical skills are equally valued. ');
INSERT INTO `Responsibility` VALUES (2, 1, 'Acting with integrity', 'Behaves in an open, honest, and inclusive manner, upholding personal and organisational ethics and values. Shows respect for the needs of others and promotes equality and diversity.');

INSERT INTO `Responsibility` VALUES (3, 1, 'Developing self-awareness', 'Understands own personal preferences, biases and values different cultures, backgrounds and circumstances in decision making and takes actions.Champions Kainos wellbeing offerings and provides direction to the various wellbeing guides and support available for our people.');
INSERT INTO `Responsibility` VALUES (3, 1, 'Managing yourself', 'Consistently sets own goals and manages this independently. Making autonomous decisions and are able to ‘get on with the job’ escalating decisions only when appropriate.');
INSERT INTO `Responsibility` VALUES (3, 1, 'Continuing personal development', 'Identifies and addresses team or individual capability requirements and gaps to deliver current and future work. Consistently identifies and develops self and others to support talent development.');
INSERT INTO `Responsibility` VALUES (3, 1, 'Acting with integrity', 'Demonstrates professional and organisational values through actions and behaviours. Behaves in an inclusive manner and respects equality and diversity.');

INSERT INTO `Responsibility` VALUES (4, 1, 'Developing self-awareness', 'Understands and confidently articulates own learning and developmental needs and proactively seek opportunities to gain experience. Has a strong level of self-awareness and in tune with own wellbeing needs and intuitive of others.');
INSERT INTO `Responsibility` VALUES (4, 1, 'Managing yourself', 'Seeks and responds positively to feedback regarding own learning and development. Approach with a willingness to take on tasks that are outside role scope.');
INSERT INTO `Responsibility` VALUES (4, 1, 'Continuing personal development', 'Looks for opportunities to take on new challenges while proactively supporting and encouraging others in identifying learning needs.');
INSERT INTO `Responsibility` VALUES (4, 1, 'Acting with integrity', 'Recognises inappropriate behaviours and challenges constructively while promoting the Kainos values. Adapting behaviours and acting in the most appropriate way.');

INSERT INTO `Responsibility` VALUES (5, 1, 'Developing self-awareness', 'Seeks out new challenges and opportunities that may stretch current abilities. Builds on own self-awareness of overall wellbeing.');
INSERT INTO `Responsibility` VALUES (5, 1, 'Managing yourself', 'Recognises the need for SMART goals, and demonstrates a “can do” attitude, through having own internal standards of performance.');
INSERT INTO `Responsibility` VALUES (5, 1, 'Continuing personal development', 'Seeks out opportunities to improve skills beyond the role scope whilst also seeking regular feedback.');
INSERT INTO `Responsibility` VALUES (5, 1, 'Acting with integrity', 'Encourages positive behaviours in others, while role modelling the Kainos values.');

INSERT INTO `Responsibility` VALUES (6, 1, 'Developing self-awareness', 'Understands others strengths and areas for development. Recognising diversity and its value within self and team. Proactively uses wellbeing tools to support self-regulation.');
INSERT INTO `Responsibility` VALUES (6, 1, 'Managing yourself', 'Able to identify own goals and discusses these with People Manager. Understands the need to work conscientiously to achieve tasks on schedule.');
INSERT INTO `Responsibility` VALUES (6, 1, 'Continuing personal development', 'Identifies learning gaps and seeks out opportunities to improve skills. Open to developmental feedback from others.');
INSERT INTO `Responsibility` VALUES (6, 1, 'Acting with integrity', 'Demonstrates positive behaviours when dealing with others and ensures application of the values while working and representing Kainos to our customers.');

INSERT INTO `Responsibility` VALUES (7, 1, 'Developing self-awareness', 'Understands own strengths and areas of development. Self-aware of own wellbeing and seeks support where appropriate.');
INSERT INTO `Responsibility` VALUES (7, 1, 'Managing yourself', 'Works with People Manager to sets and achieve goals by monitoring progress regularly against performance.');
INSERT INTO `Responsibility` VALUES (7, 1, 'Continuing personal development', 'Flexible and willingness to learn on the job while proactively keeping up to date with the knowledge and skills needed.');
INSERT INTO `Responsibility` VALUES (7, 1, 'Acting with integrity', 'Understands the company values and applies this to own principles. Is open and honest and acts respectfully with others and customers.');

# Competency 2: Working with Others
# Principle
INSERT INTO `Responsibility` VALUES (1, 2, 'Mobilises self and others to drive self-improvement', 'Motivates and coaches’ individuals and teams to strengthen their performance and assist with developing their own capabilities and skills. Aligns individual development needs with service goals.');
INSERT INTO `Responsibility` VALUES (1, 2, 'Champions continuous improvement', 'Integrates the contributions of a diverse range of stakeholders, being open and honest about the extent to which contributions can be based upon. Challenges others to provide stronger feedback and drive continuous improvement.');
INSERT INTO `Responsibility` VALUES (1, 2, 'Managing performance', 'Establishes rigorous performance measures. Holds self, individuals, and teams to account for achieving performance standards. Challenges when service expectations are not being met and takes corrective action.');
INSERT INTO `Responsibility` VALUES (1, 2, 'Developing networks and building and maintaining relationships', 'Identifies and builds effective networks with a range of influential stakeholders internal and external to the organisation. Builds and nurtures trusting relationships at all levels within and across services and organisational boundaries.');
INSERT INTO `Responsibility` VALUES (1, 2, 'Working within teams', 'Takes on recognised leadership roles within the organisation. Builds high performing inclusive teams that contribute to productivity and efficiencies. Promotes autonomy and empowerment and maintains a sense of optimism and confidence.');

# Manager
INSERT INTO `Responsibility` VALUES (2, 2, 'Mobilises self and others to drive self-improvement', 'Supports others in delivering high quality services and excellence by supporting mobilising teams and contributing to business improvement.');
INSERT INTO `Responsibility` VALUES (2, 2, 'Champions continuous improvement', 'Seeks and acknowledges the views and inputs of others. Shows respect for the contributions and challenges of others through positive and constructive feedback.');
INSERT INTO `Responsibility` VALUES (1, 2, 'Managing performance', 'Uses information and data about performance to identify improvements which will strengthen services.');
INSERT INTO `Responsibility` VALUES (2, 2, 'Developing networks and building and maintaining relationships', 'Identifies where working and cooperating with others can result in better services. Endeavours to work collaboratively. Communicates with and listens to others, recognising different perspectives. Empathises and considers the needs and feelings of others. Gains and maintains trust and support.');
INSERT INTO `Responsibility` VALUES (2, 2, 'Working within teams', 'Understands roles, responsibilities, and purpose within the team. Adopts a collaborative approach and respects team decisions.');

INSERT INTO `Responsibility` VALUES (3, 2, 'Mobilises self and others to drive self-improvement', 'Support and empower team members through a range of activities to include coaching and mentoring. Demonstrate that leadership and technical skills are equally valued.');
INSERT INTO `Responsibility` VALUES (3, 2, 'Champions continuous improvement', 'Role model continuous learning and self-development, evaluating own effectiveness and growth. Motivate others to achieve through challenging times. Regularly monitor and discuss own and team’s performance expectations defined within the performance management system.');
INSERT INTO `Responsibility` VALUES (3, 2, 'Developing networks and building and maintaining relationships', 'Builds a strong network of collaborative relationships, in order to achieve objectives, whilst supporting wider stakeholder agendas. Recognised as an active mentor and coach and can demonstrate examples of coaching others in immediate teams to improve performance.');
INSERT INTO `Responsibility` VALUES (3, 2, 'Working within teams', 'Encourages involvement from others to deliver through collaboration better results for Kainos. Recognises and builds on individual strengths of colleagues and team members while building relationships based on trust. Consistently publicise what the team members have achieved and give feedback and recognition awards where appropriate.');

INSERT INTO `Responsibility` VALUES (4, 2, 'Mobilises self and others to drive self-improvement', 'Proactively supports the development of other team members while identifying opportunities to increase innovation to achieve team’s objectives.');
INSERT INTO `Responsibility` VALUES (4, 2, 'Champions continuous improvement', 'Encourages both formal and informal learning opportunities, ensuring others take responsibility for own learning and share this to increase organisational capability. Support and stretches self and others to deliver agreed goals and objectives.');
INSERT INTO `Responsibility` VALUES (4, 2, 'Developing networks and building and maintaining relationships', 'Effectively builds wider networks across projects, Capabilities and BUs and constructively challenges colleagues including those in positions of authority.');
INSERT INTO `Responsibility` VALUES (4, 2, 'Working within teams', 'Encourages collaborative team working within immediate teams and across the whole business. Supports an environment where others can make mistakes and learn from it. Open to giving and receiving honest feedback in order to highlight areas of improvement and recognise high performance.');

INSERT INTO `Responsibility` VALUES (5, 2, 'Mobilises self and others to drive self-improvement', 'Actively take opportunities to learn from a diverse range of people, to maximise performance and mentor/coach more junior colleagues.');
INSERT INTO `Responsibility` VALUES (5, 2, 'Champions continuous improvement', 'Is creative in finding ways to learn and personally improve results, suggesting new approaches to benefit self and the team. Review progress against goals making necessary adjustments to deliver successfully.');
INSERT INTO `Responsibility` VALUES (5, 2, 'Developing networks and building and maintaining relationships', 'Consistently collaborates within immediate teams and finds opportunities to build rapport and trust while supporting others. Proactively contribute to the work of the whole team whilst building positive colleague relationships ');
INSERT INTO `Responsibility` VALUES (5, 2, 'Working within teams', 'Actively participates and cooperates to support others within the team to achieve common goals. Able to interact effectively in stressful or frustrating situations, knowing when to step away for composure.');

INSERT INTO `Responsibility` VALUES (6, 2, 'Mobilises self and others to drive self-improvement', 'Shares learning with the team and other colleagues, contributing to the team’s understanding.');
INSERT INTO `Responsibility` VALUES (6, 2, 'Champions continuous improvement', 'Maintains consistent performance, challenging self and others to be positive and focused on achieving results,despite setbacks. Support others to work in a way that is mutually supportive');
INSERT INTO `Responsibility` VALUES (6, 2, 'Developing networks and building and maintaining relationships', 'Builds strong working relationships within team and project teams and start to consider building a wider network.');
INSERT INTO `Responsibility` VALUES (6, 2, 'Working within teams', 'Aware of the consequences of own behaviour and how this may affect others within the team. Supports the sharing of knowledge, information and learning with other colleagues.');

INSERT INTO `Responsibility` VALUES (7, 2, 'Mobilises self and others to drive self-improvement', 'Understand how to respond constructively to developmental feedback from a diverse range of people and implement changes as a result.');
INSERT INTO `Responsibility` VALUES (7, 2, 'Champions continuous improvement', 'Displays high levels of enthusiasm, energy and pace to achieve performance and results.');
INSERT INTO `Responsibility` VALUES (7, 2, 'Developing networks and building and maintaining relationships', 'Recognises the need to build internal networks within immediate teams and projects. ');
INSERT INTO `Responsibility` VALUES (7, 2, 'Working within teams', 'Respects others by attending meetings on time and contributing where appropriate. Recognising how current role relates to others within Capability and project.');

# Competency 3: Setting Direction, Development and Accountability
# Principle
INSERT INTO `Responsibility` VALUES (1, 3, 'Identifying the contexts for change', 'Actively seeks to learn about internal and external factors which will impact on the business. Interprets the meaning of these for Kainos and incorporates these into business plans and actions. ');
INSERT INTO `Responsibility` VALUES (1, 3, 'Improvement and Innovation', 'Understands the complex interdependencies across a range of services. Applies knowledge to set future direction. Challenges colleagues’ thinking to find better and more effective ways of delivering services and quality. Accesses creativity and innovation from relevant individuals and groups.');
INSERT INTO `Responsibility` VALUES (1, 3, 'Making decisions', 'Remains accountable for making timely decisions in complex situations. Modifies decisions and flexes direction when faced with new information or changing circumstances.');
INSERT INTO `Responsibility` VALUES (1, 3, 'Creating and Evaluating impact', 'Identifies gains which can be applied elsewhere in the business and incorporates these into operational / capability / business plans. Disseminates learning from changes which have been introduced.');

# Manager
INSERT INTO `Responsibility` VALUES (2, 3, 'Identifying the contexts for change', 'Understands the range of factors which determine why changes are made and supports Kainos senior leaders to deliver key messages.');
INSERT INTO `Responsibility` VALUES (2, 3, 'Improvement and Innovation', 'Gathers data and information about aspects of the business, analyses evidence and uses this knowledge to suggest changes that will improve services in the future. Questions established practices which do not add value. Puts forward creative suggestions to improve the quality of service provided.');
INSERT INTO `Responsibility` VALUES (2, 3, 'Making decisions', 'Consults with others and contributes to decisions including the future direction/vision of own business area or projects. ');
INSERT INTO `Responsibility` VALUES (2, 3, 'Creating and Evaluating impact', 'Assesses the effects of change on service delivery and customer outcomes. Makes recommendations for future improvements.');

INSERT INTO `Responsibility` VALUES (3, 3, 'Effective time management', 'Develops effective systems to organise and track workload while, motivating and encouraging others to achieve planned results, delegating work to use people and resources to best effect. Ensuring colleagues are aware of any changing priorities and monitor progress.');
INSERT INTO `Responsibility` VALUES (3, 3, 'Promoting accountability', 'Delegates authority to match responsibilities and holds staff accountable for agreed-upon commitments. Within immediate teams creates accountability by using experience and advice to guide others.');
INSERT INTO `Responsibility` VALUES (3, 3, 'Effective meetings', 'Sets an example of leading internal and external meetings through preparation, prioritisation, and considered agendas, ensuring any challenges and issues are discussed.');
INSERT INTO `Responsibility` VALUES (3, 3, 'Problem solving', 'Seeks the opinions and experiences of others to understand different approaches while thinking laterally about own work and encouraging creative problem solving.');

INSERT INTO `Responsibility` VALUES (4, 3, 'Effective time management', 'A strong ability to multitask and prioritise to deadlines, overcoming obstacles to ensure the work is completed within time. Researching and communicating in advance any barriers that may affect projects.');
INSERT INTO `Responsibility` VALUES (4, 3, 'Promoting accountability', 'Demonstrates individual responsibility for achieving objectives and able to articulate success, failures and proposing remedial actions.');
INSERT INTO `Responsibility` VALUES (4, 3, 'Effective meetings', 'Consistently prepared for meetings and effectively manages own diary for preparation and an agenda is set in advance. Actively seeks input from colleagues and challenges others where appropriate.');
INSERT INTO `Responsibility` VALUES (4, 3, 'Problem solving', 'Processes and distils a variety of information to understand a problem fully while proposing options for solutions and building on the ideas of others.');

INSERT INTO `Responsibility` VALUES (5, 3, 'Effective time management', 'Recognises how much time is required for different tasks and start to prioritise and communicate effectively within teams. Seeking appropriate support while supporting peers and junior team members.');
INSERT INTO `Responsibility` VALUES (5, 3, 'Promoting accountability', ' Identifies commitments and proactively seeks responsibility in delivering towards the wider team and project objectives.');
INSERT INTO `Responsibility` VALUES (5, 3, 'Effective meetings', 'Establishes effective meetings through setting an agenda and coming prepared and speaking on projects calls where appropriate. Follows up and delivers upon actions from meetings.');
INSERT INTO `Responsibility` VALUES (5, 3, 'Problem solving', 'Makes suggestions for improvements to personal work and processes. ');

INSERT INTO `Responsibility` VALUES (6, 3, 'Effective time management', 'Plans time effectively to ensure deadlines are met, and seen to be honest, escalating in advance any issues with completing tasks within the specified time.');
INSERT INTO `Responsibility` VALUES (6, 3, 'Promoting accountability', 'Takes ownership of all responsibilities within own role and honours commitments to others and to Kainos.');
INSERT INTO `Responsibility` VALUES (6, 3, 'Effective meetings', 'Effectively manages diary, coming prepared for meetings and actively listen.');
INSERT INTO `Responsibility` VALUES (6, 3, 'Problem solving', 'Breaks down issues, and actively seeks further information and understanding.');

INSERT INTO `Responsibility` VALUES (7, 3, 'Effective time management', 'Understands role, tasks and deadlines and work towards these, escalating any issues where appropriate to People/Project Manager.');
INSERT INTO `Responsibility` VALUES (7, 3, 'Promoting accountability', 'Accepts personal responsibility for quality and timelines of work set.');
INSERT INTO `Responsibility` VALUES (7, 3, 'Effective meetings', 'Works to manage diary, commitments and ready to attend meetings on time.');
INSERT INTO `Responsibility` VALUES (7, 3, 'Problem solving', 'Actively supports new initiatives and tries different ways of doing things, learning from others’ experiences.');

# Competency 4: Supporting and Delivering the Strategy
# Principle
INSERT INTO `Responsibility` VALUES (1, 4, 'Framing the strategy', 'Encourages and demonstrates creative, lateral, critical and systems-based thinking when framing the strategy.');
INSERT INTO `Responsibility` VALUES (1, 4, 'Developing the strategy', 'Engages with key individuals and groups to formulate strategic plans that aligns with the wider Kainos objectives. Identify organisational change requirements and ensure they are understood for organisational readiness decisions.');
INSERT INTO `Responsibility` VALUES (1, 4, 'Implementing the strategy', 'Ensures that the strategic plans are translated into workable operational plans, identifying risks, critical success factors and evaluation methods. Championing change despite personal resistance to demonstrate the ability to support and lead other through change. ');
INSERT INTO `Responsibility` VALUES (1, 4, 'Embedding the strategy ', 'Creates a consultative organisational culture to support delivery of the strategy and to drive strategic change within the wider business. Sets direction and priorities through KPI’s and monitors success.');

# Manager
INSERT INTO `Responsibility` VALUES (2, 4, 'Framing the strategy', 'Strategic awareness including an understanding and knowledge of how role and those within in immediate team fit with and support delivery of the organisational objectives.');
INSERT INTO `Responsibility` VALUES (2, 4, 'Developing the strategy', 'Feeds in ideas and knowledge from immediate teams and supports the senior leaders in developing a strategy.');
INSERT INTO `Responsibility` VALUES (2, 4, 'Implementing the strategy', 'Support, develop and implement the strategy in immediate teams. ');
INSERT INTO `Responsibility` VALUES (2, 4, 'Embedding the strategy ', 'Passionately advocates the strategy and vision and translates this into action and opportunities within immediate teams. Understands the need for OKR’s/KPI’s and own role in supporting and shaping in relation to immediate teams.');

INSERT INTO `Responsibility` VALUES (3, 4, 'Supporting the strategy and vision', 'Balances own teams needs with wider organisational needs while translating the Vision and Strategy into practical and tangible goals.');
INSERT INTO `Responsibility` VALUES (3, 4, 'Organisational awareness', 'Effectively ensures immediate teams understand how their work contributes to and delivers organisational priorities.');
INSERT INTO `Responsibility` VALUES (3, 4, 'Cultural and ethical awareness', 'Consistently role models cultural effectiveness in the demonstration of ethical behaviours while understanding the value diversity brings to Kainos. Promoting diversity and inclusion within teams while adapting to the needs of our diverse people.');

INSERT INTO `Responsibility` VALUES (4, 4, 'Supporting the strategy and vision', 'Supports peers and team members in understanding the wider Kainos objectives and how we all have a role in Kainos’success.');
INSERT INTO `Responsibility` VALUES (4, 4, 'Organisational awareness', 'Recognises and reflects on how personal actions may have a wider impact on other people and teams.');
INSERT INTO `Responsibility` VALUES (4, 4, 'Cultural and ethical awareness', 'Demonstrates awareness and appreciation for the global multi-dimensional and diverse perspectives of our people.Provide a protective environment within immediate teams in which colleagues can escalate any issues while demonstrating impartial application of Kainos policies, procedure and practices.');

INSERT INTO `Responsibility` VALUES (5, 4, 'Supporting the strategy and vision', 'Articulates individual contribution to the wider Kainos objectives and uses evidence by including SMART goals that align to the Capability/BU.');
INSERT INTO `Responsibility` VALUES (5, 4, 'Organisational awareness', 'Clearly defines how individual personal performance can have an impact on the business achieving the vision and objectives.');
INSERT INTO `Responsibility` VALUES (5, 4, 'Cultural and ethical awareness', 'Respects differences and promotes inclusion on a transactional level. Displays appropriate ethical behaviours and acknowledges own unconscious bias.');

INSERT INTO `Responsibility` VALUES (6, 4, 'Supporting the strategy and vision', 'Recognises how to contribute to the wider organisational objectives and strategic principles.');
INSERT INTO `Responsibility` VALUES (6, 4, 'Organisational awareness', 'Pays attention to organisational information, how well we are doing and what is changing.');
INSERT INTO `Responsibility` VALUES (6, 4, 'Cultural and ethical awareness', 'Awareness of cultural differences and willingness to develop and grow understanding of global and cultural effectiveness.');

INSERT INTO `Responsibility` VALUES (7, 4, 'Supporting the strategy and vision', 'Understands the Kainos Vision, Strategy and Principles.');
INSERT INTO `Responsibility` VALUES (7, 4, 'Organisational awareness', 'Understands the organisation structure and the purpose of Kainos.');
INSERT INTO `Responsibility` VALUES (7, 4, 'Cultural and ethical awareness', 'Possess general knowledge of local cultural differences and familiar with the Kainos policy towards Diversity and Inclusion.');

# Competency 5: Commerciality and Risk
# Principle
INSERT INTO `Responsibility` VALUES (1, 5, 'Demonstrates commercial awareness and behaviours', 'Recognises commercial opportunities and where necessary seeks support in converting those opportunities into income. Leads on the awareness to protect margin through various means by setting the tone within own teams and BU. Encouraging others to challenge profit, spend and process. Creates value add propositions to support growth and negotiates fair deals for customers and services both internal and external. ');
INSERT INTO `Responsibility` VALUES (1, 5, 'Contributes to the ongoing development of new business to support Kainos’ growth ambitions', 'Takes ownership and responsibility of creating and executing a strategy to develop new business in an area of expertise, in accordance with agreed targets and OKRs. Pro-actively promotes Kainos externally, in line with relevant field of expertise, identifying and fostering leads and opportunities for Kainos to generate new business. Coaches, motivates and encourages colleagues to develop new BD skills and provides the opportunity for colleagues to excel in this field.');
INSERT INTO `Responsibility` VALUES (1, 5, 'Improves efficiency and effectiveness to drive profitability', 'Leads the creation of business cases while championing efficiencies through innovation and change. Challenges others to work differently by reducing the effort within Kainos internally and engages customers in commercial conversations to maximise profit. ');
INSERT INTO `Responsibility` VALUES (1, 5, 'Manages risk to Kainos and the interest of our shareholders', 'Promotes all aspects of the Kainos brand both internally and externally. Sets and delivers upon goals that directly impact the financial stability of Kainos. Implements appropriate mechanisms to measure progress towards commercial results, identifies shortfalls and risks and escalates as necessary.');
INSERT INTO `Responsibility` VALUES (1, 5, 'Promotes and adheres to Kainos process and policies', 'Influences the Kainos processes and policies ensuring teams are consistently managed against these. ');

# Manager
INSERT INTO `Responsibility` VALUES (2, 5, 'Demonstrates commercial awareness and behaviours', 'Aware of the importance of effective commercial behaviours and the needs of the business. Identifies and ensures that personal objectives are focussed on innovative solutions to achieve commercial outcomes and objectives as well as contributing to our growth ambitions. Recognises when products or services are not being  delivered to the required level of quality or standard and takes appropriate action. ');
INSERT INTO `Responsibility` VALUES (2, 5, 'Contributes to the ongoing development of new business to support Kainos’ growth ambitions', 'Contributes to development of new business through involvement in BD process, for example, bid/proposal writing, customer presentations, pricing solutions. Establishes trusted customer relationships and adds value by helping and advising customers in areas beyond current project work, identifying possible areas where additional Kainos business can be generated.');
INSERT INTO `Responsibility` VALUES (2, 5, 'Improves efficiency and effectiveness to drive profitability', 'Looks to support and drive efficiencies and profitability through immediate team. Reviews processes within own teams and challenges through the right behaviours and channels to support the overall commercial objectives. ');
INSERT INTO `Responsibility` VALUES (2, 5, 'Manages risk to Kainos and the interest of our shareholders', 'Understands and protects Kainos from risks associated with contractual commitments. Takes a balanced risk and reward view to activities and commitments while managing quality and Kainos interests.');
INSERT INTO `Responsibility` VALUES (2, 5, 'Promotes and adheres to Kainos process and policies', 'Supports and aligns to Kainos policies and processes escalating or challenging where appropriate. Ensures compliance across own team.');

INSERT INTO `Responsibility` VALUES (3, 5, 'Demonstrates commercial awareness and behaviours', 'Identifies potential new opportunities for Kainos to generate income and proactively takes action to progress. Understanding the commercial implications of changes in scope and negotiate with customers proactively. Able to look beyond the immediate problems/issues to see the impact on the bigger picture.');
INSERT INTO `Responsibility` VALUES (3, 5, 'Improves efficiency and effectiveness to drive profitability', 'Uses financial information to find pragmatic new ways of saving cost/effort without reducing output. Review procedures or systems with immediate teams to identify improvements and simplify processes and decision making.');
INSERT INTO `Responsibility` VALUES (3, 5, 'Promotes and adheres to Kainos process and policies', 'Recognise the impact of BU and company profitability and supports company decisions that affect+profit. Ensures own team are adhering to policies, processes, and procedures.');

INSERT INTO `Responsibility` VALUES (4, 5, 'Demonstrates commercial awareness and behaviours', 'Demonstrates a clear understanding of how the business makes profit and how individual role effects profitability of the company. Respectfully challenges commercial decisions to contribute an increased profitability.');
INSERT INTO `Responsibility` VALUES (4, 5, 'Improves efficiency and effectiveness to drive profitability', 'Manages and meets the expectations of customers without compromising budgets. Supports change in a constructive manner to help colleagues and stakeholders understand changes and why they have been introduced.');
INSERT INTO `Responsibility` VALUES (4, 5, 'Promotes and adheres to Kainos process and policies', 'Understands the organisational structure of Kainos, reporting lines and can actively identify where key responsibilities lie. ');

INSERT INTO `Responsibility` VALUES (5, 5, 'Demonstrates commercial awareness and behaviours', 'Comprehends the need for the business to generate additional income and respects that costs need to be managed. Recognises the contribution that role makes to the success of the business, consistently delivering to task deadlines.');
INSERT INTO `Responsibility` VALUES (5, 5, 'Improves efficiency and effectiveness to drive profitability', 'Acknowledges what is required to manage costs within the business. Considers and suggests ideas for improvements, sharing this feedback with others in a constructive manner.');
INSERT INTO `Responsibility` VALUES (5, 5, 'Promotes and adheres to Kainos process and policies', 'Constantly achieves personal productive utilisation targets. Appreciating how the team/project supports increased income for Kainos.');

INSERT INTO `Responsibility` VALUES (6, 5, 'Demonstrates commercial awareness and behaviours', 'Understands how the business generates income. Supporting the business by assisting in activities such as Recruitment events, Career fairs and Work experience mentoring. Keeps up to date with current trends relating to work.');
INSERT INTO `Responsibility` VALUES (6, 5, 'Improves efficiency and effectiveness to drive profitability', 'Reviews current practices and constructive in implementing changes to own work.');
INSERT INTO `Responsibility` VALUES (6, 5, 'Promotes and adheres to Kainos process and policies', 'Consistently cooperates with business processes completing accurately and honestly e.g., timesheets/EOY Review and travel requests.');

INSERT INTO `Responsibility` VALUES (7, 5, 'Demonstrates commercial awareness and behaviours', 'Understands the markets and sectors in which Kainos operates while acknowledging how role links in and has an impact on other teams and the business.');
INSERT INTO `Responsibility` VALUES (7, 5, 'Improves efficiency and effectiveness to drive profitability', 'Wiling to learn, and embrace new ideas, to improve processes and procedures.');
INSERT INTO `Responsibility` VALUES (7, 5, 'Promotes and adheres to Kainos process and policies', 'Cooperates with business processes completing accurately and honestly e.g., timesheets/EOY Review and travel requests.');

# Competency 6: Communicating and Influence
# Principle
INSERT INTO `Responsibility` VALUES (1, 6, 'Develops, communicates and promotes the Kainos vision and strategy', 'Engages people across the business in the vision and strategy and demonstrates a high degree of emotional intelligence and transitioning between influencing style to suit the audience.');
INSERT INTO `Responsibility` VALUES (1, 6, 'Influencing the vision and beyond', 'Communicates the vision and rationale for change and modernisation through engaging and facilitating others to work collaboratively to achieve real change.');
INSERT INTO `Responsibility` VALUES (1, 6, 'Influencing change and transformation', 'Energises others to drive change that will improve Kainos by actively managing the change process, drawing on models of effective change management. Recognises and addresses the impact of change on people and services.');
INSERT INTO `Responsibility` VALUES (1, 6, 'Stakeholder management', 'Actively builds and maintains internal and external networks to achieve progress on objectives and shared interests through a robust understanding of Kainos’s stakeholder landscape, interactions, issues,and linkages. Engages effectively on complex issues with hostile stakeholders.');

# Manager
INSERT INTO `Responsibility` VALUES (2, 6, 'Develops, communicates and promotes the Kainos vision and strategy', 'Provides feedback and support in communicating the vision, and advocates within immediate teams.');
INSERT INTO `Responsibility` VALUES (2, 6, 'Influencing the vision and beyond', 'Credible communicator, displaying authenticity and adapting influencing style for different audiences');
INSERT INTO `Responsibility` VALUES (2, 6, 'Influencing change and transformation', 'Articulates the need for changes to processes and systems, acknowledging the impact on people and services.');
INSERT INTO `Responsibility` VALUES (2, 6, 'Stakeholder management', 'Applies and adapts various approaches to stakeholder management through influencing and negotiating to maximise business for Kainos by developing and maintaining internal and external networks.');

INSERT INTO `Responsibility` VALUES (3, 6, 'Communicates effectively', 'Uses communication to create a shared sense of purpose and direction. Adapting own style to effectively communicate and able to demonstrate this when difficult conversations have a positive outcome.');
INSERT INTO `Responsibility` VALUES (3, 6, 'Influencing others', 'Acts as an influential and effective member of multi-disciplinary teams or projects. Initiate’s collaboration and actively encourages people to cooperate in initiatives where they can add value');
INSERT INTO `Responsibility` VALUES (3, 6, 'Customer Focus and Stakeholder management', 'Assesses customer needs accurately by listening and applying sensitive questioning. Managing customer expectations in relation to scope of work and are honest in what can and cannot be achieved within timescales. Confident in negotiating with customers to reflect changes in scope of work. Generic behaviours observed if individual is not yet demonstrating this competency.');

INSERT INTO `Responsibility` VALUES (4, 6, 'Communicates effectively', 'Recognises and respect that communication is a two-way process and demonstrate effective questioning and active listening skills to achieve this. Confidently handles challenging conversations and is clear and constructive in response.');
INSERT INTO `Responsibility` VALUES (4, 6, 'Influencing others', 'Persuades and influences with sound rationale argument ‘appealing to others’ interest or reason to gain support.');
INSERT INTO `Responsibility` VALUES (4, 6, 'Customer Focus and Stakeholder management', 'Authentic in stakeholder relationship sand take pride in being inclusive and trustworthy while keeping promises made with customers. Responds honestly and promptly to customer requests and whenever possible within agreed timeframes. ');

INSERT INTO `Responsibility` VALUES (5, 6, 'Communicates effectively', 'Involved at meetings, asking questions, listening and develops and presents a well-reasoned point of view. Remaining communicative and clear in own thoughts and ideas when approached by others, giving consideration to the communication needs of other staff and customers.');
INSERT INTO `Responsibility` VALUES (5, 6, 'Influencing others', 'Demonstrates influencing skills internally and able to communicate points clearly and open to feedback from others.');
INSERT INTO `Responsibility` VALUES (5, 6, 'Customer Focus and Stakeholder management', 'Demonstrates how to participate in stakeholder management, escalates effectively, and strives to provide a quality service and showcase Kainos positively.');

INSERT INTO `Responsibility` VALUES (6, 6, 'Communicates effectively', 'Communicates own views in a clear and constructive manner, while listening to different views and considers all employees from various backgrounds');
INSERT INTO `Responsibility` VALUES (6, 6, 'Influencing others', 'Observes team/colleagues influencing internally and externally and understands the benefits of adapting personal style to shape, motivate and influence effectively.');
INSERT INTO `Responsibility` VALUES (6, 6, 'Customer Focus and Stakeholder management', 'Understands who our customers are and what problems the team is trying to solve. ');

INSERT INTO `Responsibility` VALUES (7, 6, 'Communicates effectively', 'Acts in a respectful manner in all forms of communication while being open and honest. Displays a positive approach when interacting with others.');
INSERT INTO `Responsibility` VALUES (7, 6, 'Influencing others', 'Recognises influencing as a key skill required to work effectively with internal and external customers.');
INSERT INTO `Responsibility` VALUES (7, 6, 'Customer Focus and Stakeholder management', 'Acts in accordance with the Kainos values demonstrating through own behaviours and interactions with colleagues and customers.');



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

