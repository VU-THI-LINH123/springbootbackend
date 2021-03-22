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
@Table(name = "loaisp")
public class Loaisp {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
	@Column(name="name")
   private String name;
	@OneToMany(mappedBy = "loaisp", cascade = CascadeType.ALL)
   private Set<Sanpham>sanphams;
	public Long getId() {
		return id;
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
	public Set<Sanpham> getSanphams() {
		return sanphams;
	}
	public void setSanphams(Set<Sanpham> sanphams) {
		this.sanphams = sanphams;
	}  
}
