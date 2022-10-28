package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.CyanToner;

public interface CyanTonerRepository extends JpaRepository<CyanToner, String> {
	
	List<CyanToner> findTonerByPrintersName(String thePritnerName);
	
	List<CyanToner> findByNameContainsOrColorContainsAllIgnoreCase(String name,
			String color);

	CyanToner findCTonerByName(String theTonerName);
}