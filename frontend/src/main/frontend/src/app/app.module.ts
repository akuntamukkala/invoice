import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { GeneratedInvoiceComponent } from './generated-invoice/generated-invoice.component';
import {DatepickerModule} from 'ng2-bootstrap';
import { InvoiceServiceService } from './services/invoice-service.service';
import {RouterModule} from "@angular/router"
import {routeConfig} from "./routes/route-config";

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    GeneratedInvoiceComponent,
    GeneratedInvoiceComponent,
    InvoiceComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    DatepickerModule.forRoot(),
    RouterModule.forRoot(routeConfig)
  ],
  providers: [InvoiceServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
