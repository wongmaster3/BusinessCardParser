# BusinessCardParser

This application reads business card data that is stored as a text document. It parses the document and retrieves the name, phone number, and email on the business card. It uses an external library [Apache OpenNPL](https://opennlp.apache.org/) to do named entity recognition which is used in an algorithm to retrieve the name in the document. 

Comments are used in the code files to explain more about the different components and logic.

This application requires JDK 8 in order to run. 

## How to Run the Application
Clone the directory and go to the directory in the command line. In order to run any specific test, run the following command where `path/to/test/document` is a text document that contains the business card text: 

```
java -jar CardParser.jar < path/to/test/document
```

The given test examples and my own tests are stored under the folder `src/tests`. If you want to run the example tests, run the following commands:
```
java -jar CardParser.jar < src/tests/Example1
java -jar CardParser.jar < src/tests/Example2
java -jar CardParser.jar < src/tests/Example3
```

After running this command, the output should look something along the lines of:
```
Output:
Name: xxx
Phone: xxx
Email: xxx
```
