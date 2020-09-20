package com.privado.mongobackend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.privado.mongobackend.dao.TemplateRepository;
import com.privado.mongobackend.model.Template;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TemplateServiceTest {

	@InjectMocks
	private TemplateService templateService;

	@Mock
	private TemplateRepository templateDao;

	@Mock
	private ObjectMapper mapper;

	private static String templateString1 = "{\"type\":\"system\",\"entity\":\"entity\",\"customerId\":\"system\",\"law\":\"base\",\"fields\":[{\"field\":\"name\",\"label\":\"Name\",\"data_type\":\"short-text\",\"default\":\"Type name here..\",\"field_type\":\"basic_details\",\"field_type_label\":\"Basic Details\",\"is_removable\":false,\"mandatory\":true},{\"field\":\"description\",\"label\":\"Description\",\"data_type\":\"long-text\",\"default\":\"Type description here..\",\"field_type\":\"basic_details\",\"field_type_label\":\"Basic Details\",\"is_removable\":false,\"mandatory\":false},{\"field\":\"entity_type\",\"label\":\"Entity Type\",\"data_type\":\"options\",\"default\":\"\",\"field_type\":\"basic_details\",\"field_type_label\":\"Basic Details\",\"is_removable\":false,\"mandatory\":false},{\"field\":\"location\",\"label\":\"Location\",\"data_type\":\"options\",\"default\":\"\",\"field_type\":\"basic_details\",\"field_type_label\":\"Basic Details\",\"is_removable\":false,\"mandatory\":false}]}";

	private static String templateString2 = "{\"type\":\"system\",\"entity\":\"entity\",\"customerId\":\"system\",\"law\":\"GDPR\",\"fields\":[{\"field\":\"address\",\"label\":\"Address\",\"data_type\":\"long-text\",\"default\":\"Type address here..\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false},{\"field\":\"representative\",\"label\":\"Representative\",\"data_type\":\"options\",\"default\":\"Type the representative name here..\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false}]}";

	private static String customerTemplateString = "{\"type\":\"customer\",\"entity\":\"entity\",\"customerId\":\"99\",\"law\":\"base\",\"fields\":[{\"field\":\"representative_contact_details\",\"label\":\"Representative Contact Details\",\"data_type\":\"long-text\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type contact details here..\"},{\"field\":\"data_protection_officer\",\"label\":\"Data Protection Officer\",\"data_type\":\"options\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type the data protection officer name here..\"},{\"field\":\"dpo_contact_details\",\"label\":\"Data Protection Officer Contact Details\",\"data_type\":\"long-text\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type contact details here..\"},{\"field\":\"address\",\"label\":\"Address\",\"data_type\":\"long-text\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type address here..\"},{\"field\":\"representative\",\"label\":\"Representative\",\"data_type\":\"options\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type the representative name here..\"},{\"field\":\"representative_contact_details\",\"label\":\"Representative Contact Details\",\"data_type\":\"long-text\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type contact details here..\"},{\"field\":\"data_protection_officer\",\"label\":\"Data Protection Officer\",\"data_type\":\"options\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type the data protection officer name here..\"},{\"field\":\"dpo_contact_details\",\"label\":\"Data Protection Officer Contact Details\",\"data_type\":\"long-text\",\"field_type\":\"contact_details\",\"field_type_label\":\"Contact Details\",\"is_removable\":false,\"mandatory\":false,\"default\":\"Type contact details here..\"}]}";

	@Test
	public void testFindTemplateByCustomerId() throws JsonMappingException, JsonProcessingException {
		Optional<Template> customerTemplate = Optional
				.of(new ObjectMapper().readValue(customerTemplateString, Template.class));
		when(templateDao.findTemplateByCustomerId("99", "customer")).thenReturn(customerTemplate);
		Template foundTemplate = templateService.findTemplateByCustomerId("99");
		assertNotNull(foundTemplate);
	}

	@Test
	public void testFindAll() throws JsonMappingException, JsonProcessingException {
		Template template = new ObjectMapper().readValue(templateString1, Template.class);
		Template customerTemplate = new ObjectMapper().readValue(customerTemplateString, Template.class);
		List<Template> templateList = new ArrayList<Template>();
		templateList.add(customerTemplate);
		templateList.add(template);
		when(templateDao.findAll()).thenReturn(templateList);
		List<Template> foundTemplates = templateService.findAll();
		assertNotEquals(0, foundTemplates.size());
	}

	@Test
	public void testSaveTemplate() throws JsonMappingException, JsonProcessingException {
		Template customerTemplate = new ObjectMapper().readValue(customerTemplateString, Template.class);
		when(templateDao.save(customerTemplate)).thenReturn(null);
		templateService.saveTemplate(customerTemplate);
	}

	@Test
	public void testFindAllByType() throws JsonMappingException, JsonProcessingException {
		Template template1 = new ObjectMapper().readValue(templateString1, Template.class);
		Template template2 = new ObjectMapper().readValue(templateString2, Template.class);
		List<Template> templateList = new ArrayList<>();
		Optional<List<Template>> foundTemplateList = Optional.of(templateList);
		templateList.add(template1);
		templateList.add(template2);
		when(templateDao.findByType("system")).thenReturn(foundTemplateList);
		assertNotEquals(0, templateService.findAllByType("system").size());
	}
}
