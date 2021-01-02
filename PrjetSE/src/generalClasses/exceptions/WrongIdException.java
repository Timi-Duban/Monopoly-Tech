package generalClasses.exceptions;

@SuppressWarnings("serial")
public class WrongIdException extends LoginException {
	
	public WrongIdException() {
		super("");
		this.customUserMessage="Wrong ID.";
	}

}
