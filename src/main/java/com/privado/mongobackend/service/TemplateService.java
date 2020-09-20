package com.privado.mongobackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privado.mongobackend.dao.TemplateRepository;
import com.privado.mongobackend.model.Template;

@Service
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	public Template findTemplateByCustomerId(String customerId) {

		Optional<Template> templateByCustomerId = templateRepository.findTemplateByCustomerId(customerId, "customer");
		if (templateByCustomerId.isPresent()) {
			return templateByCustomerId.get();
		} else {
			return null;
		}
	}

	public List<Template> findAllByType(String type) {
		Optional<List<Template>> templates = templateRepository.findByType(type);
		if (templates.isPresent()) {
			return templates.get();
		} else {
			throw new RuntimeException("Template not found with type system ");
		}
	}

	public List<Template> findAll() {
		return templateRepository.findAll();
	}

	public void saveTemplate(Template customer) {
		templateRepository.save(customer);
	}
}
