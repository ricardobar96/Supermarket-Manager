package es.ricardo.supermarket_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ricardo.supermarket_manager.entities.DetailOrder;

public interface DetallepedidoRepository extends JpaRepository<DetailOrder, Integer>{
}
