package example.invoice;

import example.db.Customer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LateInvoiceNotifierTest {
    //Class to be tested
    private LateInvoiceNotifier lateInvoiceNotifier;

    //Dependencies (will be mocked)
    private IEmailSender emailSender;
    private IInvoiceStorage invoiceStorage;


    //Test data
    private Customer sampleCustomer;



    public void setup(){
        invoiceStorage = mock(IInvoiceStorage.class);
        emailSender = mock(IEmailSender.class);

        lateInvoiceNotifier = new LateInvoiceNotifier(emailSender,invoiceStorage);

        sampleCustomer = new Customer();
        sampleCustomer.setFirstName("Susan");
        sampleCustomer.setLastName("Ivanova");
    }

    @Test
    public void lateInvoice(){
        this.setup();
        when(invoiceStorage.hasOutstandingInvoice(sampleCustomer)).thenReturn(true);

        lateInvoiceNotifier.notifyIfLate(sampleCustomer);

        verify(emailSender).sendEmail(sampleCustomer);
    }

    @Test
    public void noLateInvoicePresent(){

        this.setup();
        when(invoiceStorage.hasOutstandingInvoice(sampleCustomer)).thenReturn(false);

        lateInvoiceNotifier.notifyIfLate(sampleCustomer);

        verify(emailSender, times(0)).sendEmail(sampleCustomer);
    }

}
