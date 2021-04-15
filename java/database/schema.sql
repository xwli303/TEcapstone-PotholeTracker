BEGIN TRANSACTION;

DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS job_status;
DROP TABLE IF EXISTS potholes;
DROP TABLE IF EXISTS severity;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
        );
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$10$szgCbRXn5E2cDH3Z5QhITO.XxQtiM22cc3iOyuJE9vRSR3.AbOvVK','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('guest','$2a$10$1xR3SoLx.1p4FSn7Jq0kuuSOaFwkde9g4a8RdFkN94fOjGWJer5fa','ROLE_GUEST');
INSERT INTO users (username,password_hash,role) VALUES ('employee', '$2a$10$5lYDl/2.ahiJuZZQesqFauNA3CURYX6/IUvc/soEygLnbumrCDKjq', 'ROLE_EMPLOYEE');

CREATE TABLE severity (
        severity_id int NOT NULL,
        severity_description varchar(50) NOT NULL,
        CONSTRAINT PK_severity PRIMARY KEY (severity_id)
        );
INSERT INTO severity ( severity_id, severity_description ) VALUES ( 1, 'Bump In The Road' );
INSERT INTO severity ( severity_id, severity_description ) VALUES ( 2, 'Serious Thud' );
INSERT INTO severity ( severity_id, severity_description ) VALUES ( 3, 'Flat Tire' );
INSERT INTO severity ( severity_id, severity_description ) VALUES ( 4, 'Lost My Wheel' );
INSERT INTO severity ( severity_id, severity_description ) VALUES ( 5, 'Car Swallower' );

CREATE TABLE status (
        status_id int NOT NULL,
        status_description varchar(50) NOT NULL,
        CONSTRAINT PK_status PRIMARY KEY (status_id)
        );
INSERT INTO status ( status_id, status_description ) VALUES ( 1, 'Reported' );
INSERT INTO status ( status_id, status_description ) VALUES ( 2, 'Inspected' );
INSERT INTO status ( status_id, status_description ) VALUES ( 3, 'Repaired' );
 
CREATE TABLE potholes (
        pothole_id serial NOT NULL,
        address varchar(200),
        latitude float,
        longitude float,
        severity_id int,
        status_id int,
        date_reported DATE,
        date_inspected DATE,
        date_repaired DATE,
        user_id int,
        CONSTRAINT PK_potholes PRIMARY KEY (pothole_id)
        );
/*
INSERT INTO potholes ( latitude, longitude, severity_id, status_id, date_reported, user_id ) VALUES ( 39.9056, -75.1742, 3, 1, '2021-04-06',2 );
INSERT INTO potholes ( latitude, longitude, severity_id, status_id, date_reported, user_id ) VALUES ( 39.9470, -75.1444, 3, 1, '2021-04-06', 2 );
INSERT INTO potholes ( latitude, longitude, severity_id, status_id, date_reported, user_id ) VALUES ( 39.95221, -75.16851, 2, 1, '2021-04-06', 2 );
INSERT INTO potholes ( latitude, longitude, severity_id, status_id, date_reported, user_id ) VALUES ( 39.9613, -75.1465, 3, 1, '2021-04-06', 2 );
*/
ALTER TABLE potholes ADD FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE potholes ADD FOREIGN KEY (severity_id) REFERENCES severity(severity_id);
ALTER TABLE potholes ADD FOREIGN KEY (status_id) REFERENCES status(status_id);

CREATE TABLE job_status (
        status_id int NOT NULL,
        Status_description varchar(50) NOT NULL,
        CONSTRAINT PK_job_status PRIMARY KEY (status_id)
        );
INSERT INTO job_status ( status_id, status_description ) VALUES (1, 'Opened');
INSERT INTO job_status ( status_id, status_description ) VALUES (2, 'In Progress');
INSERT INTO job_status ( status_id, status_description ) VALUES (3, 'Completed');
        
CREATE TABLE jobs (
        job_id serial NOT NULL,
        pothole_id int NOT NULL,
        employee_id int NOT NULL,
        job_status int NOT NULL,
        opened_date DATE,
        closed_date DATE
        );
ALTER TABLE jobs ADD FOREIGN KEY (pothole_id) REFERENCES potholes(pothole_id);
ALTER TABLE jobs ADD FOREIGN KEY (job_status) REFERENCES job_status(status_id);

COMMIT TRANSACTION;

