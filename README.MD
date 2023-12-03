# Word Counter Take Home Test ReadMe

This is a word counter program written in Java (with Maven) for the purpose of an interview take home test.

## Assumptions

1. Based on the challenge description, i.e. "ensure your solution should be production ready", I am assuming that we want to aim for a simple and quick but extensible solution therefore, I have opted to use OOP to take advantage of abstraction and encapsulation that Classes provide, which can help to ensure the code is more reusable, readable and maintainable as we hypothetically build on top of it.
2. The text file to read from is in plain text and not in another format, i.e. csv.
3. The text file will be read from within the code and not from an external source.
4. The text file may contain words or be empty. For this reason, I have included two text files containing words and another empty text file, i.e. `first_sample_file.txt`, `second_sample_file.txt` and `empty_sample_file.txt` for testing purposes.
5. `first_sample_file.txt` and `second_sample_file.txt` contains the same words but in different order for test purposes as per the requirement document, i.e. "The results should be consistent between two files which contain the exact same words in the same frequency but in a different order."

## Known edge-cases and trade-offs

1. For the purpose of this exercise, I took the tradeoff of simplicity vs. performance in order to avoid over-engineering and align with Adaptavist's culture of trying things/building in iterations, making mistakes and learning fast from them. Currently we are assuming that the text file is reasonably sized. However, there are probable scenarios where we need to process larger text files, in which case, the current solution might present scaling issues.

To solve this, I would look to iterate the current solution and optimise the performance by implementing a streaming algorithm, whereby we read and process the data in chunks whilst avoiding loading the entire file into memory at once. This can be achieved by using the `FileInputStream` [class](https://www.geeksforgeeks.org/java-io-fileinputstream-class-java/) or a similar approach.

2. As per assumptions no. 2 made above; in this solution, we are reading the text file directly from the code file. However, it is impossible that in a future iteration, we'd be required to read files from an external source.

In this event, I would look to refactor the code to handle input and data validation and sanitation to make the solution more robust and secure, so for example, in the case of the text being derived from a user input, we could achieve validation and sanitization with libraries such as [OWASP Java HTML Sanitizer](https://github.com/OWASP/java-html-sanitizer).

## Dependencies

The following third-party libraries are used in this programme:

- `JUnit`: to run unit tests.
- `Mockito`: for mocking.

## How to Run

1. **Setup**

   Before running the program, make sure you have Java 17 installed on your machine.


2. **Installation**

   - Clone the file onto your local machine => `https://github.com/sholabuilds/java-word-counter-app.git`    
   - Run `mvn clean install` to resolve dependencies and build the project.


3. **Running the Word Counter**

   In the `src/main/java` folder, there is a `textFileProcessor` module with a `Main` class pre-defined to run the code.

   - To run the program with the first text file with words, simply type `mvn clean compile exec:java -Dexec.mainClass="com.adaptavist.textfileprocessor.Main" -Dexec.args="src/first_sample_file.txt"` in the terminal and press `Enter`.
   - To run the program with the second text file with words (in reverse order), simply type `mvn clean compile exec:java -Dexec.mainClass="com.adaptavist.textfileprocessor.Main" -Dexec.args="src/second_sample_file.txt"` in the terminal and press `Enter`.
   - To run the program with the text file without words, simply type `mvn clean compile exec:java -Dexec.mainClass="com.adaptavist.textfileprocessor.Main" -Dexec.args="src/empty_sample_file.txt"` in the terminal and press `Enter`.


4. **Running the Tests**

    I've written some basic unit tests for each file using JUnit. To run the tests, navigate to the relevant test file and run the test.
