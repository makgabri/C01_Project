## Tasks Descriptions
Below are descriptions of the tasks we undertook during our two sprints, including their dependancies if any.

* task 0: Set up a basic maven project structure for the team to work on
  * Story points: N/A
* task 1: Set up a database. The database will use SQLite for Java, with the dependency listed in the pom.xml file. There should be methods included to initialize the database so that the tables and schemas can be added.
  * Story points: 2 (We underestimated the amount of points)
  * Dependency: None
* task 2: Create a abstract User class that the other user roles can be based off of. This class should include all the base details that all the user roles will need, and implementations for methods that will be the same for all of them. The details of the user class are outline in the UML for the project.
  * Story points: 2
  * Dependency: None
task 3: Implement the UTSC Staff class according to the guidelines set out in the UML.
  * Story points: 3
  * Dependency: task 2
* task 4: Implement the TEQ Staff class according to the guidelines set out in the UML.
  * Story points: 3
  * Dependency: task 2
* task 5: Implement the Organization Staff class according to the guidelines in the UML.
  * Story points: 2
  * Dependency: task 2
* task 6: Create schemas for users, role types, and user passwords. The users schema should include a userID, email, roleID, creationDate, and booleans for the user's access to the system, and their upload status. The roleID column will reference an ID from the role types table. The role types table will store auto-incrementing IDs that correspond to role names (e.g. "UTSC", "TEQ"). The user password will match up the userID from the users table to a hashed password.
  * Story points: 5
  * Dependency: task 1
* task 7: Create a set of functions that can be used for creating user accounts. These functions will insert a user into the database and return the relevant details required to build a user object (such as userID and creation date).
  * Story points: 5
  * Dependency: task 6
* task 8: Create a function that will return true if an organization has uploaded data or not. This function will simply use the UPLOADED field that is stored with each user.
  * Story points: 1
  * Dependency: None

##### The following tasks were added after our meeting with our TA:

* task 9: Create a schema for one of the templates that is on piazza. We will use the employment template and create an exact schema of it in the database so that we can parse that specific template.
  * Story points: 3
  * Dependency: task 1
* task 10: Implement functions to parse all the information in the employment template into a java friendly format that will work with other functions. We are using the Apache POI library for parsing excel files in Java.
  * Story points: 5
  * Dependency: None