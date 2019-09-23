SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments(
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
employees INTEGER,
employee_id INTEGER,
classfiedNews_id INTEGER
);

CREATE TABLE IF NOT EXISTS news(
id int PRIMARY KEY auto_increment,
title VARCHAR,
details VARCHAR
);

CREATE TABLE IF NOT EXISTS employees(
id int PRIMARY KEY auto_increment,
name VARCHAR,
department_id INTEGER,
position VARCHAR,
classifiedNews_id INTEGER,
);

CREATE TABLE IF NOT EXISTS classified_news(
id int PRIMARY KEY auto_increment,
title VARCHAR,
details VARCHAR,
department_id INTEGER,
employee_id INTEGER,
);