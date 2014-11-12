package law.smart.library.search;

import law.smart.library.books.ActiveMQBookServiceClient;
import law.smart.library.books.BookService;
import law.smart.library.checkout.ActiveMQCheckoutClient;
import law.smart.library.checkout.CheckoutService;
import law.smart.library.checkout.dto.ReceiptDTO;
import law.smart.library.search.dto.SearchResultDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;
import java.util.List;
import java.util.Scanner;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJms
public class Application {

    @Bean
    BookService bookService() {
        return new ActiveMQBookServiceClient();
    }
    
    @Bean
    CheckoutService checkoutService() {
        return new ActiveMQCheckoutClient();
    }
    
    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        return factory;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        SearchService searchService = context.getBean(SearchService.class);
        CheckoutService checkoutService = context.getBean(CheckoutService.class);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            String command = strings[0];
            switch (command) {
                case "quit":
                    System.exit(0);
                case "list":
                    List<SearchResultDTO> result = searchService.findByAuthor("*");
                    System.out.println(result);
                    break;
                case "checkout":
                    ReceiptDTO receiptDTO = checkoutService.checkout(strings[0]);
                    System.out.println(receiptDTO.getStatus() + " until " + receiptDTO.getReturnDate());
                    break;
            }
        }
    }
    
}
