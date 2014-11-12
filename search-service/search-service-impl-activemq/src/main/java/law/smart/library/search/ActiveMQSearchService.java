package law.smart.library.search;

import law.smart.library.books.BookService;
import law.smart.library.checkout.CheckoutService;
import law.smart.library.search.dto.SearchResultDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveMQSearchService implements SearchService {

    private final SearchService delegate;

    public ActiveMQSearchService(BookService bookService, CheckoutService checkoutService) {
        this(new SearchServiceImpl(bookService, checkoutService));
    }

    public ActiveMQSearchService(SearchService searchService) {
        this.delegate = searchService;
    }

    @Override
    @JmsListener(destination = "search-service/findByAuthor")
    public List<SearchResultDTO> findByAuthor(String author) {
        return delegate.findByAuthor(author);
    }
}
