package com.example.demo.Dto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.example.demo.entity.Chitietdonhang;
import com.example.demo.entity.Hoadon;
public class HoadonDto {
	private Long id;
   private Set<CartDto> cartDtos;
   private KhachhangDto khachhangDto;
   private String trangthai;
   private Date ngayban;
   private String diachi;
public Set<CartDto> getCartDtos() {
	return cartDtos;
}
public void setCartDtos(Set<CartDto> cartDtos) {
	this.cartDtos = cartDtos;
}
public KhachhangDto getKhachhangDto() {
	return khachhangDto;
}
public void setKhachhangDto(KhachhangDto khachhangDto) {
	this.khachhangDto = khachhangDto;
}
public String getTrangthai() {
	return trangthai;
}
public void setTrangthai(String trangthai) {
	this.trangthai = trangthai;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public HoadonDto(Hoadon hoadon) {
	if(hoadon!=null)
	{
		this.id=hoadon.getId();
		this.ngayban=hoadon.getNgayban();
		this.trangthai=hoadon.getTrangthai();
		if(hoadon.getChitietdonhangs()!=null)
		{
			cartDtos=new HashSet<CartDto>();
			for(Chitietdonhang t:hoadon.getChitietdonhangs())
			{
				cartDtos.add(new CartDto(t));
			}
		}
		if(hoadon.getKhachhang()!=null) {
		khachhangDto=new KhachhangDto(hoadon.getKhachhang());
		}
	}
}  

}
