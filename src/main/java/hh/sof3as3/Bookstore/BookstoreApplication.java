package hh.sof3as3.Bookstore;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3as3.Bookstore.domain.Book;
import hh.sof3as3.Bookstore.domain.BookRepository;
import hh.sof3as3.Bookstore.domain.Category;
import hh.sof3as3.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	// commandlinerunner: käynnistettäessä luodaan dataa tietokantaan
	// ja testitulostetaan consoleen (kateogriat ja kirjat)
	@Bean
	public CommandLineRunner demo (CategoryRepository categoryRepository, BookRepository bookRepository) {
		return (args) -> {
			
			// kategoriat
			Category categ1 = new Category("Scifi");
			Category categ2 = new Category("Mystery");
			Category categ3 = new Category("Short story");
			categoryRepository.save(categ1);
			categoryRepository.save(categ2);
			categoryRepository.save(categ3);
			
			// kirjat
			bookRepository.save(new Book("Flow My Tears, The Policeman Said", "Philip K. Dick", 2012, "9781780220413", 8.99, categ1));
			bookRepository.save(new Book("Altered Carbon", "Richard Morgan", 2008, "9780575081246", 10.49, categ1));
			bookRepository.save(new Book("Where the Crawdads Sing", "Delia Owens", 2019, "9781472154668", 13.99, categ2));
			bookRepository.save(new Book("Gone Girl", "Gillian Flynn", 2013, "9780753827666", 11.00, categ2));
			bookRepository.save(new Book("The First Forty-Nine Stories", "Ernest Hemingway", 1995, "9780099339212", 14.29, categ3));
			
			// testitulostus consoleen
			// vaihda system.out.println log.info() ks esim
			System.out.println("------------");
			System.out.println("Categories:");
			List<Category> categories = (List<Category>) categoryRepository.findAll();
			for (Category category: categories) {
				System.out.println(category.toString());
			}
			System.out.println("------------");
			System.out.println("Books:");
			List<Book> books = (List<Book>) bookRepository.findAll();
			for (Book book : books) {
				System.out.println(book.toString());
			}
			System.out.println();
		};
	}
	

}
