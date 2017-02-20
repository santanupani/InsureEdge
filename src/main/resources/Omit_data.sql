--use polygon;

/*login*/
insert into login (user_name, password, first_name, last_name, enabled, role) values('admin', 'secret', 'admin','admin', true, 'ROLE_ADMIN');
insert into login (user_name, password, first_name, last_name, enabled, role) values('broker', 'secret', 'Santanu(Broker)','Pani', true, 'BROKER');
insert into login (user_name, password, first_name, last_name, enabled, role) values('underwriter', 'secret', 'Binod(Underwritter)','Sethi', true, 'UNDERWRITTER');
insert into login (user_name, password, first_name, last_name, enabled, role) values('claimadmin', 'secret', 'Manmay(Claim-Admin)','Mohanty', true, 'ClAIMADMIN');
insert into login (user_name, password, first_name, last_name, enabled, role) values('manager', 'secret', 'Ardhendu(Manager)','Patri', true, 'MANAGER');

/*products*/

insert into products(name, description, image) values('Cash and Valuables in Transit', 'Covers all risk whilst in transit.', '/img/products/Cash and Valuables in Transit.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'Covers all risk whilst static/on-site in vault or safe.','/img/products/Static Cover Cash and Valuables.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Tailored cover for your treasured possessions.', '/img/products/Fine Art and Collectables.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'Covers all risk whilst in transit and static/on-site in vault or safe.', '/img/products/Static and In Transit Cover Cash and Valuables.jpeg');
insert into products(name, description, image) values('Auto Insurance', 'Covers for Liability, Collision, Comprehensive, Medical Payment and Many more', '/img/products/Auto Insurance.jpg');

/*carriers*/
insert into carriers(description) values('G4S Security Solutions');
insert into carriers(description) values('Fidelity Security Solutions');
insert into carriers(description) values('SBV Services');
insert into carriers(description) values('Imperial Logistics');
insert into carriers(description) values('Izikathi');
insert into carriers(description) values('Quiniseka');
insert into carriers(description) values('Transcash');
insert into carriers(description) values('Braziers');
insert into carriers(description) values('Rens Kontant In Transito');
insert into carriers(description) values('Gold Kid');



/*jaspers*/
insert into jasper_images(name, image) values('polygon-logo', 'reports/reverside-header.png');
insert into jasper_images(name, image) values('polygon-footer','reports/reverside-footer.png');
insert into jasper_images(name, image) values('genric-logo', 'reports/reverside.jpg');
insert into jasper_images(name, image) values('polygon-sched', 'reports/reverside.jpg');

/*broker*/
insert into brokers(code, name, email) values ('00001', 'Reverside', 'broker@reverside.co.za');


/*answer_type*/
insert into answer_types(answer_type) values('text');
insert into answer_types(answer_type) values('number');
insert into answer_types(answer_type) values('select');
insert into answer_types(answer_type) values('checkbox');
insert into answer_types(answer_type) values('textarea');
insert into answer_types(answer_type) values('date');
insert into answer_types(answer_type) values('other');


