# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

During my code review sessions I reviewed various classes having to do with database insertion and set up related to templates, as well as the representation of templates within our project. The majority of the mistakes that I found were minor and easily fixable. An example was the redundant use of an SQL INSERT statement where the code was inserting into all the columns, but also specified the names of all the columns being inserted into which was not necessary. Another mistake was creating an entirely new database for templates, rather than inserting into the existing database. Other mistakes were just things like ambiguity as to what a method or function was supposed to be used for. Overall, the mistakes were not drastic and are easily fixable, and were more a result of inexperience with things like SQL and SQLite rather than anything else.