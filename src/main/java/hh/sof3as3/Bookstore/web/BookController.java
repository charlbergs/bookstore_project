package hh.sof3as3.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3as3.Bookstore.domain.Book;
import hh.sof3as3.Bookstore.domain.BookRepository;
import hh.sof3as3.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {
	
	// esitellään repositoriot
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	// kutsuu index-templatea
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	// listaus, GET (MVC): kutsuu booklist.html:ää ja näyttää tietokannan kirjat
	@GetMapping("/booklist") // huom endpoint vaihdettu rest-metodien takia
	public String getBooklist(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll(); // haetaan kirjat tietokannasta listalle
		model.addAttribute("books", books); // välitetään lista templatelle model-olion avulla
		return "booklist";
	}
	
	// REST-metodi kirjojen hakemiseen: palauttaa kaikki kirjat listalla json-muodossa
	@GetMapping("/books")
	public @ResponseBody List<Book> booklistRest() {
		return (List<Book>) bookRepository.findAll();
	}
		
	// REST-metodi yhden kirjan hakemiseen id:llä: palauttaa yhden kirjan tiedot json-muodossa
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> getBookRest(@PathVariable("id") Long BookId ) { // PathVariable-annotaatio välittää kirjan id:n metodille
		return bookRepository.findById(BookId);
	}
	
	// lisäys, GET: kutsuu bookform-templatea ja näyttää tyhjän lomakkeen
	@GetMapping("/addbook")
	public String getBookform(Model model) {
		model.addAttribute("book", new Book()); // välitetään tyhjä kirja-olio uuden kirjan tallentamista varten
		model.addAttribute("categories", categoryRepository.findAll()); // välitetään kategorialista
		model.addAttribute("header", "Add new book"); // välitetään oikea otsikko lomakkeelle
		return "bookform";
	}
	
	// muokkaus, GET: kutsuu bookform-templatea ja välittää sinne id:n avulla muokattavan kirjan tiedot
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id)); // .findById().get() jos ei model.addAttributen sisällä
		model.addAttribute("categories", categoryRepository.findAll()); // välitetään myös kategorialista
		model.addAttribute("header", "Edit book"); // välitetään oikea otsikko lomakkeelle
		return "bookform";
	}
	
	// lisäys/muokkaus, POST: lisää kirjan tiedot lomakkeelta tietokantaan, joko muokaten olemassa olevaa tietokantariviä tai 
	// luoden uuden rivin, riippuen siitä onko id jo olemassa
	@PostMapping("/savebook")
	public String sendBookform(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist"; // uudelleenohjaus listausnäkymään
	}
	
	// poisto, GET: poistaa kirjan tietokannasta id:n avulla
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:/booklist"; // uudelleenohjaus listausnäkymään
		
	}
	
}
