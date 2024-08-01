/*
1-)
•	Add a colum to employees table named MAX_SALARY.
•	Update MAX_SALARY with maximum salary amount with subquery.
•	Delete employee who have minimum salary using subquery.

*/

ALTER TABLE EMPLOYEES
    ADD MAX_SALARY NUMBER;

UPDATE EMPLOYEES
SET MAX_SALARY = (SELECT MAX(SALARY) FROM EMPLOYEES);

DELETE EMPLOYEES
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEES);


ROLLBACK;



/*
2-)

•	Define index (named DPR_NAME_IDX) on DEPARTMENT_NAME column of DEPARTMENTS table.
•	Define constraint (named CNSTR_SALARY) on employee salary. (Salary must be between 1000$ and 100.000$)
•	Drop defined index.
•	Enable, disable, drop defined constraint.

 */

--1
CREATE INDEX DPR_NAME_IDX
ON DEPARTMENTS (DEPARTMENT_NAME);

-- 2

ALTER TABLE EMPLOYEES
ADD CONSTRAINT CNSTR_SALARY CHECK ( SALARY BETWEEN 1000 AND 100000);

--3
DROP INDEX DPR_NAME_IDX;

--4
ALTER TABLE EMPLOYEES
ENABLE CONSTRAINT CNSTR_SALARY;

ALTER TABLE EMPLOYEES
DISABLE CONSTRAINT CNSTR_SALARY;

ALTER TABLE EMPLOYEES
DROP CONSTRAINT CNSTR_SALARY;



/*
3-)
 Create a table from EMPLOYEES with distinct department_id column.
 Add department_name to that table. With DEPARTMENTS table, update department_name for
 included department_ids and insert department_id and department_name values for not included rows.
 Use MERGE keyword.

*/



SELECT * FROM EMPLOYEES_DEPT;

CREATE TABLE EMPLOYEES_DEPT AS
SELECT DISTINCT DEPARTMENT_ID
FROM EMPLOYEES;

ALTER TABLE EMPLOYEES_DEPT
ADD DEPARTMENT_NAME VARCHAR2(256);

MERGE INTO EMPLOYEES_DEPT ED
USING DEPARTMENTS D
ON (ED.DEPARTMENT_ID = D.DEPARTMENT_ID)
WHEN MATCHED THEN
    UPDATE SET ED.DEPARTMENT_NAME = D.DEPARTMENT_NAME
WHEN NOT MATCHED THEN
    INSERT (DEPARTMENT_ID, DEPARTMENT_NAME)
    VALUES (D.DEPARTMENT_ID, D.DEPARTMENT_NAME);



/*
4-)
•	Firstly select first_name, last_name, job_id, department_id from employees table
whose job_id starts with ‘S’.
•	Additionally select job_title and min-max  salary amount.
•	Add  department_name to that query.
•	Lastly concat first_name and last_name with space as full_name alias and list with other
selected columns.

 */

SELECT
    EMP.FIRST_NAME || ' ' || EMP.LAST_NAME AS FULL_NAME,
    EMP.JOB_ID,
    J.JOB_TITLE,
    EMP.DEPARTMENT_ID,
    D.DEPARTMENT_NAME,
    (SELECT MIN(SALARY) FROM EMPLOYEES) AS MIN_SALARY,
    (SELECT MAX(SALARY) FROM EMPLOYEES) AS MAX_SALARY
FROM
    EMPLOYEES EMP
    INNER JOIN JOBS J ON EMP.JOB_ID = J.JOB_ID
    INNER JOIN DEPARTMENTS D ON EMP.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE
    EMP.JOB_ID LIKE 'S%';








