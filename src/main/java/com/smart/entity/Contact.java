package com.smart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cid;
	private String name;
	private String secondName;
	private String email;
	private String work;
	private String phone;
	private String descripiton;
	
	@ManyToOne
	private User user;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescripiton() {
		return descripiton;
	}
	public void setDescripiton(String descripiton) {
		this.descripiton = descripiton;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Contact(Integer cid, String name, String secondName, String email, String work, String phone,
			String descripiton, User user) {
		super();
		this.cid = cid;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.work = work;
		this.phone = phone;
		this.descripiton = descripiton;
		this.user = user;
	}
	public Contact() {
		super();
	}
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", work="
				+ work + ", phone=" + phone + ", descripiton=" + descripiton + ", user=" + user + "]";
	}
	
	
	
}
