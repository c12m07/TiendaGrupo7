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
import co.edu.unbosque.lagenericaGr38.repository.ClientesRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClientesRepository clienteRepo;
	
	@GetMapping("/clientes/listar")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			clienteRepo.findAll().forEach(clientes::add);
		
			if(clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/clientes/{id}")
	  public ResponseEntity<Cliente> getClienteById(@PathVariable("id") String id) {
		  		Optional<Cliente> clienteData = clienteRepo.findById(id);

		  if (clienteData.isPresent()) {
		    return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente _cliente = clienteRepo.save(new Cliente(cliente.getId(), cliente.getDireccion_cliente(), cliente.getEmail_cliente(), cliente.getNombre_cliente(), cliente.getTelefono_cliente()));
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id, @RequestBody Cliente cliente){
		Optional<Cliente> clienteData = clienteRepo.findById(id);
		
		if (clienteData.isPresent()) {
			Cliente _cliente = clienteData.get();
			_cliente.setId(cliente.getId());
			_cliente.setDireccion_cliente(cliente.getDireccion_cliente());
			_cliente.setEmail_cliente(cliente.getEmail_cliente());
			_cliente.setNombre_cliente(cliente.getNombre_cliente());
			_cliente.setTelefono_cliente(cliente.getTelefono_cliente());
			return new ResponseEntity<>(clienteRepo.save(_cliente), HttpStatus.OK);	
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") String id){
		try {
			clienteRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/clientes")
	public ResponseEntity<HttpStatus> deleteAllClientes() {
		try {
			clienteRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}