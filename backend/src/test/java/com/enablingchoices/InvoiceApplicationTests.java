package com.enablingchoices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBigDecimal() {
		double subtotal = 123.123;

		double discountPercent = 0.2;
		BigDecimal decimalSubtotal = new BigDecimal(Double.toString(subtotal));
		decimalSubtotal = decimalSubtotal.setScale(2, RoundingMode.HALF_UP);
		BigDecimal decimalDiscountPercent = new BigDecimal(
				Double.toString(discountPercent));

		BigDecimal discountAmount = decimalSubtotal
				.multiply(decimalDiscountPercent);
		discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);

		BigDecimal totalBeforeTax = decimalSubtotal.subtract(discountAmount);
		BigDecimal salesTaxPercent = new BigDecimal(".05");
		BigDecimal salesTax = salesTaxPercent.multiply(totalBeforeTax);
		salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
		BigDecimal total = totalBeforeTax.add(salesTax);

		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();

		String message = "Subtotal:         "
				+ currency.format(decimalSubtotal) + "\n"
				+ "Discount percent: " + percent.format(decimalDiscountPercent)
				+ "\n" + "Discount amount:  " + currency.format(discountAmount)
				+ "\n" + "Total before tax: " + currency.format(totalBeforeTax)
				+ "\n" + "Sales tax:        " + currency.format(salesTax)
				+ "\n" + "Invoice total:    " + currency.format(total) + "\n";

		System.out.println(message);

	}

}
