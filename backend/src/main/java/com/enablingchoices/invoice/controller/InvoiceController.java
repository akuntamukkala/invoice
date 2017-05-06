package com.enablingchoices.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enablingchoices.invoice.dto.InvoiceDTO;
import com.enablingchoices.invoice.dto.InvoiceItemDTO;
import com.enablingchoices.invoice.dto.TotalInvoiceAmountDTO;
import com.enablingchoices.service.InvoiceService;
import com.enablingchoices.util.InvoiceUtil;

@RestController
@RequestMapping(value = "/v1")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceUtil invoiceUtil;

	@RequestMapping(value = "/totalInvoiceAmount", method = RequestMethod.POST)
	public ResponseEntity<TotalInvoiceAmountDTO> update(
			@RequestBody List<InvoiceItemDTO> invoiceItems) {
		//NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		TotalInvoiceAmountDTO totalInvoiceAmountDTO = new TotalInvoiceAmountDTO();
		totalInvoiceAmountDTO.setTotalInvoiceAmount(invoiceUtil
				.getTotalInvoiceAmount(invoiceItems).toString());
		return new ResponseEntity<TotalInvoiceAmountDTO>(totalInvoiceAmountDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public ResponseEntity<InvoiceDTO> update(@RequestBody InvoiceDTO invoiceDTO) {

		invoiceDTO = invoiceService.createInvoice(invoiceDTO);

		return new ResponseEntity<InvoiceDTO>(invoiceDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invoice/{invoiceIdStr}/togglestatus", method = RequestMethod.POST)
	public ResponseEntity<InvoiceDTO> toggleInvoiceStatus(@PathVariable  String invoiceIdStr) {

		InvoiceDTO invoiceDTO = invoiceService.toggleInvoiceStatus(invoiceIdStr);

		return new ResponseEntity<InvoiceDTO>(invoiceDTO, HttpStatus.OK);
	}

	
	

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<String> test() {

		return new ResponseEntity<String>("{ \"message\" : \"Test Success\" }",
				HttpStatus.OK);
	}

	@RequestMapping(value="/invoice/{invoiceIdStr}", method = RequestMethod.GET)
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable  String invoiceIdStr) {
		InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(invoiceIdStr);

		return new ResponseEntity<InvoiceDTO>(invoiceDTO, HttpStatus.OK);
		
	}
}
