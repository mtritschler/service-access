package law.smart.library.books;

import law.smart.library.books.dto.BookDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveMQBookService implements BookService {

    private final BookService delegate;

    public ActiveMQBookService() {
        this(new BookServiceImpl());
    }

    public ActiveMQBookService(BookService bookService) {
        this.delegate = bookService;
    }

    @Override
    @JmsListener(destination = "book-service/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return delegate.getAllBooks();
    }

}
