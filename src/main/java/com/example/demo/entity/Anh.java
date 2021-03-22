package com.example.demo.entity;
import javax.persistence.*;
@Entity
@Table(name = "Anh")
public class Anh {
   @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
   @Column(name = "url", length=10240)
  private String url;
  @ManyToOne
  @JoinColumn(name = "sanpham_id")
  private Sanpham sanpham;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Sanpham getSanpham() {
	return sanpham;
}
public void setSanpham(Sanpham sanpham) {
	this.sanpham = sanpham;
}
public Anh() {
	super();
}
  

}
