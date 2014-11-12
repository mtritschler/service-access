package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQCheckoutService implements CheckoutService {

    private final CheckoutService delegate;

    public ActiveMQCheckoutService() {
        this(new CheckoutServiceImpl());
    }

    public ActiveMQCheckoutService(CheckoutService checkoutService) {
        this.delegate = checkoutService;
    }

    @Override
    @JmsListener(destination = "checkout-service/checkout")
    public ReceiptDTO checkout(String id) {
        return delegate.checkout(id);
    }

    @Override
    @JmsListener(destination = "checkout-service/getStatus")
    public ReceiptDTO getStatus(String id) {
        return delegate.getStatus(id);
    }
}
