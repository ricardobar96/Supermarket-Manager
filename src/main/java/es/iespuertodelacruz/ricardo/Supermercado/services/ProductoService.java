package es.iespuertodelacruz.ricardo.Supermercado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.repositories.ProductoRepository;

@Service
public class ProductoService implements GenericService<Producto,Integer> {

	
	@Autowired
	ProductoRepository productoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Producto> findAll() {
		
		return productoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Producto> findAll(Pageable page) {
		
		return productoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Producto> findById(Integer id) {
		
		return productoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Producto save(Producto objeto) {

		return productoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		productoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Producto entity) {

		productoRepository.delete(entity);
		
	}
}
