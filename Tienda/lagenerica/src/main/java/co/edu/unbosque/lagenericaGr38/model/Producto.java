package co.edu.unbosque.lagenericaGr38.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_productos")
public class Producto {

	@Id
	private String id;
	private String nombre_producto;
	private Integer nitproveedor;
	private Integer precio_compra;
	private Integer ivacompra;
	private Integer precio_venta;
	
	public Producto() {}
	
	public Producto(String id, String nombre_producto, Integer nitproveedor, Integer precio_compra,
			Integer ivacompra, Integer precio_venta) {
		this.id= id;
		this.nombre_producto = nombre_producto;
		this.nitproveedor = nitproveedor;
		this.precio_compra = precio_compra;
		this.ivacompra = ivacompra;
		this.precio_venta = precio_venta;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public Integer getNitproveedor() {
		return nitproveedor;
	}
	public void setNitproveedor(Integer nitproveedor) {
		this.nitproveedor = nitproveedor;
	}
	public Integer getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(Integer precio_compra) {
		this.precio_compra = precio_compra;
	}
	public Integer getIvacompra() {
		return ivacompra;
	}
	public void setIvacompra(Integer ivacompra) {
		this.ivacompra = ivacompra;
	}
	public Integer getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(Integer precio_venta) {
		this.precio_venta = precio_venta;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre_producto=" + nombre_producto + ", nitproveedor="
				+ nitproveedor + ", precio_compra=" + precio_compra + ", ivacompra=" + ivacompra + ", precio_venta="
				+ precio_venta + "]";
	}
}
