package com.example.demo.Dto;

import com.example.demo.entity.Chitietdonhang;

public class CartDto {
   private SanphamDto sanpham;
   private int soluong;
    private int thanhtien;
 
public SanphamDto getSanpham() {
	return sanpham;
}
public void setSanphamDto(SanphamDto sanpham) {
	this.sanpham = sanpham;
}
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}
public CartDto(SanphamDto sanpham, int soluong) {
	super();
	this.sanpham = sanpham;
	this.soluong = soluong;
	
}
public CartDto() {
	super();
}
public void setSanpham(SanphamDto sanpham) {
	this.sanpham = sanpham;
}
public int getThanhtien() {
	return thanhtien;
}
public void setThanhtien(int thanhtien) {
	this.thanhtien = thanhtien;
}
public CartDto(Chitietdonhang chitietdonhang) {
	super();
    if(chitietdonhang!=null)
    {
    this.soluong=chitietdonhang.getSoluong();
    if(chitietdonhang.getSanpham()!=null)
    {
    	sanpham=new SanphamDto();
       sanpham.setName(chitietdonhang.getSanpham().getName());
       
    }
    }

}

  
}
