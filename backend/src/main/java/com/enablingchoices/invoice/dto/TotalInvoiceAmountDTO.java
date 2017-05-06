package com.enablingchoices.invoice.dto;

public class TotalInvoiceAmountDTO {
	
	private String totalInvoiceAmount;
	private String currency;
	
	public String getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}
	public void setTotalInvoiceAmount(String totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
