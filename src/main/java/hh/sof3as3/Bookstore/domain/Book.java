package hh.sof3as3.Bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// lisätään entity-annotaatio
@Entity
public class Book {
	
	// attribuutit
	// generoidaan id-arvo pääavaimeksi
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	@Column(name="publishing_year") // year on varattu sana, joten muutetaan sarakkeen nimi
	private int year;
	private String isbn;
	private double price;
	// luodaan viiteavainattribuutti (kategoria)
	@ManyToOne
	@JsonIgnoreProperties ("books") // blokataan categoryn books-attribuutti jotta vältetään loputon loop
	@JoinColumn(name="categoryid")
	private Category category;
	
	// konstruktorit
	// parametriton
	public Book() {
		this.title = null;
		this.author = null;
		this.year = 0;
		this.isbn = null;
		this.price = 0;
		this.category = null;
	}
	// parametrillinen
	public Book(String title, String author, int year, String isbn, double price, Category category) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	// getterit ja setterit
	// huom getId & setId lisätty poisto- ja muokkaustoiminnallisuuksia varten
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	// toString
	@Override
	public String toString() {
		return "id: " + id + ", title: " + title + ", author: " + author + ", year: " + year + ", isbn: " + isbn + ", price: " + price + " €, category: " + ((category == null) ? "" : category.getName());
	}
	
	

}
