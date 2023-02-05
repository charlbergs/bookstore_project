package hh.sof3as3.Bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof3as3.Bookstore.domain.Book;

@Controller
public class BookController {
	
	// kutsuu index-templatea
	@GetMapping("/index")
	public String getIndex(Model model) {
		// testiksi luodaan kirjalista ja lisätään siihen kaksi kirjaa
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Vadelmavenepakolainen", "Miika Nousiainen", 2014, "978-951-1-28406-2", 6.99));
		books.add(new Book("Haudattu jättiläinen", "Kazuo Ishiguro", 2016, "978-952-04-4708-3", 9.99));
		// välitetään templatelle
		model.addAttribute("books", books);
		return "index";
	}
	
}
