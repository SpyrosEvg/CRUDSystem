package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.Users;


public interface UserRepository extends JpaRepository<Users, String>{
	

	// add a method to sort by last name
		public List<Users> findAllByOrderByLastNameAsc();
		
		// search by name
		public List<Users> findByFirstNameContainsOrLastNameContainsOrUsernameContainsAllIgnoreCase(String firstName, String lastName, String username);

		
}

