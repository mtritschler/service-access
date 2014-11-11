package law.smart.library.books;

import law.smart.library.books.dto.BookDTO;

import java.util.List;

public interface BookService {
    
    List<BookDTO> getAllBooks();
    
}
