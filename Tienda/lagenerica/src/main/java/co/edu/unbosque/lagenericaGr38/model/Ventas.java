package co.edu.unbosque.lagenericaGr38.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_ventas")
public class Ventas {
	
	@Id
	private String id;
	private String codigoV;
	private String cedula;
	private
	
	public Ventas() {}

}
