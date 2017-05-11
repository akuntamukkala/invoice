import { Component, AfterViewChecked, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { InvoiceDTO } from '../dto/invoice-dto.component';
import { InvoiceItem } from '../dto/invoice-item-dto.component';
import {DatepickerModule} from 'ng2-bootstrap';
import * as moment from 'moment';
import { InvoiceServiceService } from '../services/invoice-service.service';
import { Http, Response, Headers, RequestOptions } from '@angular/http';


@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  providers: [InvoiceServiceService]
})
export class InvoiceComponent implements OnInit {

  invoiceDTO: InvoiceDTO;

  invoiceFormActive = true;
  invoiceItemFormActive = true;

  mediaType : string; 
  quantity : number;
  title : string;
  price : number;
  code : string;


  totalInvoiceAmount : string;
  generatedInvoiceID : string;

  invoiceItemForm: NgForm;

  constructor(private  invoiceService: InvoiceServiceService) { 
    this.initInvoiceFields();

  }

  initInvoiceFields() {
    this.invoiceDTO = new InvoiceDTO();
    this.invoiceDTO.name = '';
    this.invoiceDTO.email = '';
    this.invoiceDTO.note='';
    this.invoiceDTO.status='Y';
    this.invoiceDTO.invoiceDate = new Date();
    this.invoiceDTO.invoiceItems = [];

    this.initInvoiceItemFields();
    this.totalInvoiceAmount = '0';
    this.generatedInvoiceID = '';
  }

  initInvoiceItemFields() {

    this.mediaType = '';
    this.quantity = 0;
    this.title = '';
    this.price = 0.00;
    this.code = '';

  }
  ngOnInit() {
  }

  addInvoiceItem() {

    this.invoiceDTO.invoiceItems.push({mediaType: this.mediaType, code: this.code, quantity : this.quantity, title : this.title, price : this.price });
    this.initInvoiceItemFields();
    this.invoiceService.getTotalInvoiceAmount(this.invoiceDTO.invoiceItems).subscribe(res => this.totalInvoiceAmount = res);
    this.invoiceItemFormActive = false;
    setTimeout(() => this.invoiceItemFormActive = true, 0);
  }

  removeInvoiceItem(event, i) {
    this.invoiceDTO.invoiceItems.splice(i, 1);
    this.invoiceService.getTotalInvoiceAmount(this.invoiceDTO.invoiceItems).subscribe(res => this.totalInvoiceAmount = res);
  }

  generateInvoice() {

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let body = JSON.stringify(this.invoiceDTO);
    this.invoiceService.generateInvoice(this.invoiceDTO).subscribe(res => this.generatedInvoiceID =  res);
    
  }

  resetForm() {
    this.initInvoiceFields();
    this.invoiceFormActive = false;
    setTimeout(() => this.invoiceFormActive = true, 0);
  }

  isValid() {
    if(this.invoiceDTO.name != '' && this.invoiceDTO.email != '' && this.invoiceDTO.invoiceItems.length > 0) {
     return true;
    }

    return false;
  }

  


}
