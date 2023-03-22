package hh.sof3as3.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof3as3.Bookstore.domain.User;
import hh.sof3as3.Bookstore.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	// käytetään commandlinerunnerilla luotua testidataa:
	// id 1: user, "user@bookstore.com", ...
	// id 2: admin, "admin@bookstore.com", ...
	
	// esitellään repositorio
	@Autowired
	private UserRepository userRepository;
	
	// kaikkien käyttäjien hakeminen:
	// haetaan kaikki käyttäjät listalle ja tarkistetaan että listan koko on 2
	@Test
	public void findAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		assertThat(users).hasSize(2);
	}
	
	// yhden käyttäjän hakeminen id:llä:
	// haetaan id:llä 1 ja tarkistetaan että tunnus on "user"
	@Test
	public void findUserById() {
		User user = userRepository.findById((long) 1).orElse(null);
		assertThat(user.getUsername()).isEqualTo("user");
	}
	
	// yhden käyttäjän hakeminen tunnuksella:
	// haetaan tunnuksella "user" ja tarkistetaan että email on "user@bookstore.com"
	@Test
	public void findUserByUsername() {
		User user = userRepository.findByUsername("user");
		assertThat(user.getEmail()).isEqualTo("user@bookstore.com");
	}
	
	// uuden käyttäjän lisääminen:
	// luodaan uusi käyttäjä, tallennetaan repositorioon, ja tarkistetaan että id on generoitu
	@Test
	public void createNewUser() {
		User user = new User("testuser", "testpw", "test@bookstore.com", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	// käyttäjän poistaminen:
	// poistetaan käyttäjä id:llä 2, haetaan loput listalle ja tarkistetaan että listan koko on 1
	@Test
	public void deleteUser() {
		userRepository.deleteById((long) 2);
		List<User> users = (List<User>) userRepository.findAll();
		assertThat(users).hasSize(1);
	}
	
}
