# WallStreetWarriors

- **Brad Gardea**: PM, chief architect, API and software engineer
- **Dhruv Patel**: Architect, API and software engineer
- **Goncalo Ribeiro Mendes**: UX and software engineer
- **Mikhail Skazhenyuk**: UX and software engineer

**Wall Street Warriors** is an investing and financial literacy game designed to teach users about finance through gamifying investing.  ​
The application consists of Trading Contests, where all users are given the same starting budget, and a list of stocks in one industry to build a portfolio from.​
At the end of the contest time period, the user with the most valuable portfolio wins that contest.


## Auth and Release:

Authorization and releases can only be made by owners of the registered project on GCP (WallStreetWarriors), the project requires an API key and credentials file for Firestore and Finazon in order to be built, respectivley. These will either be provided ahead of time via email or upon request. 
Similarly, the project can be launched as a standalone application with the included jar file: `WallStreeWarriors-1.0-Complete.jar`


## Usage and test instructions:
### **To launch the application using the included .jar file:**

0. Ensure openjdk version 17 is installed onto your local machine, this can be tested via `java --version` in your command prompt (cmd)/terminal
1. Clone the repository onto your local device
2. Open the folder containing the jar file in cmd/terminal
3. Run `java -jar WallStreetWarriors-1.0-Complete.jar`
4. A console should open as well as a JavaSwing dialogue, use the application as desired

### **To launch the application by building it:**

0. Ensure openjdk version 17 is installed onto your local machine, this can be tested via `java --version` in your command prompt (cmd)/terminal
1. Add the files `Credentials.java` file into the `api` package and the `.json` file into `resources` folder
2. Open the project in IntelliJ
3. Set the src/tests folder as the Tests root
4. Set the src/main/java folder as the Sources root
5. Open src/main/java/app and navigate to Main.java
6. Launch the current file, a JavaSwing dialogue should open, use the application as desired
7. In order to run the Tests, right click on the src/tests folder and go to More/Debug and click "Run with coverage" to view coverage for project


### **A note about using the application**:
If the application is launched and there are no contests available, you need to run `src\main\java\common\CreateContest.java`, you may get errors in the console, these are due to errors with the API calling, the application will continue to execute, but some of the data for the "dummy" users may be incorrect. (See APIs)

Further, in order to test the Enrolled Contest to Completed Contest interaction, the 4th and 5th default contest's end time is set to 6 minutes after running the default contest initliaztion. 


### **APIs**
One of the API we are using for stock market data "Finazon" only allows for ~100 API calls per minute, some components may not function as expected without access to the API, the interactions that use these API calls are: Refreshing, modifying portfolio selection in AvaialbleContestsView, viewing portfolio selection in EnrolledContestsView and terminating contests (happens automatically when time is up).

The other API being used is Firebase's Firestore to use document based storage for entities. The project implements generic CRUD for modification of POJOs in Firestore. To build the application, a JSON key is required, this can be provided upon request. Alternativley, the .jar file included can be used to execute the application as a standalone executable, but you will be unable to create new contests as these require access to the Firestore API key.
