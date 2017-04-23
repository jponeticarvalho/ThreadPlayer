package control;

public class DAOException extends Exception {

	private static final long serialVersionUID = -3597238601069768979L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
