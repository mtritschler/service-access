package law.smart.library.search;

import law.smart.library.checkout.CheckoutService;
import law.smart.library.checkout.dto.ReceiptDTO;
import law.smart.library.search.dto.SearchResultDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@EnableAutoConfiguration
@ComponentScan("law.smart.library")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        SearchService searchService = context.getBean(SearchService.class);
        CheckoutService checkoutService = context.getBean(CheckoutService.class);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            String command = strings[0];
            switch (command) {
                case "quit":
                    System.exit(0);
                case "list":
                    List<SearchResultDTO> result = searchService.findByAuthor("*");
                    System.out.println(result);
                    break;
                case "checkout":
                    ReceiptDTO receiptDTO = checkoutService.checkout(strings[0]);
                    System.out.println(receiptDTO.getStatus() + " until " + receiptDTO.getReturnDate());
                    break;
            }
        }
    }
}
