package hh.sof3as3.Bookstore.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	// attribuutit
	// generoidaan id-arvo pääavaimeksi
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryid;
	private String name;
	// luodaan viiteavainattribuutti (book)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category") // cascadetype.all: jos kategoria poistetaan niin myös sen kirjat poistetaan, mappedby: sama nimi kuin se jolla category-entiteetti esitellään book-luokassa
	private List<Book> books;
	
	// konstruktorit
	public Category() {
		this.name = null;
	}
	public Category(String name) {
		this.name = name;
	}
	
	// getterit ja setterit
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	// toString
	@Override
	public String toString() {
		return "id: " + categoryid + ", name: " + name;
	}
	
	

}
