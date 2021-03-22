package com.example.demo.entity;

import javax.persistence.*;
@Entity
@Table(name = "chitiethang")
public class Chitietdonhang {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private int soluong;
   @OneToOne
   @JoinColumn(name = "sanphamId")
   private Sanpham sanpham;
   @ManyToOne
   @JoinColumn(name = "hoadonId")
   private Hoadon hoadon;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}
public Sanpham getSanpham() {
	return sanpham;
}
public void setSanpham(Sanpham sanpham) {
	this.sanpham = sanpham;
}
public Hoadon getHoadon() {
	return hoadon;
}
public void setHoadon(Hoadon hoadon) {
	this.hoadon = hoadon;
}
   
}
