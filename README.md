# fizzbuzz
A demo implementation of fizzbuzz API

This is a very simple server application. It generates the [fizzbuzz](https://en.wikipedia.org/wiki/Fizz_buzz) sequence up to a given number.

Stack:
- Spring Boot
- Spring Web
- Spring AOP
- Junit
- Mockito
- Lombok
- Hibernate validator
- Maven

Usage:
Application can be run on an IDE (was developed on Intellij IDEA) or can be built as an executable file by [Maven](http://maven.apache.org/). <br>
To build: <i>mvn install</i><br>
To test: <i>mvn test</i><br>
To run jar file: <i>java -jar filename</i> <br>

"fizzbuzz-web-1.0.0.jar" is used to start the webservice.


API call:
The application can be reached on the following path: "http://localhost:8080/api/getFizzBuzzSequence/"
The endpoint accepts either:
- A JSON file with a single key-value pair. The key should be "highestNumber", the value is the last number of the requested sequence
- A number after "getFizzBuzzSequence/", which is the last number of the requested sequence. For example: "http://localhost:8080/api/getFizzBuzzSequence/5"

My choice of framework was Spring Boot. It was selected because its convention over configuration makes creating a simple 
webservice from scratch quite fast.  

The project is separated into two Maven modules - one for the service layer and one for the web layer. Only the web layer
has dependency on the service layer, to ensure that the service layer is compact and reusable. Layering is also used to
make the project easy to expand. 

Anemic domain model is used for this project - this is mainly due to my practical experience with this model, and I also
think that Spring framework promotes this kind of structure.

Testing is done mostly by unittests. I used TTD to write service layer methods, since they have a clear input and return
value. To test methods in the web layer, mocking were used. 

Logging is provided by Spring's AOP module. It was implemented for the separation of concerns, as the logged methods shouldn't
be responsible for logging as well.
