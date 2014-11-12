package law.smart.library.books;

import law.smart.library.books.dto.BookDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
class ActiveMQBookService implements BookService {

    @Override
    @JmsListener(destination = "book-service/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return Arrays.asList(
                createBook("some title", "some author"),
                createBook("another title", "another author")
        );
    }

    private BookDTO createBook(String title, String author) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setId(UUID.randomUUID().toString());
        bookDTO.setAuthor(author);
        return bookDTO;
    }

}
