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
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  providers: [InvoiceServiceService]
})

@Injectable()
export class InvoiceComponent implements OnInit {

  invoiceDTO : any;
  
  //name = "";
  //email = "";
  //invoiceDate = "";

  //rows = [];

  mediaType = ""; 
  quantity = 0;
  title = "";
  price = 0.00;
  message = '';
  code = '';

  totalInvoiceAmount = "0.00";
  generatedInvoiceID = "";

  constructor(private http: Http, private  invoiceService: InvoiceServiceService) { 

    this.invoiceDTO =  {
    name : '',
    email : '',
    note : '',
    status : 'Y',
    invoiceDate : new Date(),
    invoiceItems : []
  };
  }

  ngOnInit() {
  }



  addInvoiceItem() {

    this.invoiceDTO.invoiceItems.push({mediaType: this.mediaType, code: this.code, quantity : this.quantity, title : this.title, price : this.price });
    this.mediaType = '';
    this.quantity = 0;
    this.title = '';
    this.price = 0.00;
    this.code = '';
    this.invoiceService.getTotalInvoiceAmount(this.invoiceDTO.invoiceItems).subscribe(res => this.totalInvoiceAmount = res);
  }

  removeInvoiceItem(event, i) {
    this.invoiceDTO.invoiceItems.splice(i, 1);
    this.invoiceService.getTotalInvoiceAmount(this.invoiceDTO.invoiceItems).subscribe(res => this.totalInvoiceAmount = res);
  }

  generateInvoice() {

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let body = JSON.stringify(this.invoiceDTO);
    alert(body);
    //generateInvoice
    this.invoiceService.generateInvoice(this.invoiceDTO).subscribe(res => this.generatedInvoiceID =  res);
    //this.invoiceService.test().subscribe(res => this.setMessage(res));


  }

  isValid() {
    if(this.invoiceDTO.name != '' && this.invoiceDTO.email != '' && this.invoiceDTO.invoiceItems.length > 0) {
     return true;
    }

    return false;
  }
  setMessage(res: string) {
    this.message = res;
  }

}
