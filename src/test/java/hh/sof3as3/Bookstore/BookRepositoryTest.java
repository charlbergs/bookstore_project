package hh.sof3as3.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3as3.Bookstore.domain.Book;
import hh.sof3as3.Bookstore.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {
	
	// testeissä käytetään samaa commandlinerunnerilla luotua testidataa kuin itse sovelluksessa,
	// koska se on esim. id-arvojen numeroinnin kannalta suoraviivaisinta:
	// id 1: "Flow My Tears, The Policeman Said", "Philip K. Dick", 2012, "9781780220413", 8.99, (Scifi)
	// id 2: "Altered Carbon", "Richard Morgan", 2008, "9780575081246", 10.49, (Scifi)
	// id 3: "Where the Crawdads Sing", "Delia Owens", 2019, "9781472154668", 13.99, (Mystery)
	// id 4: "Gone Girl", "Gillian Flynn", 2013, "9780753827666", 11.00, (Mystery)
	// id 5: "The First Forty-Nine Stories", "Ernest Hemingway", 1995, "9780099339212", 14.29, (Short story) 
	
	// toisaalta testejä varten voisi myös luoda itsenäisen testidatan, mutta tällöin repositorio
	// pitää tyhjentää ennen uuden datan luomista, ja id-arvot eivät ole 1-5 vaan jotakin muuta
	
	// esitellään repositorio
	@Autowired
	private BookRepository bookRepository;
	
	// kaikkien kirjojen hakeminen:
	// haetaan kaikki testikirjat listalle ja tarkistetaan että listan pituus on 5
	@Test
	public void findAllBooks() {
		List<Book> books = (List<Book>) bookRepository.findAll();
		assertThat(books).hasSize(5);
	}
	
	// yhden kirjan hakeminen id:llä:
	// haetaan kirja id:llä 1 ja tarkistetaan että sen otsikko sisältää "Flow My Tears"
	@Test
	public void findBook() {
		Book book = bookRepository.findById((long) 1).orElse(null); // orElse(): palauttaa book-olion tai null, jotta voidaan kiertää Optional<Book>
		assertThat(book.getTitle()).contains("Flow My Tears");
	}
	
	// uuden kirjan lisääminen:
	// luodaan uusi kirja, tallennetaan repositorioon, ja tarkistetaan että sille on generoitu id
	@Test
	public void createNewBook() {
		Book book = new Book("Dear Life", "Alice Munro", 2013, "9780099578642", 11.29, null); // kategoria null, koska keskitytään testaamaan kirjarepositoriota
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	// kirjan poistaminen:
	// poistetaan kirja id:llä 5, haetaan loput kirjat listalle ja tarkistetaan että listan pituus on 4
	@Test
	public void deleteBook() {
		bookRepository.deleteById((long) 5);
		List<Book> books = (List<Book>) bookRepository.findAll();
		assertThat(books).hasSize(4);
	}
	
	// kirjan muokkaus:
	// haetaan kirja id:llä 2, muokataan vuosi (2008->2022), tallennetaan repositorioon,
	// haetaan kirja uuteen muuttujaan samalla id:llä ja tarkistetaan että vuosi on muuttunut
	@Test
	public void updateBook() {
		Book book = bookRepository.findById((long) 2).orElse(null);
		book.setYear(2022);
		bookRepository.save(book);
		Book book2 = bookRepository.findById((long) 2).orElse(null);
		assertThat(book2.getYear()).isEqualTo(2022);
	}
}
