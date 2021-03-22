package com.example.demo.Dto;

import com.example.demo.entity.Anh;

public class AnhDto {
  private Long id;
  private String url;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUrl() {
	return url;
}
public void setName(String url) {
	this.url = url;
}

public AnhDto() {
	super();
}
public AnhDto(Anh anh) {
if(anh!=null) {
	this.id = anh.getId();
	this.url = anh.getUrl();
}
	
}
  
}
