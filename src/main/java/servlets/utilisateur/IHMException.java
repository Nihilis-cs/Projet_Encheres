package servlets.utilisateur;

public class IHMException extends Exception {
	public IHMException() {
		super();
	}
	
	public IHMException(String message) {
		super(message);
	}
	
	public IHMException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		
		return "IHM - " + super.getMessage();
	}
}
