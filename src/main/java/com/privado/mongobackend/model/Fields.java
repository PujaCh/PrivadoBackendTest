package com.privado.mongobackend.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Fields implements Serializable {

	private String field;
	private String label;
	private String data_type;
	@Field("default")
	@JsonProperty("default")
	private String default1;
	private String field_type;
	private String field_type_label;
	private boolean is_removable;
	private boolean mandatory;
	//Optional<T>
	List<String> options_list;
	OptionsUrl options_url;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getDefault1() {
		return default1;
	}
	public void setDefault1(String default1) {
		this.default1 = default1;
	}
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	public String getField_type_label() {
		return field_type_label;
	}
	public void setField_type_label(String field_type_label) {
		this.field_type_label = field_type_label;
	}
	public boolean isIs_removable() {
		return is_removable;
	}
	public void setIs_removable(boolean is_removable) {
		this.is_removable = is_removable;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public List<String> getOptions_list() {
		return options_list;
	}
	public void setOptions_list(List<String> options_list) {
		this.options_list = options_list;
	}
	public OptionsUrl getOptions_url() {
		return options_url;
	}
	public void setOptions_url(OptionsUrl options_url) {
		this.options_url = options_url;
	}
	@Override
	public String toString() {
		return "Fields [field=" + field + ", label=" + label + ", data_type=" + data_type + ", default1=" + default1
				+ ", field_type=" + field_type + ", field_type_label=" + field_type_label + ", is_removable="
				+ is_removable + ", mandatory=" + mandatory + ", options_list=" + options_list + ", options_url="
				+ options_url + "]";
	}
	
}
