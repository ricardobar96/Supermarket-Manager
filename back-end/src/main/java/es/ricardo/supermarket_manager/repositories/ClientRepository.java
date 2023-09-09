package es.ricardo.supermarket_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.ricardo.supermarket_manager.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	@Query("SELECT t FROM Client t where t.name = :name") 
    List<Client> findByName(@Param("name") String strName);
}
