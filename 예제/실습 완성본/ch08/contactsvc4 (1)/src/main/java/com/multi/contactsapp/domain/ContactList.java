package com.multi.contactsapp.domain;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="연락처 리스트 객체")
@XmlRootElement(name="contactList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactList {
	@ApiModelProperty(value="페이지번호", example="2")
	private int pageNo;
	@ApiModelProperty(value="한 페이지사이즈", example="5")
	private int pageSize;
	@ApiModelProperty(value="전체 연락처 건수", example="1000")
	private int totalCount;
	@ApiModelProperty(value="연락처 목록 배열값", example="Array Type")
	@XmlElement(name="contact")
	private List<Contact> contacts;
	
	public ContactList(int pageNo, int pageSize, int totalCount, List<Contact> contacts) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.contacts = contacts;
	}
	public ContactList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
}
