package law.smart.library.search;

import law.smart.library.books.ActiveMQBookService;
import law.smart.library.books.BookService;
import law.smart.library.books.dto.BookDTO;
import law.smart.library.checkout.ActiveMQCheckoutService;
import law.smart.library.checkout.CheckoutService;
import law.smart.library.checkout.dto.ReceiptDTO;
import law.smart.library.search.config.ActiveMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.ConnectionFactory;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicSetupIT.Config.class, ActiveMQConfig.class})
public class BasicSetupIT {
    
    @Autowired
    private SearchService searchService;
    
    private final Logger logger = LoggerFactory.getLogger(BasicSetupIT.class);
    
    @Test
    public void simpleTest() {
        logger.info("Starting simpleTest");
        long start = System.currentTimeMillis();
        int requests = 1000;
        for (int i = 0; i < requests; i++) {
            searchService.findByAuthor("*");
        }
        logger.info("Sent {} requests in {}ms.", requests, System.currentTimeMillis() - start);
    }
    
    @Configuration
    public static class Config {
        
        @Bean
        JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
            return new JmsTemplate(connectionFactory);
        }
        
        @Bean
        ActiveMQBookService activeMQBookService() {
            return new ActiveMQBookService(new BookService() {
                @Override
                public List<BookDTO> getAllBooks() {
                    return Collections.emptyList();
                }
            });
        }
        
        @Bean
        ActiveMQCheckoutService activeMQCheckoutService() {
            
            return new ActiveMQCheckoutService(new CheckoutService() {
                
                private final ReceiptDTO RECEIPT_DTO = new ReceiptDTO();
                
                @Override
                public ReceiptDTO checkout(String id) {
                    return RECEIPT_DTO; 
                }

                @Override
                public ReceiptDTO getStatus(String id) {
                    return RECEIPT_DTO;
                }
            });
        }
        
    }
    
}
