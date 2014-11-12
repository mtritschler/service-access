package law.smart.library.search.config;

import law.smart.library.books.ActiveMQBookServiceClient;
import law.smart.library.books.BookService;
import law.smart.library.checkout.ActiveMQCheckoutClient;
import law.smart.library.checkout.CheckoutService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
@ComponentScan
public class ActiveMQConfig {
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

}
