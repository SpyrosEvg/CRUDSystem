package Sevg.CrudSystem.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="yellow_toner")
public class YellowToner {

	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="color")
	private String color;
	
	@Column(name="stock")
	private int stock;
	
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Ytoner", 
	joinColumns = @JoinColumn(name = "yellow_toner_name"), 
	inverseJoinColumns = @JoinColumn(name = "name"))
    List<Printers> printers;
	
	public YellowToner() {
		
	}
	

	public YellowToner(String name, int stock,String color) {
		this.name = name;
		this.stock = stock;
		this.color = color;
	}

	

	public YellowToner(String name, int stock,String color ,List<Printers> printers) {
		this.name = name;
		this.stock = stock;
		this.color = color;
		this.printers = printers;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
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


	@Override
	public String toString() {
		return "YellowToner [name=" + name +", color=" + color + ", stock=" + stock + ", printers=" + printers + "]";
	}

	
	
	
}
