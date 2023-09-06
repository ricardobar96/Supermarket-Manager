package es.iespuertodelacruz.ricardo.Supermercado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.repositories.PedidoRepository;
import es.iespuertodelacruz.ricardo.Supermercado.repositories.ProductoRepository;

@Service
public class PedidoService implements GenericService<Pedido,Integer> {

	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Pedido> findAll() {
		
		return pedidoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Pedido> findAll(Pageable page) {
		
		return pedidoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Pedido> findById(Integer id) {
		
		return pedidoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Pedido save(Pedido objeto) {

		return pedidoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		pedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Pedido entity) {

		pedidoRepository.delete(entity);
		
	}
}

