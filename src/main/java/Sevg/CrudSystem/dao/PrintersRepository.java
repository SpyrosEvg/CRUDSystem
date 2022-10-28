package Sevg.CrudSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Sevg.CrudSystem.entities.Printers;

public interface PrintersRepository extends JpaRepository<Printers, String> {

	List<Printers> findByNameContainsOrManifacturerContainsAllIgnoreCase(String name,
			String Manifacturer);

		
}
