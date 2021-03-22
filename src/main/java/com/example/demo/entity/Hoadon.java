package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "hoadon")
public class Hoadon {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
	@Column(name="ngayban")
   private Date ngayban;
	@Column(name="name")
	private String name;
	@Column(name="diachi")
	private String diachi;
	
	@ManyToOne
	 @JoinColumn(name = "khachhang_id")
   private Khachhang khachhang;
	@ManyToOne
	 @JoinColumn(name = "nhanvien_id")
    private Nhanvien nhanvien;
	@OneToMany(mappedBy = "hoadon",cascade = CascadeType.ALL)
    private Set<Chitietdonhang>Chitietdonhangs;
	
	public Set<Chitietdonhang> getChitietdonhangs() {
		return Chitietdonhangs;
	}
	public void setChitietdonhangs(Set<Chitietdonhang> chitietdonhangs) {
		Chitietdonhangs = chitietdonhangs;
	}
	@Column(name = "trangthai")
	private String trangthai;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
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
	public Date getNgayban() {
		return ngayban;
	}
	public void setNgayban(Date ngayban) {
		this.ngayban = ngayban;
	}
	public Khachhang getKhachhang() {
		return khachhang;
	}
	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}
	public Nhanvien getNhanvien() {
		return nhanvien;
	}
	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}
   
}
