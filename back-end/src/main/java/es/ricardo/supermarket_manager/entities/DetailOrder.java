package es.ricardo.supermarket_manager.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the detailOrder database table.
 * 
 */
@Entity
@NamedQuery(name="DetailOrder.findAll", query="SELECT d FROM DetailOrder d")
public class DetailOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDetailOrder;

	private int quantity;

	private double unitPrice;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="fkIdPedido")
	@JsonIgnore
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="fkIdProduct")	
	private Product product;

	public DetailOrder() {
	}

	public int getIdDetailOrder() {
		return this.idDetailOrder;
	}

	public void setIdDetailOrder(int idDetailOrder) {
		this.idDetailOrder = idDetailOrder;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}