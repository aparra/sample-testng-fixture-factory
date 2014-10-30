package br.com.six2six.service;

import org.testng.annotations.DataProvider;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.model.Address;
import br.com.six2six.model.Customer;

public class CustomerServiceData {

	static {
		Fixture.of(Customer.class).addTemplate("complete", new Rule() {{
		    add("id", random(Long.class));
		    add("name", random("Anderson Parra", "Arthur Hirata"));
		    add("nickname", random("nerd", "geek"));
		    add("email", "${nickname}@gmail.com");
		    add("birthday", instant("18 years ago"));
		    add("address", one(Address.class, "valid"));
		}});

		Fixture.of(Customer.class).addTemplate("withoutEmail").inherits("complete", new Rule() {{
		    add("email", null);
		}});
		
		Fixture.of(Address.class).addTemplate("valid", new Rule(){{
		    add("street", random("Paulista Avenue", "Ibirapuera Avenue"));
		    add("city", "Sao Paulo");
		    add("state", "${city}");
		    add("country", "Brazil");
		    add("zipCode", random("06608000", "17720000"));
		}});
	}
	
    @DataProvider
    public static Object[][] customerWithValidEmail() {
        return new Object[][] {{ Fixture.from(Customer.class).gimme("complete") }};
    }
    
    @DataProvider
    public static Object[][] customerWithInvalidEmail() {
        return new Object[][] {{ Fixture.from(Customer.class).gimme("withoutEmail") }};
    }
}
