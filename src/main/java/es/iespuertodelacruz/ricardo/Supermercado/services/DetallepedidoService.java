package es.iespuertodelacruz.ricardo.Supermercado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.repositories.DetallepedidoRepository;

@Service
public class DetallepedidoService  implements GenericService<Detallepedido,Integer> {

	
	@Autowired
	DetallepedidoRepository detallepedidoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Detallepedido> findAll() {
		
		return detallepedidoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Detallepedido> findAll(Pageable page) {
		
		return detallepedidoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Detallepedido> findById(Integer id) {
		
		return detallepedidoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Detallepedido save(Detallepedido objeto) {

		return detallepedidoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		detallepedidoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Detallepedido entity) {

		detallepedidoRepository.delete(entity);
		
	}
}
