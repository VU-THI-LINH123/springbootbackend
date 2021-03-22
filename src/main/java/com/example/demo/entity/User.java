package com.example.demo.entity;
 
import java.util.*;
 
import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String email;
     
    private String password;
     
    @Column(name = "full_name")
    private String fullName;
         
  
     
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

	public User(Integer id, String email, String password, String fullName, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		
		this.roles = roles;
	}
 
    // constructors, getter and setters are not shown for brevity
}