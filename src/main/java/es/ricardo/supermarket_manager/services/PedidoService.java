package es.ricardo.supermarket_manager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ricardo.supermarket_manager.entities.Pedido;
import es.ricardo.supermarket_manager.entities.Producto;
import es.ricardo.supermarket_manager.repositories.PedidoRepository;
import es.ricardo.supermarket_manager.repositories.ProductoRepository;

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

