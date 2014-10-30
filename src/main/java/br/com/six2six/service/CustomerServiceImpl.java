package br.com.six2six.service;

import br.com.six2six.model.Customer;
import br.com.six2six.model.infra.Message;
import br.com.six2six.service.infra.MailService;

public class CustomerServiceImpl implements CustomerService {

	private final MailService mailService;

	public CustomerServiceImpl(MailService mailService) {
		this.mailService = mailService;
	}

	@Override
	public void sendMailReportingSale(Customer customer) {
		if (!customer.hasValidEmail()) throw new IllegalArgumentException("customer.invalid.email");

		mailService.send(new Message.Builder()
				.to(customer.getEmail())
				.from("noreplay@mystore.com")
				.subject("New Sale")
				.message("Thank you to shop with us...")
				.build());
	}
}
