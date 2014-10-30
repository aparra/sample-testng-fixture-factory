package br.com.six2six.model;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

import java.util.Date;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class Customer {

	public static final Pattern EMAIL_PATTERN = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);

	private Long id;
	private String name;
	private String nickname;
	private String email;
	private Date birthday;
	private Address address;
	
	public boolean hasValidEmail() {
		return email != null && EMAIL_PATTERN.matcher(email).find();
	}
}
