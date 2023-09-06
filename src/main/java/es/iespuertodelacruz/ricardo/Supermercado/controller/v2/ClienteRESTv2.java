package es.iespuertodelacruz.ricardo.Supermercado.controller.v2;

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

import es.iespuertodelacruz.ricardo.Supermercado.dto.ClienteDTO;
import es.iespuertodelacruz.ricardo.Supermercado.dto.ProductoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.ClienteService;
import es.iespuertodelacruz.ricardo.Supermercado.services.DetallepedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.PedidoService;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteRESTv2 {
	private Logger logger = LoggerFactory.getLogger(ClienteRESTv2.class);
	
	@Autowired
	ClienteService clientesService;
	
	@Autowired
	PedidoService pedidosService;
	
	@Autowired
	DetallepedidoService detallepedidosService;
	
	@GetMapping("")
	public List<Cliente> getAll(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientesService
		.findAll()
		.forEach(p -> clientes.add((Cliente) p) );
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id){
		Optional<Cliente> clienteOPT = clientesService.findById(id);
		if (clienteOPT.isPresent()) {
			return ResponseEntity.ok(clienteOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
		}
		
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Cliente> clienteOPT = clientesService.findById(id);
		
		if(clienteOPT.isPresent()) {
			for (Pedido p : clienteOPT.get().getPedidos()) {
				for (Detallepedido d : p.getDetallepedidos()) {
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
		
		Cliente c = new Cliente();
		c.setDireccion(clienteDto.getDireccion());
		c.setNombre(clienteDto.getNombre());
		c.setPassword(enhash);

		clientesService.save(c);
		return ResponseEntity.ok().body(new ClienteDTO(c));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto){
		Optional<Cliente> clienteOPT = clientesService.findById(id);
		if(clienteOPT.isPresent()) {
			String enhash = BCrypt.hashpw(clienteDto.getPassword(), BCrypt.gensalt(10));
			Cliente c = clienteOPT.get();

			if(clienteDto.getNombre()!=null) {
				c.setNombre(clienteDto.getNombre());
			}
			if(clienteDto.getDireccion()!=null) {
				c.setDireccion(clienteDto.getDireccion());
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
