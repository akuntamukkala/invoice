package com.enablingchoices.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.enablingchoices.entity.InvoiceItem;

public interface InvoiceItemRepository extends
		PagingAndSortingRepository<InvoiceItem, Integer> {

}
