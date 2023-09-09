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

import es.ricardo.supermarket_manager.dto.PedidoDTO;
import es.ricardo.supermarket_manager.dto.ProductoDTO;
import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Order;
import es.ricardo.supermarket_manager.entities.Product;
import es.ricardo.supermarket_manager.services.ClientService;
import es.ricardo.supermarket_manager.services.DetallepedidoService;
import es.ricardo.supermarket_manager.services.PedidoService;
import es.ricardo.supermarket_manager.services.ProductoService;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(PedidoRESTv2.class);
	
	@Autowired
	PedidoService pedidosService;
	
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
	public List<Order> getAll(){
		Client logged = getClienteLogged();
		String nameL = logged.getName();
		
		ArrayList<Order> pedidos = new ArrayList<Order>();
		pedidosService
		.findAll()
		.forEach(p -> pedidos.add((Order) p) );
		
		ArrayList<Order> pedidosCliente = new ArrayList<Order>();
		for (Order p : pedidos) {
			if(p.getClient().getName().equals(nameL)) {
				pedidosCliente.add(p);
			}
		}
		return pedidosCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPedidoById(@PathVariable Integer id){
		Client logged = getClienteLogged();
		String nameL = logged.getName();
		
		Optional<Order> pedidoOPT = pedidosService.findById(id);
		if (pedidoOPT.isPresent()) {
			if(pedidoOPT.get().getClient().getName().equals(nameL)) {
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
		Optional<Order> pedidoOPT = pedidosService.findById(id);
		
		if(pedidoOPT.isPresent()) {
			for (DetailOrder d : pedidoOPT.get().getDetailOrder()) {
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
		Order p = new Order();
		p.setAddressOrder(pedidoDto.getDireccionEntrega());
		p.setDelivered(pedidoDto.getEntregado());
		p.setSent(pedidoDto.getEnviado());
		p.setPaid(pedidoDto.getPagado());
	    p.setDate(pedidoDto.getFecha());
	    p.setClient(pedidoDto.getCliente());
	    
	    for(DetailOrder d : pedidoDto.getDetallepedidos()) {
	    	Optional<DetailOrder> optD = detallepedidosService.findById(d.getIdDetailOrder());
	    	optD.get().getOrder().addDetailOrder(d);
	    }

		p.setDetailOrder(pedidoDto.getDetallepedidos());
		pedidosService.save(p);
		return ResponseEntity.ok().body(new PedidoDTO(p));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDto){
		Optional<Order> pedidoOPT = pedidosService.findById(id);
		if(pedidoOPT.isPresent()) {
			Order p = pedidoOPT.get();

			if(pedidoDto.getDireccionEntrega()!=null) {
				p.setAddressOrder(pedidoDto.getDireccionEntrega());
			}
			if(pedidoDto.getEntregado()==0 || pedidoDto.getEntregado()==1) {
				p.setDelivered(pedidoDto.getEntregado());
			}
			if(pedidoDto.getEnviado()==0 || pedidoDto.getEnviado()==1) {
				p.setSent(pedidoDto.getEnviado());
			}
			if(pedidoDto.getPagado()==0 || pedidoDto.getPagado()==1) {
				p.setPaid(pedidoDto.getPagado());
			}
			if(pedidoDto.getDetallepedidos()!=null) {
				for (DetailOrder d : pedidoOPT.get().getDetailOrder()) {
					pedidoDto.getDetallepedidos().remove(d);
				}
				for (DetailOrder d : pedidoDto.getDetallepedidos()) {
					Optional<DetailOrder> optDetalle = detallepedidosService.findById(d.getIdDetailOrder());
					optDetalle.get().getOrder().addDetailOrder(d);
				}	

				p.setDetailOrder(pedidoDto.getDetallepedidos());
			}
			if(pedidoDto.getFecha()!=null) {
				p.setDate(pedidoDto.getFecha());
			}
			if(pedidoDto.getCliente()!=null) {
				p.setClient(pedidoDto.getCliente());
			}
			
			return ResponseEntity.ok(pedidosService.save(p));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no existe");
		}
	}
}
