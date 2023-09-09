package es.ricardo.supermarket_manager.dto;

import java.util.List;

import es.ricardo.supermarket_manager.entities.Detallepedido;
import es.ricardo.supermarket_manager.entities.Producto;

public class ProductoDTO {
	private int idproducto;

	private String nombre;

	private double preciounidad;

	private int stock;
	
	private List<Detallepedido> detallepedidos;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(Producto p) {
		super();
		this.idproducto = p.getIdproducto();
		this.nombre = p.getNombre();
		this.preciounidad = p.getPreciounidad();
		this.stock = p.getStock();
		this.detallepedidos = p.getDetallepedidos();
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Detallepedido> getDetallepedidos() {
		return detallepedidos;
	}

	public void setDetallepedidos(List<Detallepedido> detallepedidos) {
		this.detallepedidos = detallepedidos;
	}
	
}
