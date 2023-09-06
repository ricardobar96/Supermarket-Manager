package es.iespuertodelacruz.ricardo.Supermercado.controller.v1;

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

import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.ProductoService;

	@RestController
	@RequestMapping("/api/v1/productos")
	public class ProductoRESTv1 {
		private Logger logger = LoggerFactory.getLogger(ProductoRESTv1.class);
		@Autowired
		ProductoService productosService;
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
	}
