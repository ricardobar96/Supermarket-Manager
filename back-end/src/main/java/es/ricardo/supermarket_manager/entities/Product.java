package es.ricardo.supermarket_manager.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProduct;

	private String name;

	private double unitPrice;

	private int stock;

	//bi-directional many-to-one association to DetailOrder
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List<DetailOrder> detailOrder;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<DetailOrder> getDetailOrder() {
		return this.detailOrder;
	}

	public void setDetailOrder(List<DetailOrder> detailOrder) {
		this.detailOrder = detailOrder;
	}

	public DetailOrder addDetailOrder(DetailOrder detailOrder) {
		getDetailOrder().add(detailOrder);
		detailOrder.setProduct(this);

		return detailOrder;
	}

	public DetailOrder removeDetailOrder(DetailOrder detailOrder) {
		getDetailOrder().remove(detailOrder);
		detailOrder.setProduct(null);

		return detailOrder;
	}

}