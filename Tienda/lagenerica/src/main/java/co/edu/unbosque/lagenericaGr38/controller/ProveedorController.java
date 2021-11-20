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
import co.edu.unbosque.lagenericaGr38.model.Proveedores;
import co.edu.unbosque.lagenericaGr38.repository.ProveedoresRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProveedorController {

	@Autowired
	ProveedoresRepository proveedorRepo;
	
	@GetMapping("/proveedores")
	public ResponseEntity<List<Proveedores>> getAllProveedres(){
		try{
			List<Proveedores> proveedor = new ArrayList<Proveedores>();

			proveedorRepo.findAll().forEach(proveedor::add);

			if(proveedor.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(proveedor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/proveedores/{id}")
	  public ResponseEntity<Proveedores> getProveedorById(@PathVariable("id") String id) {
		  		Optional<Proveedores> proveedorData = proveedorRepo.findById(id);

		  if (proveedorData.isPresent()) {
		    return new ResponseEntity<>(proveedorData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
	
	@PostMapping("/proveedores")
	public ResponseEntity<Proveedores> createProveedor(@RequestBody Proveedores proveedor){
		try {
			Proveedores _proveedor= proveedorRepo.save(new Proveedores(proveedor.getId(), proveedor.getCiudad_proveedor(), proveedor.getDireecion_proveedor(), proveedor.getNombre_proveedor(), proveedor.getTelefono_proveedor()));
			return new ResponseEntity<>(_proveedor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<Proveedores> updateProveedor(@PathVariable("id") String id, @RequestBody Proveedores proveedor){
		Optional<Proveedores> proveedorData = proveedorRepo.findById(id);
		
		if (proveedorData.isPresent()) {
			Proveedores _proveedor = proveedorData.get();
			_proveedor.setId(proveedor.getId());
			_proveedor.setCiudad_proveedor(proveedor.getCiudad_proveedor());
			_proveedor.setDireecion_proveedor(proveedor.getDireecion_proveedor());
			_proveedor.setNombre_proveedor(proveedor.getNombre_proveedor());
			_proveedor.setTelefono_proveedor(proveedor.getTelefono_proveedor());
			return new ResponseEntity<>(proveedorRepo.save(_proveedor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<HttpStatus> deleteProveedor(@PathVariable("id") String id){
		try {
			proveedorRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/proveedores")
	public ResponseEntity<HttpStatus> deleteAllProveedores(){
		try {
			proveedorRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
