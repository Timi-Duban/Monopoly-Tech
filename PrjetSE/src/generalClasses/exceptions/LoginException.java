package generalClasses.exceptions;

/**
 * 
 * Exception thrown whenever something unusual happens during the login step
 *
 */
@SuppressWarnings("serial")
public class LoginException extends Exception {
	public String customUserMessage;
	
	public LoginException(String errorMessage) {
		super(errorMessage);
	}
}
