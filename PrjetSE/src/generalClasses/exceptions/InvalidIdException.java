package generalClasses.exceptions;

@SuppressWarnings("serial")
public class InvalidIdException extends LoginException {

	public InvalidIdException(String userMessage) {
		super("");
		this.customUserMessage=userMessage;
	}
}
