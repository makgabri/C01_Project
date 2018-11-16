# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

During my code review sessions I reviewed various classes having to do with database insertion and set up related to templates, as well as the representation of templates within our project. The majority of the mistakes that I found were minor and easily fixable. An example was the redundant use of an SQL INSERT statement where the code was inserting into all the columns, but also specified the names of all the columns being inserted into which was not necessary. Another mistake was creating an entirely new database for templates, rather than inserting into the existing database. Other mistakes were just things like ambiguity as to what a method or function was supposed to be used for. Overall, the mistakes were not drastic and are easily fixable, and were more a result of inexperience with things like SQL and SQLite rather than anything else.

## Brian

During the code review sessions, I reviewed Organization.java, TEQStaff.java, User.java, OrganizationTest.java, and TEQStaffTest.java. What I found was that the coding style and formatting was inconsistant. I could understand the code's functionality by reading it, but there were sometimes dead code and different spacing between functions. I also found the design could also be improved. There were some parts that were copy pasted into other classes and some parts that breaks SOLID occasionally. Overall, the code and test cases are understandable and the code style/formatting can easily be fixed. Just the improvement of design may require communication between group members.

## Anh
I reviewed the parsingExcel class, mainly checking whether the implementation is susceptibloe to bugs, as well as any possible redundancy or optimization of the algorithm. Overall, the codes needs to be commented , as there is a triple nested loop, as more than 3 magic numbers. Most other mistakes are easily fixable. An addidtional check is needed for non negative numbers in gettemplate by index and get template by line numbers, as it might throw an error. Printing templates should print by line, with one line containings all values of cell in the role, instead of the current method that prints each cell on an newline. The method for getting the line format for the data is a little bit confusing ( and might be redundant).
Also there are a lot of code commented out at the end of the file, so that might need to be removed.

## Tylar
I reviewed the cmdTerminal.java, DatabaseInsertHelper.java and DatabaseInsertHelperImpl.java. There are some parts of the code could be improved. For cmdTerminal.java, I think the class should be break down into small subclass to meet the single responsibility requirment. Moreover, the cmdTerminal should interac between User and Database, so it should use the database feature but there is none of that. In addition, more specific method description will be helpful for viwer to understand the code. For DatabaseInsertHelper.java and DatabaseInsertHelperImpl.java, I think more inline comments will be helpful to read the code. Furthermore, the method description for the insertUser is a little bit confused. The decription wrote return a String represent the userId. However, the method actully return a Map<String, String> which contain userId and creationDate. I think changing the method description become more detailed is necessary.  

## Gabrian
I reviewed the whole security package as well as DatabaseUpdateHelperImpl. There are parts of the code that have become redundant such as unnecessary if statements that make things more complicated. The benefits of removing these redundant code allows our code not only to be more clean, but it gives for more room to expand on. With more simpler lines, we would be looking at our design with a simpler mind and this way we can solve more complicated issues. Where as if we were to complicate our code now, it would be more difficult to solve complicated issues. However, the design of the code follows the guidelines and no major issues are present. Overall, from this code review, I can see that there are areas of the code that could be refined to become more efficient.

