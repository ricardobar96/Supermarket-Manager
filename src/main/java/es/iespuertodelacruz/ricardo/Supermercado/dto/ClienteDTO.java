package es.iespuertodelacruz.ricardo.Supermercado.dto;

import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;

public class ClienteDTO {
	private int idcliente;

	private String direccion;

	private String nombre;

	private String password;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente c) {
		super();
		this.idcliente = c.getIdcliente();
		this.direccion = c.getDireccion();
		this.nombre = c.getNombre();
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
