package Sevg.CrudSystem.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



import Sevg.CrudSystem.customValidation.PcValidation;




@Entity
@Table(name="users")
public class Users  {


	//define fields
	@Id
	@NotEmpty(message="Username is required")
	@Size(min=3 , max=25,message="Username size must be between 3 and 25")
	@Column(name="username")
	private String username;
	

	@Column(name="password")
	private String password;
	

	@Column(name="enabled")
	private int enable;
	
	@NotEmpty(message="is required")
	@Size(min=3 , max=25)
	@Column(name="first_name")
	private String firstName;
	
	@NotEmpty(message="is required")
	@Size(min=3 , max=25,message="size must be between 5 and 25")
	@Column(name="last_name")
	private String lastName;
	
	@NotEmpty(message="is required")
	@Email(message="not a proper email address")
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="is required")
	@PcValidation
	@Column(name="pc")
	private String pc;
	
	@NotEmpty(message="is required")
	@Column(name="sex")
	private String sex;
	
	@NotEmpty(message="is required")
	@Size(min=3 , max=10)
	@Column(name="phone")
	private String phone;
	
	@OneToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "authorities", 
	joinColumns = @JoinColumn(name = "username"), 
	inverseJoinColumns = @JoinColumn(name = "role_name"))
	private Collection<Role> roles;
	
	//define constructors
	public Users() {
		
	}


	public Users(String username, String password, int enable, String firstName, String lastName, String email,
			String pc, String sex, String phone) {
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pc = pc;
		this.sex = sex;
		this.phone = phone;
	}
	
	

	public Users(String username, String password, int enable, String firstName, String lastName, String email,
			String pc, String sex, String phone,Collection<Role> roles) {
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pc = pc;
		this.sex = sex;
		this.phone = phone;
		this.roles = roles;
	}


	
	//Setters and Getters
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public int getEnalbe() {
		return enable;
	}


	public void setEnalbe(int enable) {
		this.enable = enable;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPc() {
		return pc;
	}


	public void setPc(String pc) {
		this.pc = pc;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public int getEnable() {
		return enable;
	}


	public void setEnable(int enable) {
		this.enable = enable;
	}

	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		
		roles.add(role);
	}
	
	
	//define toString method
	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", enable=" + enable + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", pc=" + pc +", phone=" + phone + ", sex=" + sex  +
				", roles=" + roles +"]";
	}
	
}
