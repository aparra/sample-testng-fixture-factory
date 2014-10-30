package br.com.six2six.service;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.six2six.model.Customer;
import br.com.six2six.model.infra.Message;
import br.com.six2six.service.infra.MailService;

public class CustomerServiceTest {

	private CustomerServiceImpl customerService;

	private @Mock MailService mailService;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(mailService);
	}

	@Test(dataProviderClass = CustomerServiceData.class, dataProvider = "customerWithValidEmail")
	public void should_send_an_email_to_customer_with_valid_email(Customer customer) {
		doNothing().when(mailService).send(isNotNull(Message.class));
		customerService.sendMailReportingSale(customer);
		validateMockitoUsage();
	}
	
	@Test(dataProviderClass = CustomerServiceData.class, dataProvider = "customerWithInvalidEmail")
	public void should_throw_an_exception_when_try_send_an_email_to_customer_with_invalid_email(Customer customer) {
		catchException(customerService).sendMailReportingSale(customer);
		then(caughtException())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("customer.invalid.email");
		
		verify(mailService, never()).send(any(Message.class));
	}
}
