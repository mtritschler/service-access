package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class ActiveMQCheckoutClient implements CheckoutService {
    
    private final Logger logger = LoggerFactory.getLogger(ActiveMQCheckoutClient.class);
    
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public ReceiptDTO checkout(String id) {
        return sendAndReceive("checkout-service/checkout", session -> session.createObjectMessage(id));
    }

    @Override
    public ReceiptDTO getStatus(String id) {
        return sendAndReceive("checkout-service/getStatus", session -> session.createObjectMessage(id));
    }
    
    private ReceiptDTO sendAndReceive(String destinationName, MessageCreator messageCreator) {
        logger.debug("Sending request to {}", destinationName);
        Message response = jmsTemplate.sendAndReceive(destinationName, messageCreator);
        ObjectMessage objectMessage = (ObjectMessage) response;
        ReceiptDTO result;
        try {
            result = (ReceiptDTO) objectMessage.getObject();
            logger.debug("Received response {}", result);
        } catch (JMSException e) {
            throw new RuntimeException("Could not convert object message.", e);
        }
        return result;
    }
}
