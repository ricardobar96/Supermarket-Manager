package es.ricardo.supermarket_manager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ricardo.supermarket_manager.entities.Order;
import es.ricardo.supermarket_manager.entities.Product;
import es.ricardo.supermarket_manager.repositories.PedidoRepository;
import es.ricardo.supermarket_manager.repositories.ProductoRepository;

@Service
public class PedidoService implements GenericService<Order,Integer> {

	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Order> findAll() {
		
		return pedidoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Order> findAll(Pageable page) {
		
		return pedidoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Order> findById(Integer id) {
		
		return pedidoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Order save(Order objeto) {

		return pedidoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		pedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Order entity) {

		pedidoRepository.delete(entity);
		
	}
}

