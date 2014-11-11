package law.smart.library.search;

import law.smart.library.search.dto.BookDTO;

import java.util.List;

public interface SearchService {
    
    List<BookDTO> findByAuthor(String author);
    
}
