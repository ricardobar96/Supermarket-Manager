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

import es.iespuertodelacruz.ricardo.Supermercado.dto.PedidoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.dto.ProductoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.ClienteService;
import es.iespuertodelacruz.ricardo.Supermercado.services.DetallepedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.PedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.ProductoService;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(PedidoRESTv2.class);
	
	@Autowired
	PedidoService pedidosService;
	
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
	public List<Pedido> getAll(){
		Cliente logged = getClienteLogged();
		String nombreL = logged.getNombre();
		
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		pedidosService
		.findAll()
		.forEach(p -> pedidos.add((Pedido) p) );
		
		ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();
		for (Pedido p : pedidos) {
			if(p.getCliente().getNombre().equals(nombreL)) {
				pedidosCliente.add(p);
			}
		}
		return pedidosCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPedidoById(@PathVariable Integer id){
		Cliente logged = getClienteLogged();
		String nombreL = logged.getNombre();
		
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		if (pedidoOPT.isPresent()) {
			if(pedidoOPT.get().getCliente().getNombre().equals(nombreL)) {
				return ResponseEntity.ok(pedidoOPT);
			}
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El pedido lo ha encargado otro cliente");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El pedido no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		
		if(pedidoOPT.isPresent()) {
			for (Detallepedido d : pedidoOPT.get().getDetallepedidos()) {
				detallepedidosService.delete(d);
				//pedidoOPT.get().getDetallepedidos().remove(d);
				//d.getPedido().removeDetallepedido(d);
			}
			pedidosService.deleteById(id);
			return ResponseEntity.ok("Pedido eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PedidoDTO pedidoDto){
		Pedido p = new Pedido();
		p.setDireccionEntrega(pedidoDto.getDireccionEntrega());
		p.setEntregado(pedidoDto.getEntregado());
		p.setEnviado(pedidoDto.getEnviado());
		p.setPagado(pedidoDto.getPagado());
	    p.setFecha(pedidoDto.getFecha());
	    p.setCliente(pedidoDto.getCliente());
	    
	    for(Detallepedido d : pedidoDto.getDetallepedidos()) {
	    	Optional<Detallepedido> optD = detallepedidosService.findById(d.getIddetallepedido());
	    	optD.get().getPedido().addDetallepedido(d);
	    }

		p.setDetallepedidos(pedidoDto.getDetallepedidos());
		pedidosService.save(p);
		return ResponseEntity.ok().body(new PedidoDTO(p));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDto){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		if(pedidoOPT.isPresent()) {
			Pedido p = pedidoOPT.get();

			if(pedidoDto.getDireccionEntrega()!=null) {
				p.setDireccionEntrega(pedidoDto.getDireccionEntrega());
			}
			if(pedidoDto.getEntregado()==0 || pedidoDto.getEntregado()==1) {
				p.setEntregado(pedidoDto.getEntregado());
			}
			if(pedidoDto.getEnviado()==0 || pedidoDto.getEnviado()==1) {
				p.setEnviado(pedidoDto.getEnviado());
			}
			if(pedidoDto.getPagado()==0 || pedidoDto.getPagado()==1) {
				p.setPagado(pedidoDto.getPagado());
			}
			if(pedidoDto.getDetallepedidos()!=null) {
				for (Detallepedido d : pedidoOPT.get().getDetallepedidos()) {
					pedidoDto.getDetallepedidos().remove(d);
				}
				for (Detallepedido d : pedidoDto.getDetallepedidos()) {
					Optional<Detallepedido> optDetalle = detallepedidosService.findById(d.getIddetallepedido());
					optDetalle.get().getPedido().addDetallepedido(d);
				}	

				p.setDetallepedidos(pedidoDto.getDetallepedidos());
			}
			if(pedidoDto.getFecha()!=null) {
				p.setFecha(pedidoDto.getFecha());
			}
			if(pedidoDto.getCliente()!=null) {
				p.setCliente(pedidoDto.getCliente());
			}
			
			return ResponseEntity.ok(pedidosService.save(p));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no existe");
		}
	}
}
