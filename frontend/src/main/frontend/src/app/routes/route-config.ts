import {Routes} from "@angular/router";
import { InvoiceComponent } from '../invoice/invoice.component';
import { GeneratedInvoiceComponent } from '../generated-invoice/generated-invoice.component';
export const routeConfig:Routes = [
    {
        path: 'invoice',
        component: InvoiceComponent
    },
    {
        path: 'generated-invoice',
        component: GeneratedInvoiceComponent
    },
    {
        path: '',
        redirectTo: '/invoice',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/invoice',
        pathMatch: 'full'
    }
];