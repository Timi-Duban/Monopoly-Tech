package generalClasses.exceptions;
/**
 * 
 * Exception thrown when the client tries to sign in with an already existing email.
 *
 */
@SuppressWarnings("serial")
public class AlreadyUsedMailException extends LoginException {
	
	public AlreadyUsedMailException() {
		super("");
		this.customUserMessage="The email is already used.";
	}
}

