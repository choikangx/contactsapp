package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multi.contactsapp.service.ContactService;

@Controller
@RequestMapping(value="/contacts")
public class RestController1 {
	
	@Autowired
	private ContactService contactService;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getContactList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", contactService.getContactList());
		mav.setViewName("contact");
		return mav;
	}

}
