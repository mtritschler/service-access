package law.smart.library.books;

import law.smart.library.books.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.List;

@Component
public class ActiveMQBookServiceClient implements BookService {
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    private final Logger logger = LoggerFactory.getLogger(ActiveMQBookServiceClient.class);
    
    @Override
    @SuppressWarnings("unchecked")
    public List<BookDTO> getAllBooks() {
        String destinationName = "book-service/getAllBooks";
        logger.debug("Sending request to {}", destinationName);
        Message response = jmsTemplate.sendAndReceive(destinationName, Session::createMessage);
        ObjectMessage objectMessage = (ObjectMessage) response;
        List<BookDTO> result;
        try {
            result = (List<BookDTO>) objectMessage.getObject();
            logger.debug("Received list with {} books.", result.size());
        } catch (JMSException e) {
            throw new RuntimeException("Could not convert object message.", e);
        }
        return result;
    }

    
}
