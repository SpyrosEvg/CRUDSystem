package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.YellowToner;

public interface YellowTonerRepository extends JpaRepository<YellowToner, String> {
	List<YellowToner> findTonerByPrintersName(String thePritnerName);
	
	List<YellowToner> findByNameContainsOrColorContainsAllIgnoreCase(String name,
			String color);

	YellowToner findYTonerByName(String theTonerName);
}
