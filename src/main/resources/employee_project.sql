DROP TABLE IF EXISTS prev_employers;
DROP TABLE IF EXISTS employee_form;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS universities;
DROP TABLE IF EXISTS cities;




CREATE TABLE cities (
    city_id integer not null,
    city_name varchar(20) not null,
    PRIMARY KEY(city_id)
);

CREATE TABLE universities (
    university_id integer not null,
    university_name varchar(40) not null,
    PRIMARY KEY(university_id)
);

CREATE TABLE courses (
    course_id integer not null,
    course_name varchar(40) not null,
    PRIMARY KEY(course_id)
);


CREATE TABLE employee_form (
	e_form_id SERIAL,
	e_form_status int not null,
	e_form_date timestamp not null,
	f_name varchar(15) not null,
	l_name varchar(20) not null,
	b_day date not null,
	city_id integer not null,
	relocate_status int not null,
    profession int not null,
    schedule_status int not null,
    experience double precision,
    salary double precision,
    university_id integer not null,
    course_id integer not null,
	about text,
	mail varchar(30) not null,
    PRIMARY KEY(e_form_id),
    FOREIGN KEY(university_id) REFERENCES universities(university_id) ON DELETE RESTRICT,
    FOREIGN KEY(course_id) REFERENCES courses(course_id) ON DELETE RESTRICT,
	FOREIGN KEY(city_id) REFERENCES cities(city_id) ON DELETE RESTRICT
);

CREATE TABLE prev_employers (
	prev_employer_id SERIAL,
	e_form_id integer not null,
	organization varchar(40) not null,
	work_start date not null,
	work_end date not null,
	position varchar(40) not null,
	progress text,
	quit_reason text,
	PRIMARY KEY(prev_employer_id),
	FOREIGN KEY(e_form_id) REFERENCES employee_form(e_form_id) ON DELETE RESTRICT
);

CREATE INDEX idx_employee_form_id ON prev_employers(e_form_id);