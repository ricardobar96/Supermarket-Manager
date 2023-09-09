package es.ricardo.supermarket_manager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ricardo.supermarket_manager.entities.Product;
import es.ricardo.supermarket_manager.repositories.ProductoRepository;

@Service
public class ProductoService implements GenericService<Product,Integer> {

	
	@Autowired
	ProductoRepository productoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Product> findAll() {
		
		return productoRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Product> findAll(Pageable page) {
		
		return productoRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Product> findById(Integer id) {
		
		return productoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Product save(Product objeto) {

		return productoRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		productoRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Product entity) {

		productoRepository.delete(entity);
		
	}
}
