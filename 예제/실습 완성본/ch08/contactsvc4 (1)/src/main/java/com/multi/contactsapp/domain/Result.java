package com.multi.contactsapp.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="연락처 추가,수정,삭제시 처리 결과 객체")
@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
	@ApiModelProperty(value="처리 결과 상태", example="ok/fail")
	private String status;
	@ApiModelProperty(value="처리 결과 메시지")
	private String message;
	@ApiModelProperty(value="연락처의 고유키", example="33")
	private String key;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(String status, String message, String key) {
		super();
		this.status = status;
		this.message = message;
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
