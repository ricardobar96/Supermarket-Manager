package es.iespuertodelacruz.ricardo.Supermercado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;
import es.iespuertodelacruz.ricardo.Supermercado.repositories.ClienteRepository;

@Service
public class ClienteService  implements GenericService<Cliente,Integer> {

	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Cliente> findAll(Pageable page) {
		
		return clienteRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Cliente> findById(Integer id) {
		
		return clienteRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Cliente save(Cliente objeto) {

		return clienteRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		clienteRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Cliente entity) {

		clienteRepository.delete(entity);
		
	}
	
	@Transactional(readOnly=true)
	public Cliente findByNombre(String nombre) {
		Cliente c = null;
		List<Cliente> lista = clienteRepository.findByNombre(nombre);
		if( lista != null && lista.size() ==1)
			c = lista.get(0);
		return c;
	}
}