package com.example.demo.entity;

import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "sanpham")
public class Sanpham {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;
   @Column(name="name")
   private String name;
   @Column(name="soluong")
   private int soluong;
   @Column(name="gianhap")
   private int gianhap;
   @Column(name="giaban")
   private int giaban;
   @Column(name="trangthai")
   private String trangthai;
   @ManyToOne
   @JoinColumn(name = "loai_id")
   private  Loaisp loaisp;
   @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
   private Set<Anh>anhs;
public Long getId() {
	return id;
}

public String getTrangthai() {
	return trangthai;
}

public void setTrangthai(String trangthai) {
	this.trangthai = trangthai;
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
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}

public int getGianhap() {
	return gianhap;
}

public void setGianhap(int gianhap) {
	this.gianhap = gianhap;
}

public int getGiaban() {
	return giaban;
}

public void setGiaban(int giaban) {
	this.giaban = giaban;
}

public Loaisp getLoaisp() {
	return loaisp;
}
public void setLoaisp(Loaisp loaisp) {
	this.loaisp = loaisp;
}
public Set<Anh> getAnhs() {
	return anhs;
}
public void setAnhs(Set<Anh> anhs) {
	this.anhs = anhs;
}  
}
