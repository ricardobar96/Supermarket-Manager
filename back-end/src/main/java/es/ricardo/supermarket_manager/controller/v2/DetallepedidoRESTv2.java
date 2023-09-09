package es.ricardo.supermarket_manager.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ricardo.supermarket_manager.dto.DetallepedidoDTO;
import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Order;
import es.ricardo.supermarket_manager.services.ClientService;
import es.ricardo.supermarket_manager.services.DetallepedidoService;

@RestController
@RequestMapping("/api/v2/detallepedidos")
public class DetallepedidoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(DetallepedidoRESTv2.class);
	
	@Autowired
	DetallepedidoService detallepedidosService;
	
	@Autowired
	ClientService clientesService;
	
	public Client getClienteLogged() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String name = authentication.getName();
		return clientesService.findByName(name);
	}
	
	@GetMapping("")
	public List<DetailOrder> getAll(){
		Client logged = getClienteLogged();
		String nameL = logged.getName();
		
		ArrayList<DetailOrder> detallepedidos = new ArrayList<DetailOrder>();
		detallepedidosService
		.findAll()
		.forEach(p -> detallepedidos.add((DetailOrder) p) );
		
		ArrayList<DetailOrder> detallesCliente = new ArrayList<DetailOrder>();
		for (DetailOrder d : detallepedidos) {
			if(d.getOrder().getClient().getName().equals(nameL)) {
				detallesCliente.add(d);
			}
		}
		
		return detallesCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDetallepedidoById(@PathVariable Integer id){
		Client logged = getClienteLogged();
		String nameL = logged.getName();
		
		Optional<DetailOrder> detalleOPT = detallepedidosService.findById(id);
		if (detalleOPT.isPresent()) {
			if(detalleOPT.get().getOrder().getClient().getName().equals(nameL)) {
				return ResponseEntity.ok(detalleOPT);
			}
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El pedido lo ha encargado otro cliente");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Detallepedido no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<DetailOrder> detalleOPT = detallepedidosService.findById(id);
		
		if(detalleOPT.isPresent()) {
			detallepedidosService.deleteById(id);
			return ResponseEntity.ok("Detallepedido eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Detallepedido no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody DetallepedidoDTO detalleDto){
		DetailOrder d = new DetailOrder();
		d.setQuantity(detalleDto.getCantidad());
		d.setUnitPrice(detalleDto.getPreciounidad());
		d.setOrder(detalleDto.getPedido());
		d.setProduct(detalleDto.getProducto());

		detallepedidosService.save(d);
		return ResponseEntity.ok().body(new DetallepedidoDTO(d));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody DetallepedidoDTO detalleDto){
		Optional<DetailOrder> detalleOPT = detallepedidosService.findById(id);
		if(detalleOPT.isPresent()) {
			DetailOrder d = detalleOPT.get();

			if(detalleDto.getCantidad()>-1) {
				d.setQuantity(detalleDto.getCantidad());
			}
			if(detalleDto.getPreciounidad()>0) {
				d.setUnitPrice(detalleDto.getPreciounidad());
			}	
			
			if(detalleDto.getPedido()!=null) {
				d.setOrder(detalleDto.getPedido());
			}	
			
			if(detalleDto.getProducto()!=null) {
				d.setProduct(detalleDto.getProducto());
			}	
			
			return ResponseEntity.ok(detallepedidosService.save(d));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Detallepedido no existe");
		}
	}
}
