package es.iespuertodelacruz.ricardo.Supermercado.dto;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Detallepedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;

public class DetallepedidoDTO {
	private int iddetallepedido;

	private int cantidad;

	private double preciounidad;
	
	private Pedido pedido;
	
	private Producto producto;

	public DetallepedidoDTO() {
		super();
	}

	public DetallepedidoDTO(Detallepedido d) {
		super();
		this.iddetallepedido = d.getIddetallepedido();
		this.cantidad = d.getCantidad();
		this.preciounidad = d.getPreciounidad();
		this.pedido = d.getPedido();
		this.producto = d.getProducto();
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
