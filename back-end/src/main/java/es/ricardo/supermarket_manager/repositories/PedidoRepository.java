package es.ricardo.supermarket_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ricardo.supermarket_manager.entities.Order;

public interface PedidoRepository extends JpaRepository<Order, Integer>{
}
