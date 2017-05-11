import {InvoiceItem} from './invoice-item-dto.component';

export class InvoiceDTO  {
  id : number;
  name : string;
  email : string;
  note: string;
  status: string;
  invoiceDate : Date;
  invoiceItems : InvoiceItem[];
  totalAmount : number;

}
