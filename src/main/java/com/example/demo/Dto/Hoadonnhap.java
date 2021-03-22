package com.example.demo.Dto;

public class Hoadonnhap {
	 private Long id;
	 private KhachhangDto khachhangDto;
	 private String trangthai;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	 
}
