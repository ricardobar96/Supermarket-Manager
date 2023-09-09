package es.ricardo.supermarket_manager.dto;

import java.util.List;

import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Product;

public class ProductoDTO {
	private int idproducto;

	private String nombre;

	private double preciounidad;

	private int stock;
	
	private List<DetailOrder> detallepedidos;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(Product p) {
		super();
		this.idproducto = p.getIdProduct();
		this.nombre = p.getName();
		this.preciounidad = p.getUnitPrice();
		this.stock = p.getStock();
		this.detallepedidos = p.getDetailOrder();
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

	public List<DetailOrder> getDetallepedidos() {
		return detallepedidos;
	}

	public void setDetallepedidos(List<DetailOrder> detallepedidos) {
		this.detallepedidos = detallepedidos;
	}
	
}
