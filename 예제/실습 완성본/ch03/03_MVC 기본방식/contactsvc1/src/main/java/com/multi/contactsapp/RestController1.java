package com.multi.contactsapp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.service.ContactService;

@Controller
@RequestMapping(value="/contacts")
public class RestController1 {
	
	@Autowired
	private ContactService contactService;
	
	@Resource(name="xmlView")
	private View xmlView;
	
	@Resource(name="jsonView")
	private View jsonView;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getContactList(
			@RequestParam(value="pageno", required=false, defaultValue="0") int pageNo, 
			@RequestParam(value="pagesize", required=false, defaultValue="3")int pageSize) {
		ContactList contactList = null;
		if (pageNo == 0) {
			contactList = contactService.getContactList();
		} else {
			contactList = contactService.getContactList(pageNo, pageSize);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactList);
		mav.setView(jsonView);
		return mav;
	}

	@RequestMapping(value="{no}", method=RequestMethod.GET)
	public ModelAndView getContactOne(@PathVariable("no") int no) {
		Contact c = new Contact();
		c.setNo(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.getContactOne(c));
		mav.setView(jsonView);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertContact(@RequestBody Contact c) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.insertContact(c));
		mav.setView(jsonView);
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.PUT)
	public ModelAndView updateContact(@RequestBody Contact c, @PathVariable("no") int no) {
		c.setNo(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.updateContact(c));
		mav.setView(jsonView);
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.DELETE)
	public ModelAndView deleteContact(@PathVariable("no") int no) {
		Contact c = new Contact();
		c.setNo(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.deleteContact(c));
		mav.setView(jsonView);
		return mav;
	}
	
}
