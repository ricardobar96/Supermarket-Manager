package es.ricardo.supermarket_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.repositories.ClientRepository;

@Service
public class ClientService  implements GenericService<Client,Integer> {

	
	@Autowired
	ClientRepository clientRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Client> findAll() {
		
		return clientRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Client> findAll(Pageable page) {
		
		return clientRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Client> findById(Integer id) {
		
		return clientRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Client save(Client object) {

		return clientRepository.save(object);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		clientRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Client entity) {

		clientRepository.delete(entity);
		
	}
	
	@Transactional(readOnly=true)
	public Client findByName(String name) {
		Client client = null;
		List<Client> list = clientRepository.findByName(name);
		if( list != null && list.size() ==1)
			client = list.get(0);
		return client;
	}
}