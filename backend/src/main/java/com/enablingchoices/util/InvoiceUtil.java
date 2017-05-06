package com.enablingchoices.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.enablingchoices.invoice.dto.InvoiceItemDTO;

@Component
public class InvoiceUtil {
	
	@Value("${sales.tax.percent:0.082}")
	private BigDecimal salesTaxPercent;
	
	public BigDecimal getTotalInvoiceAmount(List<InvoiceItemDTO> invoiceItems) {
		
		BigDecimal totalPreTax = new BigDecimal("0.00");
		
		for(int i = 0; i < invoiceItems.size(); i++) {
			
			totalPreTax = totalPreTax.add(invoiceItems.get(i).getPrice().multiply(new BigDecimal(invoiceItems.get(i).getQuantity())));
		
		}
		
		totalPreTax = totalPreTax.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal salesTax = totalPreTax.multiply(salesTaxPercent);
		
		salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal totalAmount = new BigDecimal("0.00");
		
		totalAmount = totalAmount.add(totalPreTax).add(salesTax);
		
		totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);
		
		return totalAmount;
	}
}
