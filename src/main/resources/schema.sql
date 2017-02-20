--use polygon;

/* table : login */
create table if not exists login (
    id integer auto_increment not null primary key,
    user_name varchar(32) not null, 
    password varchar(32) not null,
    first_name varchar(32) not null,
    last_name varchar(32) not null, 
    enabled boolean not null, 
    role varchar(16) not null
);


/* table : carriers */
create table if not exists carriers (
    id integer auto_increment not null primary key,
    description varchar(64) not null 
);



/* table : product */
create table if not exists products(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    description varchar(256),
    image varchar(128) 
);

/* table : jasper_images */
create table if not exists jasper_images(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    image varchar(128) 
);


/* table : device */
create table if not exists devices(
     id integer auto_increment not null primary key,
     name varchar(256) not null
     
);


/* table: answer_types */
create table if not exists answer_types(
    id integer auto_increment not null primary key,
    answer_type varchar(16) not null
);



/* table : questionnaires */
create table if not exists questionnaires(
    id integer auto_increment not null primary key, 
    product_id integer not null,
    sequence_number integer not null,
    question varchar(256) not null,
    answer_type_id integer not null,
    depends_on integer,
    on_answer varchar(128),
    is_required boolean not null,
    constraint questionnaires_fk1 foreign key (product_id) references products (id),
    constraint questionnaires_fk2 foreign key (answer_type_id) references answer_types (id)
);

/* table : answer_values */
create table if not exists answer_values(
    id integer auto_increment not null primary key,
    questionnaire_id integer not null,
    answer_value varchar(128) not null,
    constraint answer_values_fk1 foreign key (questionnaire_id) references questionnaires (id)
);


/* table : broker */
create table if not exists brokers(
    id integer auto_increment not null primary key,
    code varchar(8) not null,
    name varchar(64) not null,
    email varchar(64) not null
);

/* table : quotation_requests */
create table if not exists quotation_requests(
    id integer auto_increment not null primary key,
    reference varchar(64) not null,
    applicant_name varchar(64) not null,
    company_name varchar(256) not null,
    applicant_email varchar(64) not null,
    broker_id integer not null,
    product_id integer not null,
    create_date date not null,
    status varchar(16) not null,
    accident_info varchar(8),
    agreed_flag varchar(8),
    class_of_use varchar(64),
    commodity varchar(64),
    cover_amount varchar(64),
    cover_type varchar(64),
    make_model varchar(64),
    overnight_parking varchar(64),
    pensioner_status varchar(64),
    regd_number varchar(64),
    year_of_manufacture varchar(8),
    policy_inception_date varchar(32),
    policy_end_date varchar(32),
    mode_of_coverage varchar(8),
    sasria varchar(8),
    insured_status varchar(8),
    
    constraint quotation_requests_fk1 foreign key (broker_id) references brokers (id),
    constraint quotation_requests_fk2 foreign key (product_id) references products (id)
);


create table if not exists histories(
   id integer auto_increment not null primary key,
   quotation_request_id integer not null,
   event_date varchar(26),
   loss_value double,
   type_of_loss varchar(64),
   constraint histories_fk1 foreign key (quotation_request_id) references quotation_requests(id)  
);


/* table : location_options */
create table if not exists location_options(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    commodity varchar(256) not null,
    from_location varchar(128),
    to_location varchar(256),
    distance varchar(64),
    max_limit double,
    static_limit double,
    static_max_amount varchar(32),
    sabs_category varchar(32),
    total_value varchar(256),
    transit_total_value varchar(256),
    total_value_static varchar(256),
    professional_carriers varchar(64),
    duration varchar(32) not null,
    is_first_loss_cover boolean,
    is_goods_moved boolean,
    is_goods_moved_static boolean,
    is_service_carrier boolean not null,
    carrier_name varchar(64),
    specific_carrier varchar(64),
    goods_description varchar(256),
    is_store_vault boolean,
    is_concrete_secured boolean,
    is_seismic_detector boolean,
    is_cctv boolean,
    is_alarmed boolean,
    storage_type varchar(64),
    vault_address varchar(256),
    other_secure_means varchar(256),
    constraint location_options_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);

/* table : quotation_questoionnaires */
create table if not exists answers(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    question varchar(256) not null,
    answer varchar(256),
    constraint answers_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);


/* table : quotations */
create table if not exists quotations(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    created_date date not null,
    expired_date date null,
    note varchar(256) null, 
    cover varchar(256),
    premium varchar(32),
   constraint quotations_fk1 foreign key (quotation_request_id) references quotation_requests (id)
);

/* table : quotation_options  */
create table if not exists quotation_options(
    id integer auto_increment not null primary key,
    quotation_id integer not null,
    location varchar(64) not null,
    limits varchar(64) not  null ,
    commodity varchar(64) not null,
    cover varchar(256) not null,
    duration varchar(256) not null,
    excess varchar(256) not null,
    cross_pavement varchar(256),
    static_limit varchar(256),
    premium varchar(256) not null,
    constraint quotation_options_fk1 foreign key (quotation_id) references quotations (id)
);

