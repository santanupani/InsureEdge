insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

insert into products(name, description, image) values('Cash and Valuables in Transit', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery � as per standard policy wording.', '/img/products/Cash and Valuables in Transit.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery � as per standard policy wording.','/img/products/Static Cover Cash and Valuables1.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery � as per standard policy wording.', '/img/products/Fine Art and Collectables.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery � as per standard policy wording.', '/img/products/Static and In Transit Cover Cash and Valuables.jpg'); 


/*broker*/
insert into brokers(code, name, email) values ('00001', 'Coin Risk Management', 'risk@coin.co.za');
insert into brokers(code, name, email) values ('00002', 'Admin Focus (Pty) Ltd', 'fanie@adminfocus.co.za');
insert into brokers(code, name, email) values ('00003', 'Optimum Financial Services Group', 'bertus@optimum-inc.co.za');
insert into brokers(code, name, email) values ('00004', 'Status Insurance Brokers (Pty) Ltd', 'info@statusib.co.za');
insert into brokers(code, name, email) values ('00005', 'Van Zyl Conradie Makelaars', 'elana@vzcon.co.za');
insert into brokers(code, name, email) values ('00006', 'Safari & Tourism Insurance Brokers (Pty) Ltd', 'yvonne@satib.co.za');
insert into brokers(code, name, email) values ('00007', 'Rens Kontant in Transito', 'renscit@vodamail.co.za');
insert into brokers(code, name, email) values ('00008', 'Multi Risk Admin (Pty) Ltd', 'queries@multirisk.co.za');
insert into brokers(code, name, email) values ('00009', 'Lazarus Dash and Associates (Pty) Ltd', 'Brian@lazdash.co.za');
insert into brokers(code, name, email) values ('00010', 'Willem Jacobus Nienaber', 'wjnienaber@telkomsa.net');
insert into brokers(code, name, email) values ('00011', 'Deposita Systems (Pty) Ltd', 'jennifer@bib.co.za');


/*answer_type*/
insert into answer_types(answer_type) values('text');
insert into answer_types(answer_type) values('number');
insert into answer_types(answer_type) values('select');
insert into answer_types(answer_type) values('checkbox');
insert into answer_types(answer_type) values('textarea');


/*insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '1', 'What is your full name ?', 				1, 	null, null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '2', 'What is your gender ?',    				3, 	null, null);
insert into answer_values(questionnaire_id, answer_value) values(2, 'Female');
insert into answer_values(questionnaire_id, answer_value) values(2, 'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '3', 'What is your salary ?',    				2, 	2,    'Female');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '4', 'What is your age ?',       				2, 	2,    'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '5', 'Do you have any criminal record ?', 	4, 	null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '6', 'Please provide details of your crime', 	5, 	5,     'true');*/


/*Product1*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '1', 'What do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '2', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '3', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '4', 'What is the total full value of the goods being moved ?',              2,   3, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '5', 'For additional premium, do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '6', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '7', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Imeprial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '8', 'By whom and how are the valuables carried ?',                           5,	6, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '9', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(9, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(9, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '10', 'From where and to where are the valuables carried.',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(10, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '11', 'What is the approximate distance ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(11, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(11, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(11, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(11, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(11, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(11, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(11, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '12', 'Are you currently insured ?'                     ,                    4,    null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '13', 'Please provide details of your insurance company ?'                     ,                    5,    12,  'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '14', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null);



/*Product2*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '15', 'What do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(15, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '16', 'What is the maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '17', 'Is the above amount the total full value of the goods being stored ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '18', 'What is the total full value of the goods being stored ?',             2,  3,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '19', 'Please give a full description of the goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '20', 'Please provide details of vault/safe ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '21', 'Are the goods going to be stored in vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '22', 'Is the vault secured with reinforced concrete on all sides as well as top and bottom ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '23', 'Is vault fitted with interior seismic detectors inside the vault ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '24', 'Is vault equipped with 24 hour infra-red cameras and normal CCTV 24 hours recording ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '25', 'Does the vault have an alarm and is it linked to an external response company ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '26', 'What is the type of storage of vault ?',                 5,  7,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '27', 'What is the address of the stored vault ?',                 5,  7,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '28', 'What is the SABS safety category of the vault, if any ?',              3,  null,   null);
insert into answer_values(questionnaire_id, answer_value) values(28, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(28, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '29', 'If secured by any other means not noted, please provide details ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '30', 'Are you currently insured ?'                     ,                    4,    null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '31', 'Please provide details of your insurance company ?'                     ,                    5,    16,  'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '32', 'Do you require SASRIA cover ?',                 4,  null,    null);


/*Product3*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '33', 'What do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(33, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '34', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '35', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '36', 'What is the total full value of the goods being moved ?',              2,   3, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '37', 'For additional premium, do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '38', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '39', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(39, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '40', 'By whom and how are the valuables carried ?',                           5,	6, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '41', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(41, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(41, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(41, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(41, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(41, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(41, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(41, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '42', 'From where and to where are the valuables carried.',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(42, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(42, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '43', 'What is the approximate distance ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(43, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(43, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(43, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(43, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(43, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(43, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(43, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '44', 'Are you currently insured ?'                     ,                    4,    null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '45', 'Please provide details of your insurance company ?'                     ,                    5,    12,  'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '46', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null);


/*product4*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '47', 'What do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Bullion');;
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '48', 'What is the maximum amount you wish to insure in Cash in transit ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '49', 'Is the above amount the total full value of the goods being moved in  Cash in transit ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '50', 'What is the total full value of the goods being moved in Cash in transit ?',              2,   3, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '51', 'For additional premium, do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '52', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '53', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(53, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '54', 'By whom and how are the valuables carried ?',                           5,	6, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '55', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(55, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(55, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(55, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(55, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(55, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(55, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(55, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '56', 'From where and to where are the valuables carried.',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(56, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(56, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '57', 'What is the approximate distance ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(57, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(57, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(57, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(57, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(57, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(57, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(57, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '58', 'What is the maximum amount you wish to insure in Static ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '59', 'Is the above amount the total full value of the goods being stored in Static ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '60', 'What is the Total Full Value of the goods being stored in Static ?',             2,  13,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '61', 'Please give a full description of the goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '62', 'Please provide details of vault/safe ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '63', 'What is the SABS safety category of the vault, if any ?',              3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(63, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(63, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '64', 'Are the goods going to be stored in vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '65', 'Is the vault secured with reinforced concrete on all sides as well as top and bottom ?',                 4,  18,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '66', 'Is vault fitted with interior seismic detectors inside the vault ?',                 4,  18,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '67', 'Is vault equipped with 24 hour infra-red cameras and normal CCTV 24 hours recording ?',                 4,  18,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '68', 'Does the vault have an alarm and is it linked to an external response company ?',                 4,  18,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '69', 'What is the type of storage of vault ?',                 5,  18,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '70', 'What is the address of the stored vault?',                 5,  18,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '71', 'If secured by any other means not noted, please provide details ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '72', 'Are you currently insured ?'                     ,                    4,    null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '73', 'Please provide details of your insurance company ?'                     ,                    5,    26,  'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '74', 'Do you require SASRIA cover ?',                 4,  null,    null);




