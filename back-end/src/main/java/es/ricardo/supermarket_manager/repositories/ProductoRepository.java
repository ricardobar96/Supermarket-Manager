package es.ricardo.supermarket_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.ricardo.supermarket_manager.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
}