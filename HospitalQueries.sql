#Room Utilization
-- 1.1 List the rooms that are occupied, along with the associated patient names and the date the patient was admitted.
SELECT Room.Room_ID, Admission.admin_date, patients.patient_id, patients.first_name, patients.last_name
FROM Room
INNER JOIN Admission ON Room.Room_ID = Admission.room_no
INNER JOIN Patients ON Admission.patient_id = Patients.patient_id;
-- 1.2. List the rooms that are currently unoccupied
SELECT Room.Room_ID
FROM Room
WHERE NOT EXISTS
(SELECT * FROM Admission
WHERE Admission.room_no = Room.Room_ID);

-- 1.3. List all rooms in the hospital along with patient names and admission dates for those that are occupied.
SELECT Room.Room_ID, Patients.first_name, Patients.last_name, Admission.admin_date
FROM Room
LEFT JOIN Admission ON Room.Room_ID = Admission.room_no
LEFT JOIN Patients ON Admission.patient_id = Patients.patient_id;

#Patient Information
--  2.1. List all patients in the database, with full personal information.
SELECT *
FROM patients;

-- 2.2. List all patients currently admitted to the hospital. List only patient identification number and name.
SELECT patients.patient_id, patients.first_name, patients.last_name
FROM patients
INNER JOIN Admission ON patients.patient_id = Admission.patient_id;

-- 2.3. List all patients who were discharged in a given date range. List only patient identification number and name.
SELECT patients.patient_id, patients.first_name, patients.last_name, admission.dis_date
FROM patients
INNER JOIN admission ON patients.patient_id = admission.patient_id
WHERE admission.dis_date BETWEEN '2022-12-03' AND '2023-05-01';

-- 2.4. List all patients who were admitted within a given date range. List only patient identification number and name.
SELECT patients.patient_id, patients.first_name, patients.last_name, admission.admin_date
FROM patients
INNER JOIN admission ON patients.patient_id = admission.patient_id
WHERE admission.admin_date BETWEEN '2023-01-01' AND '2023-12-31';

-- 2.5. For a given patient (either patient identification number or name), list all admissions to the hospital along with the diagnosis for each admission.
SELECT patients.patient_id, diagnoses.diag_id, diagnoses.diag_name
FROM patients
INNER JOIN admission ON patients.patient_id = admission.patient_id
INNER JOIN diagnoses ON admission.diag_id = diagnoses.diag_id
WHERE patients.patient_id OR patients.last_name;

-- 2.6 For a given patient (either patient identification number or name), list all treatments that were administered. Group treatments by admissions. List admissions in descending chronological order, and list treatments in ascending chronological order within each admission.
SELECT patients.patient_id, treatment.treatment_id, treatment.treat_name, admission.admin_date
FROM patients
INNER JOIN admission ON patients.patient_id = admission.patient_id
INNER JOIN treatment ON admission.treat_id = treatment.treatment_id
WHERE patients.patient_id OR patients.last_name
ORDER BY admission.admin_date DESC;

-- 2.7. List patients who were admitted to the hospital within 30 days of their last discharge date. For each patient list their patient identification number, name, diagnosis, and admitting doctor.
-- NOT WORKING
SELECT distinct patients.patient_id, patients.first_name, patients.last_name, diagnoses.diag_name, employee.first_name, employee.last_name
FROM patients
INNER JOIN admission on patients.patient_id = admission.patient_id
INNER JOIN diagnoses on admission.diag_id = diagnoses.diag_id
INNER JOIN employee on admission.admitdoc_id = employee.employee_id
WHERE admission.admin_date <= date_add(admission.dis_date, interval(1 MONTH));

-- 2.8 For each patient that has ever been admitted to the hospital, list their total number of admissions, average duration of each admission, longest span between admissions, shortest span between admissions, and average span between admissions.
SELECT patients.patient_id, patients.first_name, patients.last_name,
count(*) as total_admissions,
AVG(DATEDIFF(admission.dis_date, admission.admin_date)) AS avg_duration,
datediff(MAX(admission.admin_date), MIN(admission.admin_date)) AS longest_span,
datediff(MIN(CASE WHEN a2.admin_date > admission.admin_date THEN a2.admin_date ELSE NULL END),
admission.admin_date) AS shortest_span,
AVG(datediff(admission.admin_date, a2.admin_date)) AS avg_span
FROM admission
JOIN patients ON admission.patient_id = patients.patient_id
LEFT JOIN admission a2 ON a2.patient_id = admission.patient_id AND a2.admin_date < admission.admin_date
GROUP BY patients.patient_id, patients.first_name, patients.last_name;

