CREATE TABLE IF NOT EXISTS employee (
	id int NOT NULL auto_increment,
	name varchar(64),
	jobname varchar(64),
    PRIMARY KEY (id)
);

INSERT INTO employee (id,name,jobname) VALUES (1,'Jonathan','Developer');
INSERT INTO employee (id,name,jobname) VALUES (2,'Lee','Team Leader');
INSERT INTO employee (id,name,jobname) VALUES (3,'Phuong','Developer');
INSERT INTO employee (id,name,jobname) VALUES (4,'Alice','Tester');