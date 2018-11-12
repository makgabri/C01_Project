# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

During my code review sessions I reviewed various classes having to do with database insertion and set up related to templates, as well as the representation of templates within our project. The majority of the mistakes that I found were minor and easily fixable. An example was the redundant use of an SQL INSERT statement where the code was inserting into all the columns, but also specified the names of all the columns being inserted into which was not necessary. Another mistake was creating an entirely new database for templates, rather than inserting into the existing database. Other mistakes were just things like ambiguity as to what a method or function was supposed to be used for. Overall, the mistakes were not drastic and are easily fixable, and were more a result of inexperience with things like SQL and SQLite rather than anything else.

## Anh
I reviewed the parsingExcel class, mainly checking whether the implementation is susceptibloe to bugs, as well as any possible redundancy or optimization of the algorithm. Overall, the codes needs to be commented , as there is a triple nested loop, as more than 3 magic numbers. Most other mistakes are easily fixable. An addidtional check is needed for non negative numbers in gettemplate by index and get template by line numbers, as it might throw an error. Printing templates should print by line, with one line containings all values of cell in the role, instead of the current method that prints each cell on an newline. The method for getting the line format for the data is a little bit confusing ( and might be redundant).
Also there are a lot of code commented out at the end of the file, so that might need to be removed.