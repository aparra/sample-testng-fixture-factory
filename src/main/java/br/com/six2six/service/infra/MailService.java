package br.com.six2six.service.infra;

import br.com.six2six.model.infra.Message;

public interface MailService {

	void send(Message email);
	
}
