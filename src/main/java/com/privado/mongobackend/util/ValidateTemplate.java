package com.privado.mongobackend.util;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTemplate {

	
	private VelocityEngine velocity = new VelocityEngine();

	private VelocityContext context= new VelocityContext();

	public boolean validateTemplate(JSONObject jsonObj) {
		velocity.init();
		context.put("template", jsonObj);
		Writer writer = new StringWriter();
		boolean mergedTemplate =velocity.mergeTemplate("src\\main\\resources\\templates\\customerinfos.vm", "UTF-8", context, writer);
		return mergedTemplate;
	}
}
