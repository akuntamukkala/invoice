package com.enablingchoices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enablingchoices.entity.Invoice;
import com.enablingchoices.entity.InvoiceItem;
import com.enablingchoices.invoice.dto.InvoiceDTO;
import com.enablingchoices.invoice.dto.InvoiceItemDTO;
import com.enablingchoices.invoice.status.InvoiceStatusEnum;
import com.enablingchoices.repo.InvoiceItemRepository;
import com.enablingchoices.repo.InvoiceRepository;
import com.enablingchoices.util.InvoiceUtil;

@Service
@Transactional
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceItemRepository invoiceItemRepo;

	@Autowired
	private InvoiceUtil invoiceUtil;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

		Invoice invoice = new Invoice();
		invoice.setDate(invoiceDTO.getInvoiceDate());
		invoice.setEmail(invoiceDTO.getEmail());
		invoice.setName(invoiceDTO.getName());
		invoice.setNote(invoiceDTO.getNote());
		// potential case of invalid status sent from UI not being handled
		invoice.setStatus(invoiceDTO.getStatus());

		for (int i = 0; i < invoiceDTO.getInvoiceItems().size(); i++) {

			InvoiceItem item = new InvoiceItem();
			item.setInvoice(invoice);
			item.setMediaType(invoiceDTO.getInvoiceItems().get(i)
					.getMediaType());
			item.setPrice(invoiceDTO.getInvoiceItems().get(i).getPrice());
			item.setQuantity(invoiceDTO.getInvoiceItems().get(i).getQuantity());
			item.setTitle(invoiceDTO.getInvoiceItems().get(i).getTitle());
			item.setCode(invoiceDTO.getInvoiceItems().get(i).getCode());
			invoice.getInvoiceItems().add(item);

		}

		invoice.setTotal(invoiceUtil.getTotalInvoiceAmount(invoiceDTO
				.getInvoiceItems()));

		invoiceRepo.save(invoice);

		invoiceDTO.setId(invoice.getId()); // setting the id
		return invoiceDTO;

	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public InvoiceDTO getInvoiceById(String invoiceIdStr) {
		Integer invoiceId = -1;
		try {
			invoiceId = Integer.parseInt(invoiceIdStr.trim());
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid Invoice ID. Not an integer ");
			return new InvoiceDTO(); // sending blank invoice when not found
		}

		InvoiceDTO invoiceDTO = new InvoiceDTO();
		Invoice invoice = invoiceRepo.findOne(invoiceId);
		if (invoice != null) {
			invoiceDTO.setName(invoice.getName());
			invoiceDTO.setEmail(invoice.getEmail());
			invoiceDTO.setNote(invoice.getNote());
			invoiceDTO.setInvoiceDate(invoice.getDate());
			invoiceDTO.setInvoiceNumber(invoice.getId());
			invoiceDTO.setTotalAmount(invoice.getTotal());
			invoiceDTO.setId(invoice.getId());
			invoiceDTO.setStatus(invoice.getStatus());
			for (int i = 0; i < invoice.getInvoiceItems().size(); i++) {
				InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
				invoiceItemDTO.setMediaType(invoice.getInvoiceItems().get(i)
						.getMediaType());
				invoiceItemDTO.setPrice(invoice.getInvoiceItems().get(i)
						.getPrice());
				invoiceItemDTO.setQuantity(invoice.getInvoiceItems().get(i)
						.getQuantity());
				invoiceItemDTO.setTitle(invoice.getInvoiceItems().get(i)
						.getTitle());
				invoiceItemDTO.setCode(invoice.getInvoiceItems().get(i)
						.getCode());
				invoiceDTO.getInvoiceItems().add(invoiceItemDTO);
			}
		}

		return invoiceDTO;
	}

	public InvoiceDTO toggleInvoiceStatus(String invoiceIdStr) {
		Integer invoiceId = -1;
		try {
			invoiceId = Integer.parseInt(invoiceIdStr.trim());
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid Invoice ID. Not an integer ");
			return new InvoiceDTO(); // sending blank invoice when not found
		}

		Invoice invoice = invoiceRepo.findOne(invoiceId);
		if (invoice != null) {
			invoice.setStatus(InvoiceStatusEnum.VALID.getCode()
					.equalsIgnoreCase(invoice.getStatus().trim()) ? InvoiceStatusEnum.INVALID
					.getCode() : InvoiceStatusEnum.VALID.getCode());
			invoiceRepo.save(invoice);
		}

		return getInvoiceById(invoiceIdStr);
	}

}
