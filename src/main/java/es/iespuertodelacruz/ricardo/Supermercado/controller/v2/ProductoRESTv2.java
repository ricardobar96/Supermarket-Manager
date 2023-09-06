package es.iespuertodelacruz.ricardo.Supermercado.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.Supermercado.dto.ProductoDTO;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.DetallepedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.ProductoService;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(ProductoRESTv2.class);
	
	@Autowired
	ProductoService productosService;
	
	@Autowired
	DetallepedidoService detallepedidosService;
	
	@GetMapping("")
	public List<Producto> getAll(){
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productosService
		.findAll()
		.forEach(p -> productos.add((Producto) p) );
		return productos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductoById(@PathVariable Integer id){
		Optional<Producto> productoOPT = productosService.findById(id);
		if (productoOPT.isPresent()) {
			return ResponseEntity.ok(productoOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Producto> productoOPT = productosService.findById(id);
		
		if(productoOPT.isPresent()) {
			for (Detallepedido d : productoOPT.get().getDetallepedidos()) {
				detallepedidosService.delete(d);
			}
			productosService.deleteById(id);
			return ResponseEntity.ok("Producto eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El producto no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductoDTO productoDto){
		Producto p = new Producto();
		p.setNombre(productoDto.getNombre());
		p.setPreciounidad(productoDto.getPreciounidad());
		
		p.setStock(productoDto.getStock());
		/*
		for(Detallepedido d : productoDto.getDetallepedidos()) {
	    	Optional<Detallepedido> optD = detallepedidosService.findById(d.getIddetallepedido());
	    	optD.get().getProducto().addDetallepedido(d);
	    }
		p.setDetallepedidos(productoDto.getDetallepedidos());
		*/
		
		productosService.save(p);
		return ResponseEntity.ok().body(new ProductoDTO(p));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductoDTO productoDto){
		Optional<Producto> productoOPT = productosService.findById(id);
		if(productoOPT.isPresent()) {
			Producto p = productoOPT.get();

			if(productoDto.getNombre()!=null) {
				p.setNombre(productoDto.getNombre());
			}
			if(productoDto.getPreciounidad()>0) {
				p.setPreciounidad(productoDto.getPreciounidad());
			}	
			
			if(productoDto.getStock()>0) {
				p.setStock(productoDto.getStock());
			}	
			
			/*
			if(productoDto.getDetallepedidos()!=null) {
				
				for (Detallepedido d : productoOPT.get().getDetallepedidos()) {
					d.getProducto().removeDetallepedido(d);
				}
				
				for(Detallepedido d : productoDto.getDetallepedidos()) {
			    	Optional<Detallepedido> optD = detallepedidosService.findById(d.getIddetallepedido());
			    	optD.get().getProducto().addDetallepedido(d);
			    }
				
				p.setDetallepedidos(productoDto.getDetallepedidos());
			}	
			*/
			
			return ResponseEntity.ok(productosService.save(p));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El producto no existe");
		}
	}
}
