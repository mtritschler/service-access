package law.smart.library.checkout.dto;

import java.time.LocalDate;

public class ReceiptDTO {
    
    private String bookId;
    private LocalDate returnDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
