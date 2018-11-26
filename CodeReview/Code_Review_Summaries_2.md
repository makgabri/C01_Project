# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

I reviewed part of the cmdTerminal.java as well as some of the GUI related code. I found that there were multiple main methods defined, and the database wasn't being initialized in the cmdTerminal, so it wasn't clear where the code was supposed to run from and there were errors resulting from the lack of a database. After discussing with my team, I refactored the code so that it was clearer where the code was supposed to run from. I also found that there we some areas where it wasn't clear how the code was structured, specifically with the GUI code, since there was a lack of documentation regarding the design. As a team we discussed this matter, and clarified what the design of the GUI is.

## Brian

I reviewed TEQSignUpWindow.java, UTSCSignUpWindow.java, TEQChooseWindow.java, UTSCChooseWindow.java. I noticed that TEQSignUpWindow.java has nearly identical code as UTSCSignUpWindow.java and that TEQChooseWindow.java has nearly identical code as UTSCChooseWindow.java. I would suggest using an interface and abstract class to improve the design of the code. We could use an interface called ChooseWindow, SignUpWindow to structure the function definitions and use an abstract class called ChooseWindowImpl, SignUpWindowImpl to implement those function definitions in the interface. Then we could have UTSCChooseWindow.java, UTSCSignUpWindow.java, TEQChooseWindow.java, and TEQSignUpWindow.java inherit from those abstract classes so that only functions are not similar are defined in the classes. In terms of the code style, there is some debug statements and dead code.          

## Tylar

I review QueryPage.java. Overall, there are lack of javadoc and some code were repeated. The class can use the Composite and Decorator Design Patterns. Moreover, there are no method discription, more commentations will be helpful to understand the code. In addition, the actionPerformed method is doing multiple work and it is a huge method. Consider to break down into subclass will be helpful for further maintenance.

## Gabrian 

I review AlreadyUploadWindow.java, NotExcelWindow.java and NotUploadedWindow.java. In general, these classes were repeated. The design could have been implemented so that there was one class that would create a message window and whenever a message needed to be sent to the user, the class could be called with the message as the parameter instead of re-constructing a window for each message. This would not have created any errors and was not a bad approach, however the design could have been better so that less code would be written leading to an increase in efficiency. In conclusion, I think that the design should have used one class instead of three. This most likely originated from the fact that none of us are fluent in swing and it was a nice experience to learn it.
