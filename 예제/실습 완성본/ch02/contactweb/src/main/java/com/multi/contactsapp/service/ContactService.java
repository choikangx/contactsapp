package com.multi.contactsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.dao.ContactDAO;
import com.multi.contactsapp.domain.Contact;

@Service
public class ContactService {
	@Autowired
	private ContactDAO contactDAO;
	
	public List<Contact> getContactList() {
		List<Contact> contacts = contactDAO.getContactList();
		return contacts;
	}
	
	public List<Contact> getContactList(int pageNo, int pageSize) {
		List<Contact> contacts = contactDAO.getContactList(pageNo, pageSize);
		return contacts;
	}
	
	public Contact getContactOne(Contact c) {
		return contactDAO.getContactOne(c);
	}
	
	public int insertContact(Contact c) {
		return contactDAO.insertContact(c);
	}
	
	public int updateContact(Contact c) {
		return contactDAO.updateContact(c);
	}
	
	public int deleteContact(Contact c) {
		return contactDAO.deleteContact(c);
	}
}

