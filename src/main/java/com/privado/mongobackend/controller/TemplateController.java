package com.privado.mongobackend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privado.mongobackend.model.Fields;
import com.privado.mongobackend.model.Template;
import com.privado.mongobackend.service.TemplateService;
import com.privado.mongobackend.util.ValidateTemplate;

@RestController
public class TemplateController {

	@Autowired
	private TemplateService templateService;

	@Autowired
	private ValidateTemplate validateTemplate;

	private static final String type = "customer";
	private static final String entity = "entity";
	private static final String law = "base";

	@PostMapping("te/customer/{customer_id}/templates")
	public ResponseEntity<Template> postTemplateById(@PathVariable String customer_id) {
		try {
			Integer.parseInt(customer_id);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		List<Template> templates = templateService.findAllByType("system");
		Template customer = new Template();
		customer.setType(type);
		customer.setCustomerId(customer_id);
		customer.setFields(new ArrayList<Fields>());
		for (Template template : templates) {
			List<Fields> fields = template.getFields();
			customer.getFields().addAll(fields);
		}
		customer.setEntity(entity);
		customer.setLaw(law);
		Template existingTemplate = templateService.findTemplateByCustomerId(customer_id);
		if (existingTemplate == null) {
			templateService.saveTemplate(customer);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("te/customer/{customer_id}/templates")
	public ResponseEntity<Template> getTemplateById(@PathVariable String customer_id)
			throws JSONException, IOException {
		try {
			Integer.parseInt(customer_id);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Template foundTemplate = templateService.findTemplateByCustomerId(customer_id);
		ObjectMapper obj = new ObjectMapper();
		String jsonInString = obj.writeValueAsString(foundTemplate);
		JSONObject jsonObj = new JSONObject(jsonInString);
		if (validateTemplate.validateTemplate(jsonObj)) {
			if (foundTemplate.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundTemplate);
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundTemplate);
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PostMapping("te/save")
	public ResponseEntity<Template> saveTemplate(@RequestBody Template template) {
		templateService.saveTemplate(template);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(template);
	}
}
