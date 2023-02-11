package hh.sof3as3.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
