# service-access

## Requirements

* Apache ActiveMQ 5 (http://activemq.apache.org/)
* Java 8
* Maven 3

## How to run

1. Start an ActiveMQ broker (if necessary bootstrap before with `setup`)
    
        ~/ $ activemq setup
        ~/ $ activemq start

2. Make artifacts available for Maven dependency resolution
    
        ~/service-access $ mvn install
    
3. Start Spring Boot applications
    
        ~/service-access/book-service-activemq/book-service-activemq-impl $ mvn spring-boot:run & 
        ~/service-access/checkout-service-activemq/checkout-service-activemq-impl $ mvn spring-boot:run &
        ~/service-access/search-service-activemq/search-service-activemq-impl $ mvn spring-boot:run

4. (For now) Use CLI to interact with the search service. Available commands are

* list
* checkout <ID>
* quit
