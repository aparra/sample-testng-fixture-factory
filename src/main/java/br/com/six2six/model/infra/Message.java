package br.com.six2six.model.infra;

import lombok.Getter;

public @Getter class Message {

	private String to;
	private String from;
	private String subject;
	private String message;
	
	private Message(Builder builder) {
		to = builder.to;
		from = builder.from;
		subject = builder.subject;
		message = builder.message;
	}
	
	public static class Builder {

		private String to;
		private String from;
		private String subject;
		private String message;

		public Builder to(String to) {
			this.to = to;
			return this;
		}

		public Builder from(String from) {
			this.from = from;
			return this;
		}

		public Builder subject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Message build() {
			return new Message(this);
		}
	}
}
