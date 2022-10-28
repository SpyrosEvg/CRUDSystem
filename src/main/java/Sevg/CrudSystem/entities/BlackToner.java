package Sevg.CrudSystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.context.annotation.PropertySource;

@Entity
@Table(name="black_toner")
@PropertySource("classpath:application.properties")
public class BlackToner {

	//initialize variables
	
	@Id
	@NotEmpty(message="Name is required")
	@Size(min=4 , max=25,message="Name size must be at least 4 characters")
	@Column(name="name")
	private String name;
	
	@Column(name="color")
	private String color;
	
	@Column(name="stock")
	private Integer stock;
	
	
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Ktoner", 
	joinColumns = @JoinColumn(name = "black_toner_name"), 
	inverseJoinColumns = @JoinColumn(name = "name"))
    List<Printers> printers = new ArrayList<>();
	
	//Constructors
	
	public BlackToner() {
		
	}
	
	
	public BlackToner(String name, Integer stock,String color) {
		this.name = name;
		this.stock = stock;
		this.color = color;
	}


	public BlackToner(String name, Integer stock,String color, List<Printers> printers) {
		this.name = name;
		this.stock = stock;
		this.color = color;
		this.printers = printers;
	}
	
	//Setter and Getters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	

	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public List<Printers> getPrinters() {
		return printers;
	}


	public void setPrinters(List<Printers> printers) {
		this.printers = printers;
	}
	
	public void addPrinter(Printers thePrinter) {
		
		printers.add(thePrinter);
	}

	//toString 
	@Override
	public String toString() {
		return "BlackToner [name=" + name +", color=" + color + ", stock=" + stock +", printers=" + printers + "]";
	}
	

}
