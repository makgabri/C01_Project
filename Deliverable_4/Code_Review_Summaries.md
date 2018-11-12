# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

During my code review sessions I reviewed various classes having to do with database insertion and set up related to templates, as well as the representation of templates within our project. The majority of the mistakes that I found were minor and easily fixable. An example was the redundant use of an SQL INSERT statement where the code was inserting into all the columns, but also specified the names of all the columns being inserted into which was not necessary. Another mistake was creating an entirely new database for templates, rather than inserting into the existing database. Other mistakes were just things like ambiguity as to what a method or function was supposed to be used for. Overall, the mistakes were not drastic and are easily fixable, and were more a result of inexperience with things like SQL and SQLite rather than anything else.

## Brian

During the code review sessions, I reviewed Organization.java, TEQStaff.java, User.java, OrganizationTest.java, and TEQStaffTest.java. What I found was that the coding style and formatting was inconsistant. I could understand the code's functionality by reading it, but there were sometimes dead code and different spacing between functions. I also found the design could also be improved. There were some parts that were copy pasted into other classes and some parts that breaks SOLID occasionally. Overall, the code and test cases are understandable and the code style/formatting can easily be fixed. Just the improvement of design may require communication between group members.     
