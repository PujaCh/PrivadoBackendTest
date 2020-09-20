package com.privado.mongobackend.model;

public class OptionsUrl {
	private String url;
	private String request_type;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	@Override
	public String toString() {
		return "OptionsUrl [url=" + url + ", request_type=" + request_type + "]";
	}
	
	
}
