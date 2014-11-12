package law.smart.library.checkout;

import law.smart.library.checkout.dto.ReceiptDTO;

public interface CheckoutService {

    /**
     * If the book with the given ID is available, the book will be marked as loaned.
     * 
     * @param id The book ID.
     * @return A receipt comprising the current loan state and a return date for the book. 
     */
    ReceiptDTO checkout(String id);

    /**
     * Retrieves the current loan status and the return date of the book with the given ID.
     * 
     * @param id The book ID.
     * @return A receipt comprising the current loan state and a return date for the book.
     */
    ReceiptDTO getStatus(String id);
    
}
