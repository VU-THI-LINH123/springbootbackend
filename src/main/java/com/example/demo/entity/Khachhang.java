package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class Khachhang {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
	@Column(name="name")
   private String Name;
	@Column(name="email")
   private String email;
	@Column(name="sdt")
   private String sdt;
	@Column(name="diachi")
   private String diachi;
   @OneToMany(mappedBy = "khachhang")
	private Set<Hoadon>hoadons;
	
	public Long getId() {
		return id;
	}
	
	public Set<Hoadon> getHoadons() {
		return hoadons;
	}

	public void setHoadons(Set<Hoadon> hoadons) {
		this.hoadons = hoadons;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	
   
}
