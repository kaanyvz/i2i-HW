/*

1-) Write an SQL query that selects employee’s id, employee’s first name,
employee’s last name and employee’s number of months from hire_date to today for all employees.
(Hint:MONTHS_BETWEEN)


*/

SELECT EMPLOYEE_ID,
       FIRST_NAME,
       LAST_NAME,
       TRUNC(MONTHS_BETWEEN(SYSDATE, HIRE_DATE), 1) AS MONTHS_SINCE_HIRE
FROM EMPLOYEES;


/*

2-) Write a query that displays the grade of all employees based on the value of the column
JOB_ID, using the following data: (Use DECODE)

*/

SELECT
    FIRST_NAME,
    LAST_NAME,
    JOB_ID,
    DECODE(JOB_ID,  'AD_PRES', 'A',
                    'ST_MAN', 'B',
                    'IT_PROG', 'C',
                    'SA_REP', 'D',
                    'ST_CLERK', 'E',
    'O') AS GRADE

FROM EMPLOYEES
GROUP BY JOB_ID, FIRST_NAME, LAST_NAME
ORDER BY JOB_ID;


/*

3-) Write a query for SQL02-EX-02(previous question) with using CASE WHEN.

*/

SELECT FIRST_NAME,
       LAST_NAME,
       JOB_ID,
        CASE JOB_ID
           WHEN 'AD_PRES' THEN 'A'
           WHEN 'ST_MAN' THEN 'B'
           WHEN 'IT_PROG' THEN 'C'
           WHEN 'SA_REP' THEN 'D'
           WHEN 'ST_CLERK' THEN 'E'
        ELSE 'O'
        END AS GRADE
FROM EMPLOYEES
ORDER BY JOB_ID;



/*

4-) Write a query that displays the employee number and last name of all employees
who work in a department with any employee whose last name contains a “i”.

*/
SELECT EMP.EMPLOYEE_ID, EMP.LAST_NAME
FROM EMPLOYEES EMP
WHERE EMP.DEPARTMENT_ID IN (SELECT E2.DEPARTMENT_ID
                            FROM EMPLOYEES E2
                            WHERE EMP.LAST_NAME LIKE '%i%');




/*

5-)
•	Create a table for MY_EMP_TABLE with following columns
•	Insert following rows,
•	Update salary with 1.10 times of salary value
•	Delete rows which first_name is David
•	Truncate table.

*/

--1
CREATE TABLE MY_EMP_TABLE (
    EMP_ID NUMBER(10) NOT NULL,
    LAST_NAME VARCHAR2(256),
    FIRST_NAME VARCHAR2(256),
    SALARY NUMBER(10)
);

--2
INSERT INTO MY_EMP_TABLE VALUES (1, 'Black', 'John', 1100);
INSERT INTO MY_EMP_TABLE VALUES (2, 'White', 'Kent', 1300);
INSERT INTO MY_EMP_TABLE VALUES (3, 'Orange', 'David', 1700);
INSERT INTO MY_EMP_TABLE VALUES (4, 'Pink', 'Alissa', 1900);

--3
UPDATE MY_EMP_TABLE EMP
SET EMP.SALARY = (EMP.SALARY*0.1) + EMP.SALARY;

--4
DELETE FROM MY_EMP_TABLE
WHERE FIRST_NAME = 'David';

--5
TRUNCATE TABLE MY_EMP_TABLE;










