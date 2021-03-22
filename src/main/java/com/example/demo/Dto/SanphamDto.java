package com.example.demo.Dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Anh;
import com.example.demo.entity.Sanpham;
public class SanphamDto {
   private Long id;
   private String name;
   private int giaban;
   private int gianhap;
   private String trangthai;
   private int soluong;
   private LoaiDto loaiDto;
   private Set<AnhDto> anhDtos;
public Long getId() {
	return id;
}

public String getTrangthai() {
	return trangthai;
}

public int getSoluong() {
	return soluong;
}

public SanphamDto() {
	super();
}

public void setSoluong(int soluong) {
	this.soluong = soluong;
}

public void setTrangthai(String trangthai) {
	this.trangthai = trangthai;
}

public void setId(Long id) {
	this.id = id;
}

public int getGiaban() {
	return giaban;
}
public void setGiaban(int giaban) {
	this.giaban = giaban;
}
public int getGianhap() {
	return gianhap;
}
public void setGianhap(int gianhap) {
	this.gianhap = gianhap;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public LoaiDto getLoaiDto() {
	return loaiDto;
}
public void setLoaiDto(LoaiDto loaiDto) {
	this.loaiDto = loaiDto;
}
public Set<AnhDto> getAnhDtos() {
	return anhDtos;
}
public void setAnhDtos(Set<AnhDto> anhDtos) {
	this.anhDtos = anhDtos;
}
public SanphamDto(Sanpham sanpham) {
	if(sanpham !=null)
	{
		this.id=sanpham.getId();
		this.name=sanpham.getName();
		this.giaban=sanpham.getGiaban();
		this.gianhap=sanpham.getGianhap();
		this.trangthai=sanpham.getTrangthai();
	     if(sanpham.getLoaisp()!=null && sanpham.getId()!=null)
	     {
	    	 loaiDto=new LoaiDto(sanpham.getLoaisp());
	     }
	     if(sanpham.getAnhs().size()>0 && sanpham.getAnhs()!=null)
	     {
	    	 anhDtos=new HashSet<AnhDto>();
	    	 for(Anh a:sanpham.getAnhs())
	    	 {
	    		 if(a!=null && a.getId()!=null)
	    		 {
	    			 anhDtos.add(new AnhDto(a));
	    		 }
	    	 }
	     }
	}
}
   
}
