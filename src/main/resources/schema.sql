--use polygon;
/* table : login */
create table login (
    id integer auto_increment not null primary key,
    user_name varchar(32) not null, 
    password varchar(32) not null,
    first_name varchar(32) not null,
    last_name varchar(32) not null, 
    enabled boolean not null, 
    role varchar(16) not null
);

/* table : product */
create table products(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    description varchar(256),
    image varchar(128) 
);

/* table: answer_types */
create table answer_types(
    id integer auto_increment not null primary key,
    answer_type varchar(16) not null
);



/* table : questionnaires */
create table questionnaires(
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
create table answer_values(
    id integer auto_increment not null primary key,
    questionnaire_id integer not null,
    answer_value varchar(128) not null,
    constraint answer_values_fk1 foreign key (questionnaire_id) references questionnaires (id)
);


/* table : broker */
create table brokers(
    id integer auto_increment not null primary key,
    code varchar(8) not null,
    name varchar(64) not null,
    email varchar(64) not null
);

/* table : quotation_requests */
create table quotation_requests(
    id integer auto_increment not null primary key,
    reference varchar(64) not null,
    applicant_name varchar(32) not null,
    company_name varchar(32) not null,
    applicant_email varchar(64) not null,
    broker_id integer not null,
    product_id integer not null,
    create_date date not null,
    status varchar(16) not null,
    constraint quotation_requests_fk1 foreign key (broker_id) references brokers (id),
    constraint quotation_requests_fk2 foreign key (product_id) references products (id)
);


create table histories(
   id integer auto_increment not null primary key,
   quotation_request_id integer not null,
   event_date varchar(26),
   loss_value double,
   type_of_loss varchar(64),
   constraint histories_fk1 foreign key (quotation_request_id) references quotation_requests(id)  
);


/* table : location_options */
create table location_options(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    commodity varchar(256) not null,
    from_location varchar(32) not null,
    to_location varchar(32),
    distance varchar(32) not null,
    max_limit double,
    static_limit double,
    static_max_amount varchar(32),
    sabs_category varchar(32),
    total_value varchar(64),
    transit_total_value varchar(64),
    total_value_static varchar(64),
    professional_carriers varchar(32),
    duration varchar(32) not null,
    is_first_loss_cover boolean,
    is_goods_moved boolean,
    is_goods_moved_static boolean,
    is_service_carrier boolean not null,
    carrier_name varchar(32),
    specific_carrier varchar(32),
    goods_description varchar(64),
    is_store_vault boolean,
    is_concrete_secured boolean,
    is_seismic_detector boolean,
    is_cctv boolean,
    is_alarmed boolean,
    storage_type varchar(32),
    vault_address varchar(128),
    other_secure_means varchar(256),
    constraint location_options_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);

/* table : quotation_questoionnaires */
create table answers(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    question varchar(256) not null,
    answer varchar(256),
    constraint answers_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);


/* table : quotations */
create table quotations(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    created_date date not null,
    expired_date date null,
    constraint quotations_fk1 foreign key (quotation_request_id) references quotation_requests (id)
);

/* table : quotation_options  */
create table quotation_options(
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
create table policy_requests(
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
create table bank_accounts(
      id integer auto_increment not null primary key,
      account_number varchar(32) not null,
      account_name varchar(32) not null,
      branch varchar(32) not null,
      bank_name varchar(32) not null,
      account_type varchar(32) not null
);

/* table :  contacts */
create table contacts(
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
create table insurers(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    business_description varchar(256),
    contact_person varchar(32) not null
);

/* table : clients */
create table clients (
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
create table underwriters(
    id integer auto_increment not null primary key, 
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null
);

/* table : sub_agents */
create table sub_agents(
    id integer auto_increment not null primary key, 
    broker_id integer not null,
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null,
    constraint sub_agents_fk1 foreign key(broker_id) references brokers(id)
);


/* table : policy_masters */
create table policies(
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
    excess_structure varchar(256) not null,
    special_condition varchar(1024) not null,
    conveyances varchar(256) not null,
    geographical_duration varchar(2048) not null,
    notes varchar(256) not null,
    constraint policies_fk1 foreign key (sub_agent_id) references sub_agents (id),
    constraint policies_fk2 foreign key(client_id) references clients(id),
    constraint policies_fk3 foreign key(underwriter_id) references underwriters(id)
);

/* table : indemnity_option */

create table indemnity_options(
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


create table claim_types(
    id integer auto_increment not null primary key,
    claim_type varchar(32) not null
);


create table claim_answer_types(
   id integer auto_increment not null primary key,
   claim_answer_type varchar(16) not null
);

create table claim_questionnaires(
   id integer auto_increment not null primary key,
   claim_type_id integer not null,
   claim_answer_type_id integer not null,
   question varchar(124) not null,
   sequence_number integer not null,
   is_required boolean not null,
   constraint claim_questionnaires_fk1 foreign key (claim_answer_type_id) references claim_answer_types(id),
   constraint claim_questinnaires_fk1 foreign key (claim_type_id) references claim_types(id)
);

create table claim_answer_values(
    id integer auto_increment not null primary key,
    claim_questionnaire_id integer not null,
    claim_answer_value varchar(16),
    constraint claim_answer_values_fk1 foreign key (claim_questionnaire_id) references claim_questionnaires (id)
);



create table claim_requests(
    id integer auto_increment not null primary key,
    claim_number varchar(64) not null,
    policy_id integer not null,
    claim_type_id integer not null,
    create_date date not null,
    status varchar(16) not null,
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
    photo5 longblob,
    constraint claim_request_fk1 foreign key (policy_id) references policies(id),
    constraint claim_request_fk2 foreign key (claim_type_id) references claim_types(id)
);

create table claim_answers(
      id integer auto_increment not null primary key,
     claim_request_id integer not null,
     question varchar(128) not null,
     answer varchar(512),
     attachment longblob,
     constraint claim_answer_fk1 foreign key (claim_request_id) references  claim_requests(id)
);

create table request_types(
    id integer auto_increment not null primary key,
    request_type varchar(32) not null
);

create table request_answer_types(
   id integer auto_increment not null primary key,
   request_answer_type varchar(16) not null
);

create table request_questionnaires(
   id integer auto_increment not null primary key,
   request_type_id integer not null,
   request_answer_type_id integer not null,
   question varchar(124) not null,
   sequence_number integer not null,
   is_required boolean not null,
   constraint request_questionnaires_fk1 foreign key (request_answer_type_id) references request_answer_types(id),
   constraint request_questinnaires_fk2 foreign key (request_type_id) references request_types(id)
);

create table request_answer_values(
    id integer auto_increment not null primary key,
    request_questionnaire_id integer not null,
    request_answer_value varchar(16),
    constraint request_answer_values_fk1 foreign key (request_questionnaire_id) references request_questionnaires (id)
);

create table policy_request_type(
     id integer auto_increment not null primary key,
     request_type_id varchar(32) not null,
     policy_id varchar(16) not null,
     status_from varchar(16),
     reference varchar(60) not null,
     created_date date not null,
     effective_date date,
     status varchar(16) not null,
     constraint policy_request_type_fk1 foreign key (policy_id) references  policies(id),
     constraint policy_request_type_fk2 foreign key (request_type_id) references  request_types(id)
);

create table request_answers(
      id integer auto_increment not null primary key,
     request_type_id integer not null,
     question varchar(128) not null,
     answer varchar(512),
     constraint request_answer_fk1 foreign key (request_type_id) references  policy_request_type(id)
);
