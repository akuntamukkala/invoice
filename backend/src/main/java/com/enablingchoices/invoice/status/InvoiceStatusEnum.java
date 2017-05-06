package com.enablingchoices.invoice.status;

public enum InvoiceStatusEnum {
	
	VALID("Y"), INVALID("N");
	
	private String code;
	private InvoiceStatusEnum(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
	}
	
	public static InvoiceStatusEnum getStatusEnumByCode(String code) {
		switch(code) {
		case "y" :
		case "Y" :
			return VALID;
		case "n" :
		case "N" : 
			return INVALID;
		}
		return null;
	}
}
