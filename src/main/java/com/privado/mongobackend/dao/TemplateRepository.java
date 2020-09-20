package com.privado.mongobackend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.privado.mongobackend.model.Template;

@Repository
public interface TemplateRepository extends MongoRepository<Template,String>{
	
	Optional<Template> findTemplateByCustomerId(String customerId,String type);
	
	Optional<Template> findByCustomerId(String customerId);

	Optional<List<Template>> findByType(String type);
	
	
}
