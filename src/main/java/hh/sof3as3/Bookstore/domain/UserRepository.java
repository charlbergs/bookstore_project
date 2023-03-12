package hh.sof3as3.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username); // määritellään Springin kirjautumistestiä varten
}
