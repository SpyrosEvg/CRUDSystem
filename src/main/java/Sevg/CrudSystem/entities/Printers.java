package Sevg.CrudSystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="printers")
public class Printers {

	@Id
	@NotEmpty(message = "Name is required")
	@Size(min=5, message="Name must be at least 5 characters")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "Manifacturer is required")
	@Size(min=2, message="Manifacturer must be at least 2 characters")
	@Column(name="manifacturer")
	private String manifacturer;
	
	@ElementCollection()
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Ktoner", 
	joinColumns = @JoinColumn(name = "name"), 
	inverseJoinColumns = @JoinColumn(name = "black_toner_name"))
	private List<BlackToner> BlackToner = new ArrayList<>();
	
	@ElementCollection()
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Mtoner", 
	joinColumns = @JoinColumn(name = "name"), 
	inverseJoinColumns = @JoinColumn(name = "magenta_toner_name"))
	private List<MagentaToner> MagentaToner = new ArrayList<>();
	
	@ElementCollection()
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Ctoner", 
	joinColumns = @JoinColumn(name = "name"), 
	inverseJoinColumns = @JoinColumn(name = "Cyan_toner_name"))
	private List<CyanToner> CyanToner = new ArrayList<>();
	
	@ElementCollection()
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "printer_Ytoner", 
	joinColumns = @JoinColumn(name = "name"), 
	inverseJoinColumns = @JoinColumn(name = "yellow_toner_name"))
	private List<YellowToner> YellowToner = new ArrayList<>();

	
	
	public Printers() {
		
	}

	public Printers(String printer_name,String manifacturer) {
		this.manifacturer = manifacturer;
	}

	public Printers(String printer_name,String manifacturer,
			List<BlackToner> blackToner,
			List<MagentaToner> magentaToner,
			List<CyanToner> cyanToner,
			List<YellowToner> yellowToner) {
		this.name = printer_name;
		this.manifacturer = manifacturer;
		BlackToner = blackToner;
		MagentaToner = magentaToner;
		CyanToner = cyanToner;
		YellowToner = yellowToner;
	}

	public String getName() {
		return name;
	}

	public void setName(String printer_name) {
		this.name = printer_name;
	}

	public String getManifacturer() {
		return manifacturer;
	}

	public void setManifacturer(String manifacturer) {
		this.manifacturer = manifacturer;
	}

	public List<BlackToner> getBlackToner() {
		return BlackToner;
	}

	public void setBlackToner(List<BlackToner> blackToner) {
		BlackToner = blackToner;
	}
	
	public void addKtoner(BlackToner theBlackToner) {
		BlackToner.add(theBlackToner);
	}

	public List<MagentaToner> getMagentaToner() {
		return MagentaToner;
	}

	public void setMagentaToner(List<MagentaToner> magentaToner) {
		MagentaToner = magentaToner;
	}
	
	public void addMtoner(MagentaToner theMagentaToner) {
		MagentaToner.add(theMagentaToner);
	}

	public List<CyanToner> getCyanToner() {
		return CyanToner;
	}

	public void setCyanToner(List<CyanToner> cyanToner) {
		CyanToner = cyanToner;
	}
	
	public void addCtoner(CyanToner theCyanToner) {
		CyanToner.add(theCyanToner);
	}

	public List<YellowToner> getYellowToner() {
		return YellowToner;
	}

	public void setYellowToner(List<YellowToner> yellowToner) {
		YellowToner = yellowToner;
	}
	
	public void addYtoner(YellowToner theYellowToner) {
		YellowToner.add(theYellowToner);
	}


	@Override
	public String toString() {
		return "Printers [name=" + name + "manifacturer="+manifacturer +", BlackToner=" + BlackToner + ", MagentaToner="
				+ MagentaToner + ", CyanToner=" + CyanToner + ", YellowToner=" + YellowToner + "]";
	}

	

	

}
