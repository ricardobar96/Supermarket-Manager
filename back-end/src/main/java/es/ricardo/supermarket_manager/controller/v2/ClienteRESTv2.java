package es.ricardo.supermarket_manager.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ricardo.supermarket_manager.dto.ClienteDTO;
import es.ricardo.supermarket_manager.dto.ProductoDTO;
import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Order;
import es.ricardo.supermarket_manager.entities.Product;
import es.ricardo.supermarket_manager.services.ClientService;
import es.ricardo.supermarket_manager.services.DetallepedidoService;
import es.ricardo.supermarket_manager.services.PedidoService;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteRESTv2 {
	private Logger logger = LoggerFactory.getLogger(ClienteRESTv2.class);
	
	@Autowired
	ClientService clientesService;
	
	@Autowired
	PedidoService pedidosService;
	
	@Autowired
	DetallepedidoService detallepedidosService;
	
	@GetMapping("")
	public List<Client> getAll(){
		ArrayList<Client> clientes = new ArrayList<Client>();
		clientesService
		.findAll()
		.forEach(p -> clientes.add((Client) p) );
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id){
		Optional<Client> clienteOPT = clientesService.findById(id);
		if (clienteOPT.isPresent()) {
			return ResponseEntity.ok(clienteOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
		}
		
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Client> clienteOPT = clientesService.findById(id);
		
		if(clienteOPT.isPresent()) {
			for (Order p : clienteOPT.get().getOrders()) {
				for (DetailOrder d : p.getDetailOrder()) {
					detallepedidosService.delete(d);
				}
				pedidosService.delete(p);
			}
			clientesService.deleteById(id);
			return ResponseEntity.ok("Cliente eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDto){
		String enhash = BCrypt.hashpw(clienteDto.getPassword(), BCrypt.gensalt(10));
		
		Client c = new Client();
		c.setAddress(clienteDto.getDireccion());
		c.setName(clienteDto.getNombre());
		c.setPassword(enhash);

		clientesService.save(c);
		return ResponseEntity.ok().body(new ClienteDTO(c));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto){
		Optional<Client> clienteOPT = clientesService.findById(id);
		if(clienteOPT.isPresent()) {
			String enhash = BCrypt.hashpw(clienteDto.getPassword(), BCrypt.gensalt(10));
			Client c = clienteOPT.get();

			if(clienteDto.getNombre()!=null) {
				c.setName(clienteDto.getNombre());
			}
			if(clienteDto.getDireccion()!=null) {
				c.setAddress(clienteDto.getDireccion());
			}
			if(clienteDto.getPassword()!=null) {
				c.setPassword(enhash);
			}
			
			return ResponseEntity.ok(clientesService.save(c));
			
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no existe");
		}
	}
}
