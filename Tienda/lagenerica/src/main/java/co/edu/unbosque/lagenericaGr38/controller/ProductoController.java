package co.edu.unbosque.lagenericaGr38.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.lagenericaGr38.model.Cliente;
import co.edu.unbosque.lagenericaGr38.model.Producto;
import co.edu.unbosque.lagenericaGr38.repository.ProductosRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	ProductosRepository productoRepo;
	
	@GetMapping("/productos")
	  public ResponseEntity<List<Producto>> getAllProductos() {
		  try {
			    List<Producto> productos = new ArrayList<Producto>();

			    productoRepo.findAll().forEach(productos::add);

			    if (productos.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }

			    return new ResponseEntity<>(productos, HttpStatus.OK);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	
	@GetMapping("/productos/{id}")
	  public ResponseEntity<Producto> getProductoById(@PathVariable("id") String id) {
		  		Optional<Producto> productoData = productoRepo.findById(id);

		  if (productoData.isPresent()) {
		    return new ResponseEntity<>(productoData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }

	  @PostMapping("/productos")
	  public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		  try {
			    Producto _producto = productoRepo.save(new Producto(producto.getId(), producto.getNombre_producto(), producto.getNitproveedor(), producto.getPrecio_compra(), producto.getIvacompra(), producto.getPrecio_venta()));
			    return new ResponseEntity<>(_producto, HttpStatus.CREATED);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

	  @PutMapping("/productos/{id}")
	  public ResponseEntity<Producto> updateProducto(@PathVariable("id") String id, @RequestBody Producto producto) {
		  Optional<Producto> productoData = productoRepo.findById(id);

		  if (productoData.isPresent()) {
		    Producto _producto = productoData.get();
		    _producto.setId(producto.getId());
		    _producto.setNombre_producto(producto.getNombre_producto());
		    _producto.setNitproveedor(producto.getNitproveedor());
		    _producto.setPrecio_compra(producto.getPrecio_compra());
		    _producto.setIvacompra(producto.getIvacompra());
		    _producto.setPrecio_venta(producto.getPrecio_venta());
		    return new ResponseEntity<>(productoRepo.save(_producto), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }

	  @DeleteMapping("/productos/{id}")
	  public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") String id) {
		  try {
			  	productoRepo.deleteById(id);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

	  @DeleteMapping("/productos")
	  public ResponseEntity<HttpStatus> deleteAllProductos() {
		  try {
			  	productoRepo.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }

}
