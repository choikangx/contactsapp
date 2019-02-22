package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;
import com.multi.contactsapp.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="contacts")
@Api(description="����ó RESTful ����, ContactList Service Controller")
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@ApiOperation(value="����ó ��� ��ȸ", notes="����ó ��� ��ȸ�� ���ҽ��Դϴ�. pageno�� 0�̸� ��ü���ҽ��� ��ȸ�ϸ� pageno�� 0�� �ƴ϶�� Ư�� �������� ����ó ����� ��ȸ�մϴ�")
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageno", value="������ ��ȣ", dataType = "string", paramType = "query", defaultValue="0"), 
			@ApiImplicitParam(name="pagesize", value="������ ������", dataType = "string", paramType = "query", defaultValue="4") 
	})
	@RequestMapping(method = RequestMethod.GET)
	public ContactList getContactList(
			@RequestParam(value="pageno", required=false, defaultValue="0") int pageNo, 
			@RequestParam(value="pagesize", required=false, defaultValue="4")int pageSize) {
		if (pageNo==0) {
			return contactService.getContactList();
		} else {
			return contactService.getContactList(pageNo, pageSize);
		}
	}
	
	@ApiOperation(value="����ó �Ѱ� ��ȸ", notes="����ó�� �Ϸù�ȣ�� �̿��� �Ѱ��� ����ó�� ��ȸ�մϴ�.")
	@ApiImplicitParam(name="no", value="����ó �Ϸù�ȣ", dataType="int", paramType="path") 
	@RequestMapping(value="{no}", method = RequestMethod.GET)
	public Contact getContactOne(@ApiParam(value="����ó�� �Ϸù�ȣ") @PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		return contactService.getContactOne(contact);
	}

	@ApiOperation(value="����ó �߰�", notes="���ο� ����ó�� �߰��մϴ�. �Ϸù�ȣ�� ������ �ʿ䰡 �����ϴ�.")
	@RequestMapping(method = RequestMethod.POST)
	public Result insertContact(@ApiParam(value="����ó �Ѱ�(�̸�,��ȭ��ȣ,�ּ�)") @RequestBody Contact contact) {
		return contactService.insertContact(contact);
	}
	
	@ApiOperation(value="����ó ����", notes="���� ����ó�� �����մϴ�. �Ϸù�ȣ�� URI ��η� �����մϴ�.")
	@RequestMapping(value="{no}", method = RequestMethod.PUT)
	public Result updateContact(
			@ApiParam(value="����ó�� �Ϸù�ȣ") @PathVariable("no") int no, 
			@ApiParam(value="������ ����ó ����(�̸�,��ȭ��ȣ,�ּ�)") @RequestBody Contact contact) {
		contact.setNo(no);
		return contactService.updateContact(contact);
	}
	
	@ApiOperation(value="����ó ����", notes="���� ����ó�� �����մϴ�. �Ϸù�ȣ�� URI ��η� �����մϴ�.")
	@RequestMapping(value="{no}", method = RequestMethod.DELETE)
	public Result deleteContact(@ApiParam(value="����ó�� �Ϸù�ȣ")@PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		return contactService.deleteContact(contact);
	}
	

}
