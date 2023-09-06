package es.iespuertodelacruz.ricardo.Supermercado.controller.v2;

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

import es.iespuertodelacruz.ricardo.Supermercado.dto.DetallepedidoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.services.ClienteService;
import es.iespuertodelacruz.ricardo.Supermercado.services.DetallepedidoService;

@RestController
@RequestMapping("/api/v2/detallepedidos")
public class DetallepedidoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(DetallepedidoRESTv2.class);
	
	@Autowired
	DetallepedidoService detallepedidosService;
	
	@Autowired
	ClienteService clientesService;
	
	public Cliente getClienteLogged() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String name = authentication.getName();
		return clientesService.findByNombre(name);
	}
	
	@GetMapping("")
	public List<Detallepedido> getAll(){
		Cliente logged = getClienteLogged();
		String nombreL = logged.getNombre();
		
		ArrayList<Detallepedido> detallepedidos = new ArrayList<Detallepedido>();
		detallepedidosService
		.findAll()
		.forEach(p -> detallepedidos.add((Detallepedido) p) );
		
		ArrayList<Detallepedido> detallesCliente = new ArrayList<Detallepedido>();
		for (Detallepedido d : detallepedidos) {
			if(d.getPedido().getCliente().getNombre().equals(nombreL)) {
				detallesCliente.add(d);
			}
		}
		
		return detallesCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDetallepedidoById(@PathVariable Integer id){
		Cliente logged = getClienteLogged();
		String nombreL = logged.getNombre();
		
		Optional<Detallepedido> detalleOPT = detallepedidosService.findById(id);
		if (detalleOPT.isPresent()) {
			if(detalleOPT.get().getPedido().getCliente().getNombre().equals(nombreL)) {
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
		Optional<Detallepedido> detalleOPT = detallepedidosService.findById(id);
		
		if(detalleOPT.isPresent()) {
			detallepedidosService.deleteById(id);
			return ResponseEntity.ok("Detallepedido eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Detallepedido no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody DetallepedidoDTO detalleDto){
		Detallepedido d = new Detallepedido();
		d.setCantidad(detalleDto.getCantidad());
		d.setPreciounidad(detalleDto.getPreciounidad());
		d.setPedido(detalleDto.getPedido());
		d.setProducto(detalleDto.getProducto());

		detallepedidosService.save(d);
		return ResponseEntity.ok().body(new DetallepedidoDTO(d));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody DetallepedidoDTO detalleDto){
		Optional<Detallepedido> detalleOPT = detallepedidosService.findById(id);
		if(detalleOPT.isPresent()) {
			Detallepedido d = detalleOPT.get();

			if(detalleDto.getCantidad()>-1) {
				d.setCantidad(detalleDto.getCantidad());
			}
			if(detalleDto.getPreciounidad()>0) {
				d.setPreciounidad(detalleDto.getPreciounidad());
			}	
			
			if(detalleDto.getPedido()!=null) {
				d.setPedido(detalleDto.getPedido());
			}	
			
			if(detalleDto.getProducto()!=null) {
				d.setProducto(detalleDto.getProducto());
			}	
			
			return ResponseEntity.ok(detallepedidosService.save(d));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Detallepedido no existe");
		}
	}
}
