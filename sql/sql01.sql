/*

1-) Write an SQL query that selects employee’s id, employee’s first name and employee’s department name
for all employees.

*/

SELECT  EMP.EMPLOYEE_ID,
        EMP.FIRST_NAME ,
        DEP.DEPARTMENT_NAME
FROM EMPLOYEES EMP INNER JOIN DEPARTMENTS DEP
        ON EMP.DEPARTMENT_ID = DEP.DEPARTMENT_ID;


/*

2-) Create a report that displays the employee’s id and their manager’s id.

*/

SELECT E1.EMPLOYEE_ID AS EMPLOYEE_ID,
       E2.EMPLOYEE_ID AS MANAGER_ID
FROM EMPLOYEES E1 LEFT JOIN EMPLOYEES E2
    ON E1.MANAGER_ID = E2.EMPLOYEE_ID
ORDER BY E1.EMPLOYEE_ID;

/*

3-) For example; first three character of PHONE_NUMBER column gives us a operator of employee.
Create a report that displays the operators and their total subscriber.
But we want two different displays with different queries.

*/

--FIRST QUERY
SELECT
    SUBSTR(PHONE_NUMBER, 1, 3) AS OPERATOR,
    COUNT(*) AS TOTAL
FROM EMPLOYEES
GROUP BY SUBSTR(PHONE_NUMBER, 1, 3);

--SECOND QUERY


/*

4-) Create a table (table name like HR.EMP) from HR.EMPLOYEES table.
Insert a new row to HR.EMP table and update this employee’s phone number and salary.
Delete your new row and display the HR.EMP table. Finally drop your table HR.EMP.

*/


CREATE TABLE EMP AS SELECT * FROM EMPLOYEES;
INSERT INTO EMP VALUES(
    250, 'Erman', 'İbrisim', 'erman@ibrisim.com', '5554443322', CURRENT_DATE, 'IT_PROG', 150000, 0, 124, 70
);

/*

5-) Select employees’ first name and last name as masked with “*” character as shown in sample output below.

*/

SELECT
    RPAD(SUBSTR(FIRST_NAME, 1, 2), LENGTH(FIRST_NAME), '*')
        ||
        ' '
        ||
    RPAD(SUBSTR(LAST_NAME, 1, 2), LENGTH(LAST_NAME), '*')
FROM EMPLOYEES;
























