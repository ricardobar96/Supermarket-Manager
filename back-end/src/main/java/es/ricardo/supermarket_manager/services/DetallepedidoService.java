package es.ricardo.supermarket_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.repositories.DetallepedidoRepository;

@Service
public class DetallepedidoService  implements GenericService<DetailOrder,Integer> {

	
	@Autowired
	DetallepedidoRepository detallepedidoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<DetailOrder> findAll() {
		
		return detallepedidoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<DetailOrder> findAll(Pageable page) {
		
		return detallepedidoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<DetailOrder> findById(Integer id) {
		
		return detallepedidoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public DetailOrder save(DetailOrder objeto) {

		return detallepedidoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		detallepedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(DetailOrder entity) {

		detallepedidoRepository.delete(entity);
		
	}
}
