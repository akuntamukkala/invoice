package com.enablingchoices.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enablingchoices.entity.Invoice;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Integer> {

	
}
