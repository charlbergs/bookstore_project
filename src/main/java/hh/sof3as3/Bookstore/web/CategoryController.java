package hh.sof3as3.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3as3.Bookstore.domain.Category;
import hh.sof3as3.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class CategoryController {
	
	// esitellään repositorio
	@Autowired
	private CategoryRepository categoryRepository;
	
	// listaus, GET: kutsuu categorylist.html:ää ja listaa kategoriat
	@GetMapping("/categories")
	public String getCategorylist(Model model) {
		List<Category> categories = (List<Category>) categoryRepository.findAll(); // haetaan kirjat tietokannasta listalle
		model.addAttribute("categories", categories); // välitetään lista templatelle model-olion avulla
		return "categorylist";
	}
	
	// lisäys, GET: kutsuu categoryform.html:ää ja näyttää lomakkeen
	@GetMapping("/addcategory")
	public String getCategoryform(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform";
	}
	
	// lisäys, POST: tallentaa kategorian tiedot lomakkeelta tietokantaan
	@PostMapping("/savecategory")
	public String sendCategoryform(Category category) {
		categoryRepository.save(category);
		return "redirect:/categories"; // uudelleenohjataan listausnäkymään
	}

}