/*table : policy_requests */
create table if not exists policy_requests(
    id integer auto_increment not null primary key,
    quotation_id integer not null,
    company_reg_number varchar(32) not null,
    vat_reg_number varchar(32) not  null ,
    telephone_number varchar(32) not null,
    fax_number varchar(256) not null,
    street_address varchar(32) not null,
    suburb varchar(64) not null,
    postal_code varchar(64) not null,
    designation varchar(64) not null,
    buisness_desc varchar(64) not null,
    account_holder varchar(64) not null,
    account_name varchar(64) not null,
    bank_name varchar(64) not null,
    account_number varchar(64) not null,
    branch_code varchar(64) not null,
    acc_type varchar(64) not null,
    debit_order_date varchar(32) not null,
    bank_statement longblob not null,
    status varchar(32) not null,
    constraint policy_requests_fk1 foreign key (quotation_id) references quotations (id)
);



/* table : bank_accounts */
create table if not exists bank_accounts(
      id integer auto_increment not null primary key,
      account_number varchar(32) not null,
      account_name varchar(32) not null,
      branch varchar(32) not null,
      bank_name varchar(32) not null,
      account_type varchar(32) not null
);

/* table :  contacts */
create table if not exists contacts(
      id integer auto_increment not null primary key,
      street varchar(32) not null,
      code varchar(32) not null,
      suburb varchar(32) not null,
      work_tel_number varchar(32) not null,
      fax_number varchar(32) not null,
      email varchar(32) not null,
      contact_person varchar(32) not null
);

/* table : insurers */
create table if not exists insurers(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    business_description varchar(256),
    contact_person varchar(32) not null
);

/* table : clients */
create table if not exists clients (
      id integer auto_increment not null primary key,
      bank_account_id integer not null,
      contact_id integer not null,
      company_name varchar(64) not null,
      reg_no varchar(32) not null,
      income_tax_number varchar(32),
      designation varchar(32) not null,
      vat_number varchar(32) not null,
      constraint clients_fk1 foreign key (bank_account_id) references bank_accounts (id),
      constraint clients_fk2 foreign key (contact_id) references contacts(id)

);


/* table : underwriters */
create table if not exists underwriters(
    id integer auto_increment not null primary key, 
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null
);

/* table : sub_agents */
create table if not exists sub_agents(
    id integer auto_increment not null primary key, 
    broker_id integer not null,
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null,
    constraint sub_agents_fk1 foreign key(broker_id) references brokers(id)
);


/* table : policy_masters */
create table if not exists policies(
    id integer auto_increment not null primary key,
    reference varchar(45) not null,
    sub_agent_id integer not null,
    client_id integer not null,
    underwriter_id integer,
    policy_inception_date date not null,
    inception_date date not null,
    renewal_date date not null,
    anniversary_date date not null,
    product_name varchar(128) not null,
    make_model varchar(64),
    commodity varchar(64),
    class_of_use varchar(64),
    underwriting_year integer not null,
    status varchar(20) not null,
    frequency varchar(30) not null,
    sasria_frequency varchar(30) not null,
    device varchar(30) not null,
    collect_by_debit_order boolean not null,
    exclude_sasria boolean,
    underwriter_commission decimal not null,
    broker_commission decimal not null,
    uma_fee decimal not null,
    policy_fee decimal not null,
    initial_fee decimal not null,
    sum_insured decimal not null,
    max_sum_insured decimal not null,
    premium decimal not null,
    sasria_premium decimal not null,
    schedule_attaching varchar(1024) not null,
    type_of_cover varchar(1024) not null,
    subject_matter varchar(64) not null,
    excess_structure varchar(256),
    special_condition varchar(1024) not null,
    conveyances varchar(256),
    geographical_duration varchar(2048) not null,
    notes varchar(256) not null,
    constraint policies_fk1 foreign key (sub_agent_id) references sub_agents (id),
    constraint policies_fk2 foreign key(client_id) references clients(id),
    constraint policies_fk3 foreign key(underwriter_id) references underwriters(id)
);

/* table : policy endorsements */
create table if not exists endorsements(
    id integer auto_increment not null primary key,
    policy_id integer not null,
    endorsement_date date not null,
    inception_date date,
    renewal_date date,
    product_name varchar(128),
    underwriting_year integer,
    status varchar(20),
    frequency varchar(30),
    sasria_frequency varchar(30),
    device varchar(30),
    underwriter_commission decimal,
    broker_commission decimal,
    uma_fee decimal,
    policy_fee decimal,
    initial_fee decimal,
    sum_insured decimal,
    max_sum_insured decimal,
    premium decimal,
    sasria_premium decimal,
    schedule_attaching varchar(1024),
    type_of_cover varchar(1024),
    subject_matter varchar(64),
    excess_structure varchar(256),
    special_condition varchar(1024),
    conveyances varchar(256),
    geographical_duration varchar(2048),
    notes varchar(256),
    constraint endorsements_fk1 foreign key (policy_id) references policies (id)
);

