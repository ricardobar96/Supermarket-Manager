package es.ricardo.supermarket_manager.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idOrder;

	@Column(name="address_order")
	private String addressOrder;

	private byte delivered;

	private byte sent;

	private long date;

	private byte paid;

	//bi-directional many-to-one association to DetailOrder
	@OneToMany(mappedBy="order")
	private List<DetailOrder> detailOrder;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="fkIdClient")
	private Client client;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAddressOrder() {
		return this.addressOrder;
	}

	public void setAddressOrder(String addressOrder) {
		this.addressOrder = addressOrder;
	}

	public byte getDelivered() {
		return this.delivered;
	}

	public void setDelivered(byte delivered) {
		this.delivered = sent;
	}

	public byte getSent() {
		return this.sent;
	}

	public void setSent(byte sent) {
		this.sent = sent;
	}

	public Date getDate() {
		return new Date(this.date);
	}

	public void setDate(Date date) {
		this.date = date.getTime();
	}

	public byte getPaid() {
		return this.paid;
	}

	public void setPaid(byte paid) {
		this.paid = paid;
	}

	public List<DetailOrder> getDetailOrder() {
		return this.detailOrder;
	}

	public void setDetailOrder(List<DetailOrder> detailOrder) {
		this.detailOrder = detailOrder;
	}

	public DetailOrder addDetailOrder(DetailOrder detailOrder) {
		getDetailOrder().add(detailOrder);
		detailOrder.setOrder(this);

		return detailOrder;
	}

	public DetailOrder removeDetailOrder(DetailOrder detailOrder) {
		getDetailOrder().remove(detailOrder);
		detailOrder.setOrder(null);

		return detailOrder;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}