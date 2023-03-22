package hh.sof3as3.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3as3.Bookstore.domain.Category;
import hh.sof3as3.Bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {
	
	// käytetään commandlinerunnerilla luotua testidataa:
	// id 1: Scifi
	// id 2: Mystery
	// id 3: Short story
	
	// esitellään repositorio
	@Autowired
	private CategoryRepository categRepository;
	
	// kaikkien kategorioiden hakeminen:
	// haetaan kategoriat listalle ja tarkistetaan että listan pituus on 3
	@Test
	public void findAllCategories() {
		List<Category> categories = (List<Category>) categRepository.findAll();
		assertThat(categories).hasSize(3);
	}
	
	// yhden kategorian hakeminen id:llä:
	// haetaan kategoria id:llä 1 ja tarkistetaan että sen nimi on "Scifi"
	@Test
	public void findCategory() {
		Category category = categRepository.findById((long) 1).orElse(null);
		assertThat(category.getName()).isEqualTo("Scifi");
	}
	
	// uuden kategorian luominen:
	// luodaan uusi kategoria, tallennetaan repositorioon ja tarkistetaan että sille on generoitu id
	@Test
	public void createNewCategory() {
		Category category = new Category("Thriller");
		categRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	// kategorian poistaminen: 
	// poistetaan kategoria id:llä 3, haetaan loput kategoriat listalle, ja tarkistetaan että listan pituus on 2
	@Test
	public void deleteCategory() {
		categRepository.deleteById((long) 3);
		List<Category> categories = (List<Category>) categRepository.findAll();
		assertThat(categories).hasSize(2);
	}
}
