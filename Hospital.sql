CREATE DATABASE HospitalProject;
USE HospitalProject;

CREATE TABLE Patients ( -- Dropped the address column as it wasn't really needed
patient_id INT PRIMARY KEY NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL, 
sex VARCHAR(1),
emer_contact VARCHAR(20) NOT NULL,
insurance_policy VARCHAR(20) NOT NULL
);

CREATE TABLE Admission (
patient_id INT NOT NULL,
admitdoc_id	INT NOT NULL,
nurse_id INT NOT NULL,
room_no INT NOT NULL,
diag_id INT NOT NULL,
treat_id INT NOT NULL,
doc_id INT NOT NULL,
other_id INT NOT NULL,
admin_date date NOT NULL,
dis_date date NOT NULL,
PRIMARY KEY (patient_id, admitdoc_id, nurse_id, room_no, diag_id, treat_id, doc_id, other_id, admin_date, dis_date),
FOREIGN KEY (patient_id) REFERENCES patients (patient_id),
FOREIGN KEY (admitdoc_id) REFERENCES admittingdoctor (employee_id),
FOREIGN KEY (nurse_id) REFERENCES Nurse (employee_id),
FOREIGN KEY (room_no) REFERENCES room (Room_ID),
FOREIGN KEY (diag_id) REFERENCES Diagnoses (diag_id),
FOREIGN KEY (treat_id) REFERENCES Treatment (treatment_id),
FOREIGN KEY (doc_id) REFERENCES employee (employee_id),
FOREIGN KEY (other_id) REFERENCES EMPLOYEE (employee_id)
);

CREATE TABLE EMPLOYEE (
employee_id INT PRIMARY KEY NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
emp_type VARCHAR(20) NOT NULL
);

CREATE TABLE ROOM (
Room_ID INT PRIMARY KEY NOT NULL,
admin_id INT NOT NULL,
FOREIGN KEY (admin_id) REFERENCES employee (employee_id)
);

CREATE TABLE Doctor (
employee_id INT NOT NULL PRIMARY KEY,
FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (employee_id)
);

CREATE TABLE Nurse (
employee_id INT PRIMARY KEY NOT NULL,
FOREIGN KEY (employee_id) REFERENCES EMPLOYEE (employee_id)
);

CREATE TABLE Technician (
employee_id INT PRIMARY KEY NOT NULL,
foreign key (employee_id) references EMPLOYEE (employee_id)
);

CREATE TABLE Admin (
employee_id INT PRIMARY KEY NOT NULL,
foreign key (employee_id) references EMPLOYEE (employee_id)
);

CREATE TABLE Staff (
employee_id INT PRIMARY KEY NOT NULL,
foreign key (employee_id) references EMPLOYEE (employee_id)
);

CREATE TABLE Treatment (
treatment_id INT primary key NOT NULL,
treatment_type bit, -- 0 for procedure, 1 for medication
treat_name VARCHAR(20)
-- ADD COLUMN FOR Diagnoses and tie wtih FK
);

CREATE TABLE Diagnoses (
diag_id INT NOT NULL PRIMARY KEY,
diag_name VARCHAR(20) NOT NULL 
);


CREATE TABLE AdmittingDoctor (
employee_id INT NOT NULL PRIMARY KEY,
FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);




