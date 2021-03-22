package com.example.demo.Dto;

import com.example.demo.entity.Loaisp;

public class LoaiDto {
  private Long id;
  private String name;
public Long getId() {
	return id;
}

public LoaiDto() {
	super();
}

public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public LoaiDto(Loaisp loaisp) {
	if(loaisp !=null)
	{
		this.id=loaisp.getId();
		this.name=loaisp.getName();
	}
}
  
}
