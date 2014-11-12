package law.smart.library.books;

import law.smart.library.books.dto.BookDTO;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BookServiceImpl implements BookService {

  @Override
  public List<BookDTO> getAllBooks() {
    return Arrays.asList(
      createBook("A Song of Ice and Fire", "George R. R. Martin"),
      createBook("Moby Dick", "Herman Melville")
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
