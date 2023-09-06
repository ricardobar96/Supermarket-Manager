package es.iespuertodelacruz.ricardo.Supermercado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
}