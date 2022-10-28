package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.BlackToner;


public interface BlackTonerRepository extends JpaRepository<BlackToner, String> {
	
    public List<BlackToner> findTonerByPrintersName(String thePrinterName);

	public BlackToner findKTonerByName(String theTonerName);

	List<BlackToner> findByNameContainsOrColorContainsAllIgnoreCase(String name,
			String color);
	
}
