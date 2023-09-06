package es.iespuertodelacruz.ricardo.Supermercado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}
