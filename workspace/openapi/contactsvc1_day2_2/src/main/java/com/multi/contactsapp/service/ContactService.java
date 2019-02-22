package com.multi.contactsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.dao.ContactDAO;
import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;

@Service
public class ContactService {
	@Autowired
	private ContactDAO contactDAO;
	
	public ContactList getContactList() {
		List<Contact> contacts = contactDAO.getContactList();
		ContactList contactList = new ContactList(0, 0, contacts.size(), contacts);
		return contactList;
	}
	
	public ContactList getContactList(int pageNo, int pageSize) {
		List<Contact> contacts = contactDAO.getContactList(pageNo, pageSize);
		int totalCount = contactDAO.getContactCount();		
		ContactList contactList = new ContactList(pageNo, pageSize, totalCount, contacts);
		return contactList;	
	}
	
	public Contact getContactOne(Contact c) {
		return contactDAO.getContactOne(c);
	}
	
	public Result insertContact(Contact c) {
		Result result = new Result("ok", "", "");
		try {
			int no = contactDAO.insertContact(c);
			result.setMessage("success" + no);
			result.setKey(""+no);
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getMessage());
			result.setKey("");
		}
		return result;
	}
	
	public Result updateContact(Contact c) {
		Result result = new Result("ok", "", "");
		try {
			int count = contactDAO.updateContact(c);
			result.setMessage(count + "success");
			result.setKey(c.getNo()+"");
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getMessage());
			result.setKey("");
		}
		return result;
	}
	
	public Result deleteContact(Contact c) {
		Result result = new Result("ok", "", "");
		try {
			int count = contactDAO.deleteContact(c);
			result.setMessage(count + "���� ����ó ���� ����");
			result.setKey(c.getNo()+"");
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getMessage());
			result.setKey("");
		}
		return result;
	}
}

