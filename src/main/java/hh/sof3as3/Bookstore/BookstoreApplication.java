package hh.sof3as3.Bookstore;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3as3.Bookstore.domain.Book;
import hh.sof3as3.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	// luodaan CommandLineRunner joka käynnistettäessä luo dataa tietokantaan
	@Bean
	public CommandLineRunner demo (BookRepository bookRepository) {
		return (args) -> {
			Book book1 = new Book("Flow My Tears, The Policeman Said", "Philip K. Dick", 2012, "9781780220413", 8.99);
			Book book2 = new Book("Where the Crawdads Sing", "Delia Owens", 2019, "9781472154668", 13.99);
			bookRepository.save(book1);
			bookRepository.save(book2);
			// testitulostus consoleen
			List<Book> books = (List<Book>) bookRepository.findAll();
			for (Book book : books) {
				System.out.println(book.toString());
			}
		};
	}
	

}
