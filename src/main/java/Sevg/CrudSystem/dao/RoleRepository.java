package Sevg.CrudSystem.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.Role;
import Sevg.CrudSystem.entities.Users;


public interface RoleRepository extends JpaRepository<Role, String>{

	public Collection<Role> findRoleByName(String theRoleName);
	
	public Users findUserByName(String theUsername);
}