/* table : indemnity_option */

create table if not exists indemnity_options(
    id integer auto_increment not null primary key,
    policy_id integer not null,
    indemity_item_option varchar(64) not null,   
    indemnity_value varchar(128) not null,
    sum_insured decimal not null,
    pavement decimal,
    static_limit decimal,
    premium decimal not null,
    constraint indemnity_options_fk1 foreign key (policy_id) references policies (id)
);


create table if not exists claim_types(
    id integer auto_increment not null primary key,
    claim_type varchar(32) not null
);


create table if not exists claim_answer_types(
   id integer auto_increment not null primary key,
   claim_answer_type varchar(16) not null
);

create table if not exists claim_questionnaires(
   id integer auto_increment not null primary key,
   claim_type_id integer not null,
   claim_answer_type_id integer not null,
   question varchar(124) not null,
   sequence_number integer not null,
   is_required boolean not null,
   constraint claim_questionnaires_fk1 foreign key (claim_answer_type_id) references claim_answer_types(id),
   constraint claim_questinnaires_fk1 foreign key (claim_type_id) references claim_types(id)
);

create table if not exists claim_answer_values(
    id integer auto_increment not null primary key,
    claim_questionnaire_id integer not null,
    claim_answer_value varchar(16),
    constraint claim_answer_values_fk1 foreign key (claim_questionnaire_id) references claim_questionnaires (id)
);



create table if not exists claim_requests(
    id integer auto_increment not null primary key,
    claim_number varchar(64) not null,
    policy_id integer not null,
    claim_type_id integer not null,
    create_date date not null,
    status varchar(32) not null,
    investigation_report longblob,
    confirmation_amount longblob,
    proof_of_pickup longblob,
    case_number longblob,
    amount_banked longblob,
    trans_track_document longblob,
    quote longblob,
    photos longblob,
    report longblob,
    affidavit longblob,
    photo1 longblob,
    photo2 longblob,
    photo3 longblob,
    photo4 longblob,
    investigation_report_c varchar(128),
    confirmation_amount_c varchar(128),
    proof_of_pickup_c varchar(128),
    case_number_c varchar(128),
    amount_banked_c varchar(128),
    trans_track_document_c varchar(128),
    quote_c varchar(128),
    report_c varchar(128),
    affidavit_c varchar(128),
    photo1_c varchar(128),
    photo2_c varchar(128),
    photo3_c varchar(128),
    photo4_c varchar(128),
    constraint claim_request_fk1 foreign key (policy_id) references policies(id),
    constraint claim_request_fk2 foreign key (claim_type_id) references claim_types(id)
);

create table if not exists claim_answers(
      id integer auto_increment not null primary key,
     claim_request_id integer not null,
     question varchar(128) not null,
     answer varchar(512),
     attachment longblob,
     constraint claim_answer_fk1 foreign key (claim_request_id) references  claim_requests(id)
);


create table if not exists release_form(
    id integer auto_increment not null primary key,
    claim_number varchar(64) not null,
    policy_number varchar(64) not null,
    claim_request_id integer not null,
    insured varchar(64) not null,
    amount_claim double not null,
    less_excess double not null,
    total_payeble double not null,
    good_description varchar(256) not null,
    loss_date varchar(64) not null,
    loss_description varchar(256) not null,
    constraint release_form_fk1 foreign key (claim_request_id) references claim_requests(id) 
);

create table if not exists request_types(
    id integer auto_increment not null primary key,
    request_type varchar(32) not null
);

create table if not exists request_answer_types(
   id integer auto_increment not null primary key,
   request_answer_type varchar(16) not null
);

create table if not exists request_questionnaires(
   id integer auto_increment not null primary key,
   request_type_id integer not null,
   request_answer_type_id integer not null,
   question varchar(124) not null,
   sequence_number integer not null,
   is_required boolean not null,
   constraint request_questionnaires_fk1 foreign key (request_answer_type_id) references request_answer_types(id),
   constraint request_questinnaires_fk2 foreign key (request_type_id) references request_types(id)
);

create table if not exists request_answer_values(
    id integer auto_increment not null primary key,
    request_questionnaire_id integer not null,
    request_answer_value varchar(16),
    constraint request_answer_values_fk1 foreign key (request_questionnaire_id) references request_questionnaires (id)
);

create table if not exists policy_request_type(
     id integer auto_increment not null primary key,
     request_type_id integer not null,
     policy_id integer not null,
     status_from varchar(16),
     reference varchar(60) not null,
     created_date date not null,
     effective_date date,
     status varchar(16) not null,
     constraint policy_request_type_fk1 foreign key (policy_id) references  policies(id),
     constraint policy_request_type_fk2 foreign key (request_type_id) references  request_types(id)
);

create table if not exists request_answers(
     id integer auto_increment not null primary key,
     request_type_id integer not null,
     question varchar(128) not null,
     answer varchar(512),
     constraint request_answer_fk1 foreign key (request_type_id) references  policy_request_type(id)
);
