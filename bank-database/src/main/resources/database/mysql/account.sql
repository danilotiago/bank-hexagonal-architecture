create table accounts(
	number int primary key auto_increment not null,
	balance decimal(10,2) not null default 0.0,
	account_holder varchar(255) not null
);

insert into accounts(number, balance, account_holder) values (10, 25.50, 'Um Nome De Cliente');
insert into accounts(number, balance, account_holder) values (20, 15.00, 'Um Outro Nome De Cliente');
insert into accounts(number, balance, account_holder) values (30, 10.00, 'Um Cliente Fictício');