INSERT INTO job_status ( status_id, status_description ) VALUES (1, 'Opened');
INSERT INTO job_status ( status_id, status_description ) VALUES (2, 'In Progress');
INSERT INTO job_status ( status_id, status_description ) VALUES (3, 'Completed');

INSERT INTO jobs ( pothole_id, employee_id, job_status, opened_date, closed_date ) VALUES ( 1, 1, 1, '2021-04-06', '2021-04-21');
INSERT INTO jobs ( pothole_id, employee_id, job_status, opened_date, closed_date ) VALUES ( 2, 1, 2, '2021-04-07', '2021-04-22');
INSERT INTO jobs ( pothole_id, employee_id, job_status, opened_date, closed_date ) VALUES ( 3, 2, 3, '2021-04-08', '2021-04-23');

