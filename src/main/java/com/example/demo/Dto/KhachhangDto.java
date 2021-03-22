package com.example.demo.Dto;

import com.example.demo.entity.Khachhang;

public class KhachhangDto {
private Long id;
   private String name;
   private String sodt;
   private String email;
   private String diachi;
   
public String getDiachi() {
	return diachi;
}
public void setDiachi(String diachi) {
	this.diachi = diachi;
}

public KhachhangDto() {
	super();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSodt() {
	return sodt;
}
public void setSodt(String sodt) {
	this.sodt = sodt;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public KhachhangDto(Khachhang khachhang) {
	if(khachhang!=null) {
   this.name=khachhang.getName();
   this.email=khachhang.getEmail();
   this.sodt=khachhang.getSdt();
	}
}
 
}
