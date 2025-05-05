-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS flight;

-- Step 2: Use the database
USE flight;

-- Step 3: Create flightbook table
CREATE TABLE IF NOT EXISTS flightbook (
    flightno INT PRIMARY KEY,
    from_ VARCHAR(100),
    to_ VARCHAR(100),
    journey_time VARCHAR(50),
    fare_in_inr INT,
    airline VARCHAR(100)
);


-- Step 4: Create user detail table
CREATE TABLE IF NOT EXISTS user_detail (
    username VARCHAR(100),
    email_address VARCHAR(100) PRIMARY KEY,
    password VARCHAR(100)
);

-- Step 5: Flight details
USE flight;

INSERT INTO flightbook (flightno, from_, to_, journey_time, fare_in_inr, airline) VALUES
(1001, 'Delhi', 'Mumbai', '2h 15m', 5200, 'IndiGo'),
(1002, 'Chennai', 'Kolkata', '3h 0m', 6500, 'Air India'),
(1003, 'Hyderabad', 'Pune', '1h 30m', 3200, 'SpiceJet'),
(1004, 'Lucknow', 'Ahmedabad', '2h 45m', 4500, 'Vistara'),
(1005, 'Bangalore', 'Jaipur', '2h 30m', 5900, 'Go First'),
(1006, 'Pune', 'Delhi', '1h 50m', 4100, 'Akasa Air'),
(1007, 'Mumbai', 'Chennai', '2h 0m', 4700, 'AirAsia'),
(1008, 'Ahmedabad', 'Hyderabad', '2h 20m', 5300, 'Alliance Air'),
(1009, 'Kolkata', 'Bangalore', '3h 10m', 7200, 'IndiGo'),
(1010, 'Jaipur', 'Lucknow', '1h 40m', 3100, 'SpiceJet'),
(1011, 'Delhi', 'Chennai', '3h 0m', 6700, 'Vistara'),
(1012, 'Mumbai', 'Hyderabad', '1h 20m', 3000, 'Go First'),
(1013, 'Chennai', 'Pune', '2h 15m', 4800, 'AirAsia'),
(1014, 'Hyderabad', 'Delhi', '2h 40m', 5500, 'IndiGo'),
(1015, 'Lucknow', 'Bangalore', '2h 25m', 5200, 'Air India'),
(1016, 'Ahmedabad', 'Mumbai', '1h 35m', 3300, 'SpiceJet'),
(1017, 'Kolkata', 'Jaipur', '3h 30m', 6900, 'Vistara'),
(1018, 'Pune', 'Chennai', '2h 5m', 4600, 'Akasa Air'),
(1019, 'Jaipur', 'Ahmedabad', '1h 15m', 2800, 'Alliance Air'),
(1020, 'Delhi', 'Bangalore', '2h 55m', 6100, 'IndiGo'),
(1021, 'Mumbai', 'Lucknow', '2h 20m', 5000, 'Air India'),
(1022, 'Chennai', 'Delhi', '3h 10m', 6600, 'Go First'),
(1023, 'Hyderabad', 'Kolkata', '2h 35m', 4900, 'AirAsia'),
(1024, 'Pune', 'Jaipur', '2h 10m', 4200, 'Vistara'),
(1025, 'Ahmedabad', 'Chennai', '3h 5m', 7300, 'IndiGo'),
(1026, 'Lucknow', 'Mumbai', '2h 25m', 5600, 'SpiceJet'),
(1027, 'Delhi', 'Pune', '1h 45m', 3900, 'Akasa Air'),
(1028, 'Kolkata', 'Ahmedabad', '3h 20m', 7100, 'Air India'),
(1029, 'Bangalore', 'Chennai', '1h 25m', 3100, 'Go First'),
(1030, 'Jaipur', 'Hyderabad', '2h 30m', 5700, 'Alliance Air'),
(1031, 'Chennai', 'Lucknow', '3h 15m', 6800, 'AirAsia'),
(1032, 'Mumbai', 'Pune', '1h 10m', 2500, 'IndiGo'),
(1033, 'Delhi', 'Kolkata', '2h 50m', 6000, 'Vistara'),
(1034, 'Hyderabad', 'Bangalore', '1h 20m', 2700, 'SpiceJet'),
(1035, 'Pune', 'Ahmedabad', '1h 55m', 3900, 'Air India'),
(1036, 'Lucknow', 'Chennai', '2h 45m', 5400, 'Akasa Air'),
(1037, 'Ahmedabad', 'Delhi', '2h 0m', 4400, 'Go First'),
(1038, 'Bangalore', 'Lucknow', '3h 10m', 7300, 'IndiGo'),
(1039, 'Kolkata', 'Pune', '2h 55m', 5900, 'AirAsia'),
(1040, 'Jaipur', 'Mumbai', '2h 5m', 5000, 'SpiceJet'),
(1041, 'Chennai', 'Ahmedabad', '3h 0m', 7000, 'Air India'),
(1042, 'Hyderabad', 'Jaipur', '2h 15m', 4700, 'Vistara'),
(1043, 'Pune', 'Lucknow', '2h 30m', 5500, 'Alliance Air'),
(1044, 'Delhi', 'Hyderabad', '2h 25m', 5100, 'IndiGo'),
(1045, 'Mumbai', 'Bangalore', '1h 45m', 3900, 'Akasa Air'),
(1046, 'Chennai', 'Pune', '2h 10m', 4800, 'AirAsia'),
(1047, 'Ahmedabad', 'Kolkata', '3h 20m', 6900, 'SpiceJet'),
(1048, 'Lucknow', 'Delhi', '1h 30m', 3100, 'Air India'),
(1049, 'Bangalore', 'Ahmedabad', '2h 35m', 5300, 'Vistara'),
(1050, 'Jaipur', 'Chennai', '2h 45m', 5800, 'Go First');


