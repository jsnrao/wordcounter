# wordcounter

Requirements
For building and running the application you need:

JDK 1.17
Maven 4
Running the application locally
There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in WordCounterServiceApplication class from IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run

Swagger end point : http://localhost:8090/swagger-ui/index.html
  API1:  Add word  
 
 curl -X 'POST' \
  'http://localhost:8090/v1.0/word/add?word=flor%20flower%20blume%20flower%20flowee123' \
  -H 'accept: */*'
  
 API2: Retrieve Word 
 
 curl -X 'GET' \
  'http://localhost:8090/v1.0/word/retrieve/{word}?Word=flor' \
  -H 'accept: */*'
  
 Implemented thread safe collections and thread safe add word method, 
 
 Implemented loose coupling repository  in future if we want to fetch data from database or other service we can change seemlessly 
 
 Implemented TransactionClient class for fetching transaction stub data. Instead of stub if we want to call other service or db wer can simply change client calls here.
 
 Sample input tried :we can give input single word or multiple words with space as delimitor by default, in case if we want to change the delimiter we can read from consul properties and split the words before adding
 
 While Adding new word if we pass single word with special characters or numbers then we will get as invalid word.
 
 While Adding multiple words it will check for valid words and adds to the concurent hashmap and invalid words will be skipped and confirmation message will be sent as Word has been added succesfully
 
 If we are trying to retrieve new word which doesnt exist in our stub repository then it will throw word not found exception.
 
 Implemented spring boot custom validator framework to handle exception.
 
 Implemented testcases using spock covered negative scenarios as well.
  
