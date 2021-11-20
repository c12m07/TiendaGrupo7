package co.edu.unbosque.lagenericaGr38.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.lagenericaGr38.model.Cliente;

public interface ClientesRepository extends MongoRepository<Cliente, String>{
	
}
