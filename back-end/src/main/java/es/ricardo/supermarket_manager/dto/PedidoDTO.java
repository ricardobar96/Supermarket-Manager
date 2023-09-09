package es.ricardo.supermarket_manager.dto;

import java.util.Date;
import java.util.List;

import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.entities.DetailOrder;
import es.ricardo.supermarket_manager.entities.Order;

public class PedidoDTO {
	private int idpedido;
	
	private String direccionEntrega;

	private byte entregado;

	private byte enviado;

	private long fecha;

	private byte pagado;
	
	private List<DetailOrder> detallepedidos;
	
	private Client cliente;

	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Order p) {
		super();
		this.idpedido = p.getIdOrder();
		this.direccionEntrega = p.getAddressOrder();
		this.entregado = p.getDelivered();
		this.enviado = p.getSent();
		this.fecha = p.getDate().getTime();
		this.pagado = p.getPaid();
		this.detallepedidos = p.getDetailOrder();
		this.cliente = p.getClient();
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public byte getEntregado() {
		return entregado;
	}

	public void setEntregado(byte entregado) {
		this.entregado = entregado;
	}

	public byte getEnviado() {
		return enviado;
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
		return pagado;
	}

	public void setPagado(byte pagado) {
		this.pagado = pagado;
	}

	public List<DetailOrder> getDetallepedidos() {
		return detallepedidos;
	}

	public void setDetallepedidos(List<DetailOrder> detallepedidos) {
		this.detallepedidos = detallepedidos;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
}
