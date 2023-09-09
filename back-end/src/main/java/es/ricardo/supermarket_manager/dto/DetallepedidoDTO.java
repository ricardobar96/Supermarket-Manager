package es.ricardo.supermarket_manager.dto;

import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Order;
import es.ricardo.supermarket_manager.entities.Product;

public class DetallepedidoDTO {
	private int iddetallepedido;

	private int cantidad;

	private double preciounidad;
	
	private Order pedido;
	
	private Product producto;

	public DetallepedidoDTO() {
		super();
	}

	public DetallepedidoDTO(DetailOrder d) {
		super();
		this.iddetallepedido = d.getIdDetailOrder();
		this.cantidad = d.getQuantity();
		this.preciounidad = d.getUnitPrice();
		this.pedido = d.getOrder();
		this.producto = d.getProduct();
	}

	public int getIddetallepedido() {
		return iddetallepedido;
	}

	public void setIddetallepedido(int iddetallepedido) {
		this.iddetallepedido = iddetallepedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public Order getPedido() {
		return pedido;
	}

	public void setPedido(Order pedido) {
		this.pedido = pedido;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}
}
