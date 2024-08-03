--1
SELECT
    DEPARTMENT_ID,
    LISTAGG(FIRST_NAME || '_' || LAST_NAME ||'; ')
WITHIN GROUP ( ORDER BY  EMPLOYEE_ID) AS DEPARTMENTS_OF_WORKERS
FROM
    EMPLOYEES
GROUP BY DEPARTMENT_ID;


--2
SELECT
    employee_id,
    job_id,
    hire_date,
    salary,
    LAG(salary, 1) OVER (PARTITION BY job_id ORDER BY hire_date) AS previous_salary,
    LEAD(salary, 1) OVER (PARTITION BY job_id ORDER BY hire_date) AS next_salary
FROM
    employees
ORDER BY
    job_id, hire_date;


--3
WITH ranked_salaries AS (
  SELECT
    e.employee_id,
    e.first_name,
    e.last_name,
    e.department_id,
    e.salary,
    ROW_NUMBER() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS salary_rank,
    COUNT(*) OVER (PARTITION BY e.department_id) AS dept_count
  FROM
    employees e
)
SELECT
  rs.employee_id,
  rs.first_name,
  rs.last_name,
  d.department_name,
  rs.salary,
  rs.salary_rank
FROM
  ranked_salaries rs
JOIN
  departments d ON rs.department_id = d.department_id
WHERE
  rs.salary_rank > 1
  AND rs.dept_count > 1
ORDER BY
  d.department_name,
  rs.salary_rank;


--4
SELECT
    EMPLOYEE_ID,
    FIRST_NAME,
    LAST_NAME,
    HIRE_DATE,
    EXTRACT(YEAR FROM HIRE_DATE) AS HIRE_YEAR
FROM
    EMPLOYEES
ORDER BY
    HIRE_YEAR;


--5
SELECT
    FIRST_NAME,
    LAST_NAME,
    SALARY,
    LAG(SALARY, 1) OVER (ORDER BY HIRE_DATE) AS PREVIOUS_SALARY,
    LEAD(SALARY, 1) OVER (ORDER BY HIRE_DATE) AS NEXT_SALARY
FROM
    EMPLOYEES
ORDER BY
    HIRE_DATE;






























