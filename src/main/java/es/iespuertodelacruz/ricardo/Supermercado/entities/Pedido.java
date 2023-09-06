package es.iespuertodelacruz.ricardo.Supermercado.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idpedido;

	@Column(name="direccion_entrega")
	private String direccionEntrega;

	private byte entregado;

	private byte enviado;

	private long fecha;

	private byte pagado;

	//bi-directional many-to-one association to Detallepedido
	@OneToMany(mappedBy="pedido")
	private List<Detallepedido> detallepedidos;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="fkidcliente")
	private Cliente cliente;

	public Pedido() {
	}

	public int getIdpedido() {
		return this.idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public String getDireccionEntrega() {
		return this.direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public byte getEntregado() {
		return this.entregado;
	}

	public void setEntregado(byte entregado) {
		this.entregado = entregado;
	}

	public byte getEnviado() {
		return this.enviado;
	}

	public void setEnviado(byte enviado) {
		this.enviado = enviado;
	}

	public Date getFecha() {
		return new Date(this.fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha.getTime();
	}

	public byte getPagado() {
		return this.pagado;
	}

	public void setPagado(byte pagado) {
		this.pagado = pagado;
	}

	public List<Detallepedido> getDetallepedidos() {
		return this.detallepedidos;
	}

	public void setDetallepedidos(List<Detallepedido> detallepedidos) {
		this.detallepedidos = detallepedidos;
	}

	public Detallepedido addDetallepedido(Detallepedido detallepedido) {
		getDetallepedidos().add(detallepedido);
		detallepedido.setPedido(this);

		return detallepedido;
	}

	public Detallepedido removeDetallepedido(Detallepedido detallepedido) {
		getDetallepedidos().remove(detallepedido);
		detallepedido.setPedido(null);

		return detallepedido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}