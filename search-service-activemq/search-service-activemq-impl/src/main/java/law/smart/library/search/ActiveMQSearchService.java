package law.smart.library.search;

import law.smart.library.books.BookService;
import law.smart.library.books.dto.BookDTO;
import law.smart.library.checkout.CheckoutService;
import law.smart.library.checkout.dto.ReceiptDTO;
import law.smart.library.search.dto.SearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ActiveMQSearchService implements SearchService {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private CheckoutService checkoutService;
    
    @Override
    @JmsListener(destination = "search-service/findByAuthor")
    public List<SearchResultDTO> findByAuthor(String author) {
        List<BookDTO> allBooks = bookService.getAllBooks();
        List<SearchResultDTO> result = new ArrayList<>();
        for (BookDTO book : allBooks) {
            SearchResultDTO searchResultDTO = createSearchResultDTO(book);
            addLoanInformation(searchResultDTO, book);
            result.add(searchResultDTO);
        }
        return result;
    }

    private void addLoanInformation(SearchResultDTO searchResultDTO, BookDTO book) {
        ReceiptDTO status = checkoutService.getStatus(book.getId());
        searchResultDTO.setStatus(status.getStatus().toString());
        searchResultDTO.setReturnDate(status.getReturnDate());
    }

    private SearchResultDTO createSearchResultDTO(BookDTO book) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setId(book.getId());
        searchResultDTO.setAuthor(book.getAuthor());
        searchResultDTO.setTitle(book.getTitle());
        return searchResultDTO;
    }

}
