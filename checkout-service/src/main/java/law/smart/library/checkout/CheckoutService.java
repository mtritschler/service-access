package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;

public interface CheckoutService {
    
    ReceiptDTO checkout(String id);
    
}
