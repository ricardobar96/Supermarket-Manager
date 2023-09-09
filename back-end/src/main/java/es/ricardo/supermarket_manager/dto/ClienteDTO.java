package es.ricardo.supermarket_manager.dto;

import es.ricardo.supermarket_manager.entities.Client;

public class ClienteDTO {
	private int idcliente;

	private String direccion;

	private String nombre;

	private String password;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Client c) {
		super();
		this.idcliente = c.getIdClient();
		this.direccion = c.getAddress();
		this.nombre = c.getName();
		this.password = c.getPassword();
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