#Diagnosis and Treatment Information

-- 3.1. List the diagnoses given to patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.
SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) as occurences
FROM diagnoses
JOIN admission ON diagnoses.diag_id = admission.diag_id
GROUP BY diagnoses.diag_id, diagnoses.diag_name
ORDER BY occurences DESC;

-- 3.2. List the diagnoses given to hospital patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.
SELECT d.diag_id, d.diag_name, count(*) as OCCURENCES
FROM diagnoses d
JOIN admission a ON d.diag_id = a.diag_id
GROUP BY d.diag_id, d.diag_name
ORDER BY occurences DESC;

-- 3.3. List the treatments performed on admitted patients, in descending order of occurrences. List treatment identification number, name, and total number of occurrences of each treatment.
SELECT treatment.treatment_id, treatment.treat_name, count(*) as occurences
FROM treatment
JOIN admission ON treatment.treatment_id = admission.treat_id
GROUP BY treatment.treatment_id, treatment.treat_name
ORDER BY occurences DESC;

-- 3.4. List the diagnoses associated with patients who have the highest occurrences of admissions to the hospital, in ascending order or correlation.
SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) AS occurences
FROM diagnoses
JOIN admission ON diagnoses.diag_id = admission.diag_id
GROUP BY diagnoses.diag_id, diagnoses.diag_name
HAVING count(DISTINCT admission.patient_id) >= (SELECT COUNT(*) FROM admission GROUP BY patient_id ORDER BY COUNT(*) DESC LIMIT 1)
ORDER BY occurences ASC;


-- 3.5. For a given treatment occurrence, list the patient name and the doctor who ordered the treatment.
SELECT patients.first_name, patients.last_name
from patients
inner join admission on patients.patient_id = admission.patient_id
inner join doctor on admission.doc_id = doctor.employee_id;


#Employee Information

-- 4.1. List all workers at the hospital, in ascending last name, first name order. For each worker, list their, name, and job category.
SELECT employee.first_name, employee.last_name, employee.emp_type
FROM employee
ORDER BY last_name ASC, first_name ASC;

-- 4.2. List the primary doctors of patients with a high admission rate (at least 4 admissions within a one-year time frame).
#No admitting docotr has had more than four admissions in 1 year
SELECT distinct admission.admitdoc_id, admittingdoctor.employee_id, patients.patient_id
from admission
JOIN admittingdoctor on admission.admitdoc_id = admittingdoctor.employee_id
JOIN patients on admission.patient_id = patients.patient_id
WHERE patients.patient_id IN (
	SELECT patient_id
    FROM admission
    WHERE admin_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR)
    GROUP BY patient_id
    HAVING COUNT(*) >= 4
    );
    
-- 4.3. For a given doctor, list all associated diagnoses in descending order of occurrence. For each diagnosis, list the total number of occurrences for the given doctor.
SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) as total_occurences
from diagnoses
join admission on diagnoses.diag_id = admission.diag_id
join patients on admission.patient_id = patients.patient_id
where admission.admitdoc_id = '201'
group by diagnoses.diag_id
order by total_occurences DESC;

-- 4.4. For a given doctor, list all treatments that they ordered in descending order of occurrence. For each treatment, list the total number of occurrences for the given doctor.
SELECT treatment.treatment_id, treatment.treat_name, count(*) as total_occurences
from treatment
join admission on treatment.treatment_id = admission.treat_id
join patients on admission.patient_id = patients.patient_id
where admission.doc_id = '204'
group by treatment.treatment_id
order by total_occurences DESC;

-- 4.5. List employees who have been involved in the treatment of every admitted patient.
SELECT employee.first_name, employee.last_name, employee.emp_type
from employee
where not exists (
	select distinct admission.patient_id
    from admission
    where not exists (
		select admission.other_id
        join treatment on admission.treat_id = treatment.treatment_id
        join employee on admission.doc_id = employee.employee_id
        join nurse on admission.nurse_id = nurse.nurse_id
        )
	)
   
        