/*Product1*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '1', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, true);
insert into answer_values(questionnaire_id, answer_value) values(1, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(1, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '2', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '3', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '4', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '5', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '6', 'Are you currently insured ?'                     ,                      4,    null,  null, false);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '7', 'Please provide details of your previous insurance company :',           1,    6,  true, true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '8', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, false);

/*Product2*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '9', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, true);
insert into answer_values(questionnaire_id, answer_value) values(9, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(9, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '10', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '11', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '12', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '13', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '14', 'Are you currently insured ?'                     ,                    4,    null,  null, false);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '15', 'Please provide details of your previous insurance company :',           1,    6,  true, true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '16', 'Do you require SASRIA cover ?',                 4,  null,    null, false);


/*Product3*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '17', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, true);
insert into answer_values(questionnaire_id, answer_value) values(17, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(17, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(17, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '18', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '19', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '20', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '21', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '22', 'Are you currently insured ?'                     ,                    4,    null,  null, false);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '23', 'Please provide details of your previous insurance company :'                     ,                    1,   6,  true, true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '24', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, false);

/*product 4*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '25', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, true);
insert into answer_values(questionnaire_id, answer_value) values(25, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(25, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(25, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '26', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '27', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '28', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '29', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '30', 'Are you currently insured ?'                     ,                    4,    null,  null, false);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '31', 'Please provide details of your previous insurance company :'                     ,                    1,    6,  true, true);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '32', 'Do you require SASRIA cover ?',                 4,  null,    null, false);

insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Santanu','Santanu','Pani','santanu.pani@reverside.co.za');
insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Binod','Binod','Sethi','santanu.pani@reverside.co.za');

insert into underwriters(first_name,middle_name,last_name,email) values('Binod','Binod','Sethi','santanu.pani@reverside.co.za');
insert into underwriters(first_name,middle_name,last_name,email) values('Santanu','Santanu','Pani','santanu.pani@reverside.co.za');


insert into claim_types (claim_type) values('ONSITE ROBBERY');
insert into claim_types (claim_type) values('CROSS PAVEMENT ROBBERY');
insert into claim_types (claim_type) values('VEHICLE ROBBERY');
insert into claim_types (claim_type) values('SHORTAGES');

insert into claim_answer_types(claim_answer_type) values('text');
insert into claim_answer_types(claim_answer_type) values('number');
insert into claim_answer_types(claim_answer_type) values('select');
insert into claim_answer_types(claim_answer_type) values('checkbox');
insert into claim_answer_types(claim_answer_type) values('textarea');
insert into claim_answer_types(claim_answer_type) values('date');
insert into claim_answer_types(claim_answer_type) values('other');
insert into claim_answer_types(claim_answer_type) values('blob');


insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','1', 'Event date and time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','2', 'Discovery date and time', 6, true);

insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','3', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','4', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(4, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(4, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','5', 'If on premises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(5, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(5, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','6', 'Please select Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','7', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','8', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','9', 'If Loss/Damage caused by another party give contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','10', 'If Loss/Damage caused by another party give insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','11', 'What is the Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','12', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','13', 'Security Company case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','14', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','15', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','16', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','17', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','18', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','19', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','20', 'Value of all Valuables Claimed under the policy?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','21', 'Value of Cash Claimed under the policy?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','22', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','24', 'Event Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','25', 'Discovery Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','26', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','27', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(27, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(27, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','28', 'If on premises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(28, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(28, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','29', 'Please select Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','30', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','31', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','32', 'If Loss/Damage caused by another party give contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','33', 'If Loss/Damage caused by another party give insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','34', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','35', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','36', 'Security Company case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','37', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','38', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','39', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','40', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','41', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','42', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','43', 'Value of all Valuables Claimed under the policy?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','44', 'Value of Cash Claimed under the policy?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','45', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','46', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','47', 'Event Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','48', 'Discovery Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','49', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','50', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(50, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(50, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','51', 'If on premises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(51, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(51, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','52', 'Please select Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(52, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(52, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(52, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(52, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','53', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','54', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','55', 'If Loss/Damage caused by another party give contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','56', 'If Loss/Damage caused by another party give insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','57', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','58', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','59', 'Security Company case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','60', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','61', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','62', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','63', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','64', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','65', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','66', 'Value of all Valuables Claimed under the policy?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','67', 'Value of Cash Claimed under the policy?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','68', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','69', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','70', 'Event Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','71', 'Discovery Date and Time', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','72', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','73', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(73, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(73, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','74', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(74, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(74, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','75', 'Please select Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(75, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(75, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(75, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(75, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','76', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','77', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','78', 'If Loss/Damage caused by another party give contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','79', 'If Loss/Damage caused by another party give insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','80', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','81', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','82', 'Security Company case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','83', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','84', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','85', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','86', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','87', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','88', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','89', 'Value of all Valuables Claimed under the policy?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','90', 'Value of Cash Claimed under the policy? ', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','91', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','92', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);


insert into request_types (request_type) values('CANCELLATION');
insert into request_types (request_type) values('REINSTATEMENT');
insert into request_types (request_type) values('TERMINATION');
insert into request_types (request_type) values('ENDORSEMENT');

insert into request_answer_types(request_answer_type) values('text');
insert into request_answer_types(request_answer_type) values('number');
insert into request_answer_types(request_answer_type) values('select');
insert into request_answer_types(request_answer_type) values('checkbox');
insert into request_answer_types(request_answer_type) values('textarea');
insert into request_answer_types(request_answer_type) values('date');
insert into request_answer_types(request_answer_type) values('blob');

insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('1','1', 'Reason For Cancellation', 1, true);
insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('1','2', 'Effective Date', 6, true);

insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('3','1', 'Reason For Termination', 1, true);
insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('3','2', 'Effective Date', 6, true);



/*insert into release_form(claim_number, policy_number, claim_request_id, insured, amount_claim , less_excess , total_payeble , good_description , loss_date, loss_description )
values('CS-000', '2016-08', 1, 'santanu', '500', '34', '100', 'goods', '2016-07-15', 'loss');*/


