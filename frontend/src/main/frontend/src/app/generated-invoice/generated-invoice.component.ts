import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {DatepickerModule} from 'ng2-bootstrap';
import * as moment from 'moment';
import { InvoiceDTO } from '../dto/invoice-dto.component';
import { InvoiceItem } from '../dto/invoice-item-dto.component';
import { InvoiceServiceService } from '../services/invoice-service.service';


@Component({
  selector: 'app-generated-invoice',
  templateUrl: './generated-invoice.component.html',
   providers: [InvoiceServiceService]
})

@Injectable()
export class GeneratedInvoiceComponent implements OnInit {

  message: string = 'test invoice generated';

  invoiceId : string;

  invoiceDTO : any;
  showInvoiceItems : boolean;
  constructor(private http: Http, private  invoiceService: InvoiceServiceService) {
    this.invoiceDTO =  {
    name : '',
    email : '',
    note : '',
    status : 'Y',
    invoiceDate : new Date(),
    invoiceItems : []
  };
    this.showInvoiceItems = false;
  }
  ngOnInit() {

  }

 mapToInvoice(res) {
    
    this.invoiceDTO = <InvoiceDTO>res;
  
    
  }

  search(invoiceId) {
    this.showInvoiceItems = false;
    this.invoiceDTO =  {
    name : '',
    email : '',
    note : '',
    status : 'Y',
    invoiceDate : new Date(),
    invoiceItems : []
  };
    this.invoiceService.searchInvoice(this.invoiceId).subscribe(res => this.mapToInvoice(res));
  }
 
  showInvoiceItemDetails() {
    this.showInvoiceItems = true;
  }

  toggleInvoiceStatus() {
    this.invoiceService.toggleInvoiceStatus(this.invoiceId).subscribe(res => this.mapToInvoice(res));
  }
}
