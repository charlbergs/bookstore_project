package hh.sof3as3.Bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	// attribuutit
	// generoidaan id-arvo pääavaimeksi
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryid;
	private String name;
	
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
	
	// toString
	@Override
	public String toString() {
		return "id: " + categoryid + ", name: " + name;
	}
	
	

}
