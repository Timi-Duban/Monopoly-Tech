package generalClasses.exceptions;

/**
 * 
 * Exception thrown when the client cannot send a message to the server during the login step.
 *
 */
@SuppressWarnings("serial")
public class SendMessageException extends LoginException {
	
	public SendMessageException(String errorMessage) {
		super(errorMessage);
		this.customUserMessage="The server is busy, please try again later";
	}

}
