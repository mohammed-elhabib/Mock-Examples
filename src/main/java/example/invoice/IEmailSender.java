package example.invoice;

import example.db.Customer;

public interface IEmailSender {
    void sendEmail(Customer customer);
}
