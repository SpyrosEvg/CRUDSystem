package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.MagentaToner;

public interface MagentaTonerRepository extends JpaRepository<MagentaToner, String> {

	List<MagentaToner> findTonerByPrintersName(String thePritnerName);

	List<MagentaToner> findByNameContainsOrColorContainsAllIgnoreCase(String name,
			String color);

	MagentaToner findMTonerByName(String theTonerName);
}
