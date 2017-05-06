import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {InvoiceDTO} from '../dto/invoice-dto.component'
import {InvoiceItem} from '../dto/invoice-item-dto.component'


@Injectable()
export class InvoiceServiceService {

  constructor(private http: Http) { }

  generateInvoice(invoiceDTO: InvoiceDTO) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({ headers: headers });

    let body = JSON.stringify(invoiceDTO);

    return this.http.post(`/v1/generate`, body, options).map((res:Response) => res.json().id);

  }

  test() : Observable<string>{

    let headers = new Headers();
    headers.append('Accept', 'application/json');
    return this.http.get(`/v1/test`).map((res:Response) => res.json().message);
    
  }


  getTotalInvoiceAmount(invoiceItems: InvoiceItem[]) : Observable<string>{

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({ headers: headers });

    let body = JSON.stringify(invoiceItems);
    return this.http.post(`/v1/totalInvoiceAmount`, body, options).map((res:Response) => res.json().totalInvoiceAmount);
    
  }

  searchInvoice(invoiceId: string) : Observable<JSON>{

    
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let options = new RequestOptions({ headers: headers });

    
    let url : string;
    url = '/v1/invoice/' + invoiceId;
    return this.http.get(url , options).map((res:Response) => res.json());
    
  }


  toggleInvoiceStatus(invoiceId: string) : Observable<JSON>{

    
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let options = new RequestOptions({ headers: headers });

    
    let url : string;
    url = '/v1/invoice/' + invoiceId + '/togglestatus';
    return this.http.post(url , options).map((res:Response) => res.json());
    
  }
  
  private getHeaders(){
   
  }

}
