# BusinessCardParser

This application reads business card data that is in a text document and parses each line in the document to 
retrieve the name, phone number, and email. It uses an external library [Apache OpenNPL](https://opennlp.apache.org/) to do named entity recognition which is used to retrieve the name in the document. 

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
