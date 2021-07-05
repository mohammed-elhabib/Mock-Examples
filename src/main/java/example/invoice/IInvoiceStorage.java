package example.invoice;

import example.db.Customer;

public interface IInvoiceStorage {
     boolean hasOutstandingInvoice(Customer customer) ;
}
