package law.smart.library.checkout.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReceiptDTO implements Serializable {
    
    public static enum LoanStatus {
        AVAILABLE, TAKEN
    }
    
    private String bookId;
    private LocalDate returnDate;
    private LoanStatus status = LoanStatus.AVAILABLE;

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

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
