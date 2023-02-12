package hh.sof3as3.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3as3.Bookstore.domain.Book;
import hh.sof3as3.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	// esitellään repositorio
	@Autowired
	private BookRepository bookRepository;
	
	// kutsuu index-templatea
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	// kutsuu booklist.html:ää endpointissa /books ja näyttää tietokannan kirjat
	@GetMapping("/books")
	public String getBooklist(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll(); // haetaan kirjat tietokannasta listalle
		model.addAttribute("books", books); // välitetään lista templatelle model-olion avulla
		return "booklist";
	}
	
	// kutsuu addbook.html:ää endpointissa addbook ja näyttää kirjalisäyslomakkeen
	@GetMapping("/addbook")
	public String getBookform(Model model) {
		model.addAttribute("book", new Book()); // välitetään tyhjä kirja-olio uuden kirjan tallentamista varten
		return "addbook";
	}
	
	// lisää uuden kirjan tiedot lomakkeelta tietokantaan
	@PostMapping("/addbook")
	public String sendBookform(Book book) {
		bookRepository.save(book);
		return "redirect:/books";
	}
	
	// poistaa kirjan tietokannasta id:n avulla
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:/books";
		
	}
	
}
