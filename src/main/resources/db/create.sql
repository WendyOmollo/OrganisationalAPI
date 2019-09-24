SET MODE PostgreSQL;


--CREATE DATABASE organisation;

CREATE TABLE IF NOT EXISTS departments(
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
employees INTEGER,
);

CREATE TABLE IF NOT EXISTS news(
id int PRIMARY KEY auto_increment,
title VARCHAR,
details VARCHAR
);

CREATE TABLE IF NOT EXISTS employees(
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
department_id INTEGER,
);

CREATE TABLE IF NOT EXISTS classified_news(
id int PRIMARY KEY auto_increment,
title VARCHAR,
details VARCHAR,
);

CREATE TABLE IF NOT EXISTS departments_classifiedNews(
id int PRIMARY KEY auto_increment,
department_id INTEGER,
classifiedNews_id INTEGER,
);

