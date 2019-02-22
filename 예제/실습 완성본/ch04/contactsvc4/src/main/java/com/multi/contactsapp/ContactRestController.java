package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;
import com.multi.contactsapp.service.ContactService;

@RestController
@RequestMapping(value="contacts")
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ContactList getContactList() {
		return contactService.getContactList();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Result insertContact(@RequestBody Contact c) {
		return contactService.insertContact(c);
	}
	
	@RequestMapping(value="{no}", method = RequestMethod.PUT)
	public Result updateContact(@RequestBody Contact c, @PathVariable("no") int no) {
		c.setNo(no);
		System.out.println(c);
		return contactService.updateContact(c);
	}
}
