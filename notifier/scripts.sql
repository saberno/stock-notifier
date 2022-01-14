create table stock_record(
    id int auto_increment,
    symbol varchar(50),
    created_date timestamp,
    stock_value decimal(10, 4),
    PRIMARY KEY(id)
);

create table user(
    id int auto_increment,
    name varchar(50),
    phone_number char(11),
    email varchar(30),
    created_date timestamp,
    PRIMARY KEY(id, phone_number, email)
);

create table user_interest(
    id int auto_increment,
    user_id int,
    created_date timestamp,
    symbol varchar(50),
    stock_change_direction char(1),
    stock_change_percentage decimal(10, 0),
    PRIMARY KEY(id, user_id, symbol)
);

