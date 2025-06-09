alter table customers alter column status set default 'REGULAR';

insert into customers (first_name, last_name)
values
    ('Alex', 'Smith');

insert into customers (first_name, last_name, status)
values
    ('Alex', 'Smith', 'GOLD');