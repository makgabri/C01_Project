# Team Code Review Summaries

(Note: Please see Code_Review_Guidelines.md for information about how we conducted our code review)

## Mohammad

I reviewed part of the cmdTerminal.java as well as some of the GUI related code. I found that there were multiple main methods defined, and the database wasn't being initialized in the cmdTerminal, so it wasn't clear where the code was supposed to run from and there were errors resulting from the lack of a database. After discussing with my team, I refactored the code so that it was clearer where the code was supposed to run from. I also found that there we some areas where it wasn't clear how the code was structured, specifically with the GUI code, since there was a lack of documentation regarding the design. As a team we discussed this matter, and clarified what the design of the GUI is.

## Brian

Stuff

## Tylar

## Gabrian

I review AlreadyUploadWindow.java, NotExcelWindow.java and NotUploadedWindow.java. In general, these classes were repeated. The design could have been implemented so that there was one class that would create a message window and whenever a message needed to be sent to the user, the class could be called with the message as the parameter instead of re-constructing a window for each message. This would not have created any errors and was not a bad approach, however the design could have been better so that less code would be written leading to an increase in efficiency. In conclusion, I think that the design should have used one class instead of three. This most likely originated from the fact that none of us are fluent in swing and it was a nice experience to learn it.
