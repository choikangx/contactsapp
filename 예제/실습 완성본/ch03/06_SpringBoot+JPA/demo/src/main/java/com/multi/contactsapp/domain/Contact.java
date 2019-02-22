package com.multi.contactsapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="CONTACT_SEQ_GENERATOR", 
	sequenceName="CONTACT_SEQ", initialValue=1, allocationSize=1)
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType. SEQUENCE, generator="CONTACT_SEQ_GENERATOR")
	private int no;
	private String name;
	private String tel;
	private String address;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(int no, String name, String tel, String address) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.address = address;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
