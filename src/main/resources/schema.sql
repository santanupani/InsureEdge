
/* table : login */
create table login (
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    user_name varchar(32) not null, 
    password varchar(32) not null, 
    enabled boolean not null, 
    role varchar(16) not null
);

/* table : product */
create table products(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key, 
    name varchar(64) not null, 
    description varchar(256),
    image varchar(128) 
);

/* table: answer_types */
create table answer_types(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    answer_type varchar(16) not null
);


/* table : questionnaires */
create table questionnaires(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key, 
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
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    questionnaire_id integer not null,
    answer_value varchar(128) not null,
    constraint answer_values_fk1 foreign key (questionnaire_id) references questionnaires (id)
);

/* table : broker */
create table brokers(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    code varchar(8) not null,
    name varchar(64) not null,
    email varchar(64) not null
);

/* table : quotation_requests */
create table quotation_requests(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    reference varchar(64) not null,
    applicant_name varchar(32) not null,
    applicant_email varchar(64) not null,
    broker_id integer not null,
    product_id integer not null,
    create_date date not null,
    status varchar(16) not null,
    constraint quotation_requests_fk1 foreign key (broker_id) references brokers (id),
    constraint quotation_requests_fk2 foreign key (product_id) references products (id)
);

/* table : quotation_questoionnaires */
create table quotation_questionnaires(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    quotation_requests_id integer not null,
    question varchar(256) not null,
    answer varchar(256),
    constraint quotation_questionnaires_fk1 foreign key (quotation_requests_id) references quotation_requests (id)   
);




/* table : quotations */
create table quotations(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    quotation_requests_id long not null,
    constraint quotation_requests_fk foreign key (quotation_requests_id) references quotation_requests (id)
    
);


/* table : quotation_options */
create table quotation_options(
    id integer generated by default as identity (start with 1, increment by 1) not null primary key,
    quotations_id long not null,
    location varchar(32) not null,
    limit varchar(32) not  null ,
    commodity varchar(32) not null,
    cover varchar(256) not null,
    peroid varchar(32) not null,
    excess varchar(256) not null,
    premium varchar(256) not null,
    constraint quotation_options_fk foreign key (quotations_id) references quotations (id)
);

create table message_body(
     id integer generated by default as identity (start with 1, increment by 1) not null primary key,
     quotation_requests_id integer not null,
     reason varchar(512),
     constraint quotation_questionnaires_fk foreign key (quotation_requests_id) references quotation_requests (id)
);
