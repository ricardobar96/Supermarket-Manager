package es.iespuertodelacruz.ricardo.Supermercado.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the detallepedido database table.
 * 
 */
@Entity
@NamedQuery(name="Detallepedido.findAll", query="SELECT d FROM Detallepedido d")
public class Detallepedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int iddetallepedido;

	private int cantidad;

	private double preciounidad;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="fkidpedido")
	@JsonIgnore
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="fkidproducto")	
	private Producto producto;

	public Detallepedido() {
	}

	public int getIddetallepedido() {
		return this.iddetallepedido;
	}

	public void setIddetallepedido(int iddetallepedido) {
		this.iddetallepedido = iddetallepedido;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPreciounidad() {
		return this.preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}