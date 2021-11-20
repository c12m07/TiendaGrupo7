package co.edu.unbosque.lagenericaGr38.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_clientes")
public class Cliente {

	@Id
	private String id;
	private String direccion_cliente;
	private String email_cliente;
	private String nombre_cliente;
	private String telefono_cliente;
	
	public Cliente() {}

	public Cliente(String id, String direccion_cliente, String email_cliente, String nombre_cliente,
			String telefono_cliente) {
		this.id = id;
		this.direccion_cliente = direccion_cliente;
		this.email_cliente = email_cliente;
		this.nombre_cliente = nombre_cliente;
		this.telefono_cliente = telefono_cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getTelefono_cliente() {
		return telefono_cliente;
	}

	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", direccion_cliente=" + direccion_cliente
				+ ", email_cliente=" + email_cliente + ", nombre_cliente=" + nombre_cliente + ", telefono_cliente="
				+ telefono_cliente + "]";
	}
}
