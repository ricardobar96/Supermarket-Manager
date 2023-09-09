package es.ricardo.supermarket_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ricardo.supermarket_manager.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}
