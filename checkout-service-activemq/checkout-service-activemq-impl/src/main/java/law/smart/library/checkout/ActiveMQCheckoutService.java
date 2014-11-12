package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
class ActiveMQCheckoutService implements CheckoutService {
    
    private final Map<String, ReceiptDTO> data = new HashMap<>();
    
    @Override
    @JmsListener(destination = "checkout-service/checkout")
    public ReceiptDTO checkout(String id) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setBookId(id);
        if (isAvailable(id)) {
            receiptDTO.setReturnDate(LocalDate.now().plusWeeks(2));
            receiptDTO.setStatus(ReceiptDTO.LoanStatus.TAKEN);
            data.put(id, receiptDTO);
        }
        return receiptDTO;
    }

    @Override
    @JmsListener(destination = "checkout-service/getStatus")
    public ReceiptDTO getStatus(String id) {
        return data.getOrDefault(id, new ReceiptDTO());
    }

    private boolean isAvailable(String id) {
        return data.containsKey(id);
    }
}
