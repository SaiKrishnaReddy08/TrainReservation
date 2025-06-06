create database RailwayManagement;
use RailwayManagement;

create table user( user_id varchar(25) primary key,
					password varchar(20) not null,
					full_name varchar(30) not null, 
					age int not null, 
                    gender varchar(15) not null,
                    phone_number varchar(10) Unique not null, 
                    address varchar(100));
                    
create table train(train_number int primary key,
					name varchar(30),
                    source varchar(30),
                    destination varchar(30),
                    sl_price float,
                    ac1_price float,
                    ac2_price float,
                    ac3_price float);
                    
create table train_availability( train_number int references train(train_number),
								 DOJ date not null,
								 sl_seats int default -1,
								 ac1_seats int default -1,
								 ac2_seats int default -1,
								 ac3_seats int default -1,
                                 primary key (train_number, DOJ));


create table booking_details(booking_id int primary key auto_increment,
							 user_id varchar(25) references user(user_id) ,
                             train_number int,
                             DOJ date,
                             coach varchar(10) not null,
                             no_of_seats int not null,
                             total_fare float not null
			     FOREIGN KEY (train_number, DOJ) REFERENCES train_availability(train_number, DOJ)
			);
                             
create table Passenger
( passenger_id int auto_increment primary key,
  booking_id int references booking_details(booking_id),
  passenger_name varchar(25),
  seat_number int);
