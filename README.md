# CRUDSystem
This project is a Spring Boot CRUD system

<h2>Introduction</h2>

 - </h3>Overview of the project</h3>
 This project functions as a Database , it has 3 tables User,Printers,Toner. CRUDSystem is a system to keep track of users , printers and toners.
 
 - <h3>Technologies Used :</h3>
    <ul>
      <li>Spring Boot
      <li>Spring Security
      <li>Spring Data JPA
      <li>Thymeleaf
      <li>Validation
      <li>Hibernate
      <li>MySql
      <li>Maven
      <li>Html
      <li>CSS
     </ul>

<h2>Getting Started</h2>

<h3>Installation instructions</h3>
In order to see the project in action , First of all  , change the Prespective to Java EE then you will need eclipse with m2e plugin installed then in eclipse go to 
<Br>File->Import->Under Maven : Existing Maven Project -> Next -> <br>
Browse your computer to find the location of the unzipped project and hit Finish

<br> Also , you will need to install tomcat 9 , then you need to go in 
<br>File -> New -> Server or Other -> Under Server section choose Server -> Next -> Under Apache go to Tomcat 9.0 -> hit Next -> browse the location that you have installed Tomcat -> Finish

<br> Finally, you will need MySql , after the installation is complete log in as root and run the create_user.sql then log in as the user and as shown run the CRUDSystem.sql file.

<h3>How to run the project</h3>

When all of the above have been done , to run the Program go to src/main/java -> Sevg.CrudSystem -> Left click at CrudSystemApplication.java and Run as "Java Application".
After the build and the console finish running go to your browser and write http://localhost:8080/LoginPage .

<h4>The project is up and running.</h4>


<h2>Usage</h2>

<h3>Login Page</h3>

In Login Page you have 4 options :
1. Username : jdoe , Pass: qwer12#$ , role : employee
2. Username : jadoe , Pass: qwer12#$ , role : employee
3. Username : rsnow , Pass: qwer12#$ , role : Admin 
4. Username : lsand , Pass: qwer12#$ , role : Manager

Depending of the role the user has different capabilities.

<h3>User Guide</h3>

- Employees
Employees can only see the Users and Printers table, also they can change their password.

- Managers
Managers can see the Users table, Update them and change their Role, also they can change their password. Furthermore, they can see the Printers table,Update it and see their stocks. Finally, in stock they can add or remove a toner quantity and update the name

- Administrators
Admins can do the same as Managers including  add or deleting Users,Printers and Toners.


<h3>Notes</h3>
In the add user function the default password to log in is 123456 and the role is employee.


   
