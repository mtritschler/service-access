package law.smart.library.books;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJms
public class Application {

    private final Logger logger = LoggerFactory.getLogger(Application.class);
    
//    @Bean
//    BrokerService brokerService() throws Exception {
//        BrokerService brokerService;
//        try {
//            String brokerURI = "broker:(tcp://localhost:61616)";
//            brokerService = BrokerFactory.createBroker(brokerURI, true);
//        } catch (Exception e) {
//            logger.error("Could not initialize broker.", e);
//            throw e;
//        }
//        brokerService.setBrokerName("dev.smartlaw.de");
//        brokerService.setDataDirectoryFile(new File("./target/activemq-data"));
//        brokerService.setCacheTempDestinations(true);
//        ManagementContext managementContext = new ManagementContext();
//        managementContext.setCreateConnector(false);
//        brokerService.setManagementContext(managementContext);
//        return brokerService;
//    }

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
        SpringApplication.run(Application.class, args);
    }
}
