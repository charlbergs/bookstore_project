package hh.sof3as3.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof3as3.Bookstore.web.BookController;
import hh.sof3as3.Bookstore.web.CategoryController;

@SpringBootTest
class BookstoreApplicationTests {
	// esitellään testattavat controllerit
	@Autowired
	private BookController bookController;
	@Autowired
	private CategoryController categoryController;

	// testataan bookControllerin toiminta
	@Test
	public void bookControllerSmokeTest() throws Exception {
		assertThat(bookController).isNotNull();
	}
	// testataan categorycontrollerin toiminta
	@Test
	public void categControllerSmokeTest() throws Exception {
		assertThat(categoryController).isNotNull();
	}
}
