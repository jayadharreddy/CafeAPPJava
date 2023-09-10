package com.inn.cafe.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name= "User.findbyEmailId", query="select u from User u where u.email=email")

@AllArgsConstructor
@Getter @Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="user")
public class User {
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID= 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name= "contactNumber")
	private String contactNumber;
	
	@Column(name= "email")
	private String email;

	@Column(name= "password")
	private String password;
	
	@Column(name= "status")
	private String status;
	
	@Column(name= "role")
	private String role;

}
