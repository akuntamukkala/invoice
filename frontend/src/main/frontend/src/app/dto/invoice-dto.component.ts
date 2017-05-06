import {InvoiceItem} from './invoice-item-dto.component';

export interface InvoiceDTO  {
  id : number;
  name : string;
  email : string;
  note: string;
  status: string;
  invoiceDate : Date;
  invoiceItems : InvoiceItem[];
  totalAmount : number;

}
