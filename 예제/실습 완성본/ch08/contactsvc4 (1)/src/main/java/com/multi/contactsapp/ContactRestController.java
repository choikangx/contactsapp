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
@Api(description="연락처 RESTful 서비스, ContactList Service Controller")
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@ApiOperation(value="연락처 목록 조회", notes="연락처 목록 조회용 리소스입니다. pageno가 0이면 전체리소스를 조회하며 pageno가 0이 아니라면 특정 페이지의 연락처 목록을 조회합니다")
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageno", value="페이지 번호", dataType = "string", paramType = "query", defaultValue="0"), 
			@ApiImplicitParam(name="pagesize", value="페이지 사이즈", dataType = "string", paramType = "query", defaultValue="4") 
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
	
	@ApiOperation(value="연락처 한건 조회", notes="연락처의 일련번호를 이용해 한건의 연락처만 조회합니다.")
	@ApiImplicitParam(name="no", value="연락처 일련번호", dataType="int", paramType="path") 
	@RequestMapping(value="{no}", method = RequestMethod.GET)
	public Contact getContactOne(@ApiParam(value="연락처의 일련번호") @PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		return contactService.getContactOne(contact);
	}

	@ApiOperation(value="연락처 추가", notes="새로운 연락처를 추가합니다. 일련번호는 전달할 필요가 없습니다.")
	@RequestMapping(method = RequestMethod.POST)
	public Result insertContact(@ApiParam(value="연락처 한건(이름,전화번호,주소)") @RequestBody Contact contact) {
		return contactService.insertContact(contact);
	}
	
	@ApiOperation(value="연락처 수정", notes="기존 연락처를 수정합니다. 일련번호는 URI 경로로 전달합니다.")
	@RequestMapping(value="{no}", method = RequestMethod.PUT)
	public Result updateContact(
			@ApiParam(value="연락처의 일련번호") @PathVariable("no") int no, 
			@ApiParam(value="수정할 연락처 정보(이름,전화번호,주소)") @RequestBody Contact contact) {
		contact.setNo(no);
		return contactService.updateContact(contact);
	}
	
	@ApiOperation(value="연락처 삭제", notes="기존 연락처를 삭제합니다. 일련번호는 URI 경로로 전달합니다.")
	@RequestMapping(value="{no}", method = RequestMethod.DELETE)
	public Result deleteContact(@ApiParam(value="연락처의 일련번호")@PathVariable("no") int no) {
		Contact contact = new Contact();
		contact.setNo(no);
		return contactService.deleteContact(contact);
	}
	

}
