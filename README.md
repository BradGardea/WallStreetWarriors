# WallStreetWarriors

## Auth and Release:

Authorization and releases can only be made by owners of the registered project on GCP (WallStreetWarriors), the project requires an API key and credentials file for Firestore and Finazon in order to be built, respectivley. These will either be provided ahead of time via email or upon request. 
Similarly, the project can be launched as a standalone application with the included jar file: WallStreeWarriors-1.0-Complete


## Usage and test instructions:
### **To launch the application using the included .jar file, the following instructions explain how to:**

0. Ensure openjdk version 17 is installed onto your local machine, this can be tested via `java --version` in your command prompt (cmd)/terminal
1. Clone the repository onto your local device
2. Open the folder containing the jar file in cmd/terminal
3. Run `java -jar WallStreetWarriors-1.0-Complete`
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
The api we are using for stock market data "Finazon" only allows for ~100 API calls per minute, some components may not function as expected without access to the API, the interactions that use these API calls are: Refreshing, modifying portfolio selection in AvaialbleContestsView, viewing portfolio selection in EnrolledContestsView and terminating contests (happens automatically when time is up).
