package example.invoice;

import example.db.Customer;

public class LateInvoiceNotifier {
    private final IEmailSender emailSender;
    private final IInvoiceStorage invoiceStorage;

    public LateInvoiceNotifier(final IEmailSender emailSender, final IInvoiceStorage invoiceStorage){
        this.emailSender = emailSender;
        this.invoiceStorage = invoiceStorage;
    }

    public void notifyIfLate(Customer customer)
    {
        if(invoiceStorage.hasOutstandingInvoice(customer)){
            emailSender.sendEmail(customer);
        }
    }
}
