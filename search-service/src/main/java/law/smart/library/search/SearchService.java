package law.smart.library.search;

import law.smart.library.search.dto.SearchResultDTO;

import java.util.List;

public interface SearchService {
    
    List<SearchResultDTO> findByAuthor(String author);
    
}
