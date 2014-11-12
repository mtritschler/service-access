package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CheckoutServiceImpl implements CheckoutService {

    private final Map<String, ReceiptDTO> data = new HashMap<>();

    @Override
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
    public ReceiptDTO getStatus(String id) {
        return data.getOrDefault(id, new ReceiptDTO());
    }

    private boolean isAvailable(String id) {
        return data.containsKey(id);
    }
}
