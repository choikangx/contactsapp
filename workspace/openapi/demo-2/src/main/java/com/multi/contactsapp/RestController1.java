package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.service.ContactService;

@RestController // @RestController  vs @Controller 차이는 ?  @ResponseBody의 default 처리화. 
@RequestMapping(value="/contacts")
public class RestController1 {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ContactList getContactList() {
		return contactService.getContactList();
	}
	/*@RestController가 아닌 @Controller를 사용할 경우 아래와 같이 @ResponseBody를 사용해 줘야 한다. 
	@RequestMapping(method=RequestMethod.GET)
	public  @ResponseBody ContactList getContactList() {
		return contactService.getContactList();
	}
	*/
	
	//ContentNagociationViewResolver가 대신해줌으로 아래 @Resouce 필요 없네. 
//	@Resource(name="xmlView")
//	private View xmlView;
//	
//	@Resource(name="jsonView")
//	private View jsonView;
//
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView getContactList(
//			@RequestParam(value="pageno", required=false, defaultValue="0") int pageNo, 
//			@RequestParam(value="pagesize", required=false, defaultValue="3")int pageSize) {
//		ContactList contactList = null;
//		if (pageNo == 0) {
//			contactList = contactService.getContactList();
//		} else {
//			contactList = contactService.getContactList(pageNo, pageSize);
//		}
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("data", contactList);
//		mav.setViewName("contact");
//		return mav;
//	}
//
//	@RequestMapping(value="{no}", method=RequestMethod.GET)
//	public ModelAndView getContactOne(@PathVariable("no") int no) {
//		Contact c = new Contact();
//		c.setNo(no);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("data", contactService.getContactOne(c));
//		mav.setView(jsonView);
//		return mav;
//	}
//	
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView insertContact(@RequestBody Contact c) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("data", contactService.insertContact(c));
//		mav.setView(jsonView);
//		return mav;
//	}
//	
//	@RequestMapping(value="{no}", method=RequestMethod.PUT)
//	public ModelAndView updateContact(@RequestBody Contact c, @PathVariable("no") int no) {
//		c.setNo(no);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("data", contactService.updateContact(c));
//		mav.setView(jsonView);
//		return mav;
//	}
//	
//	@RequestMapping(value="{no}", method=RequestMethod.DELETE)
//	public ModelAndView deleteContact(@PathVariable("no") int no) {
//		Contact c = new Contact();
//		c.setNo(no);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("data", contactService.deleteContact(c));
//		mav.setView(jsonView);
//		return mav;
//	}
//	
}