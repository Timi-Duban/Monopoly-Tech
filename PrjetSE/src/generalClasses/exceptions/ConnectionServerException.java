package generalClasses.exceptions;

/**
 * 
 * Exception thrown when the client cannot connect to the server
 *
 */
@SuppressWarnings("serial")
public class ConnectionServerException extends LoginException {
	
	public ConnectionServerException(String errorMessage) {
		super(errorMessage);
		this.customUserMessage="The server is unavailable, please try again later.";
	}
}
