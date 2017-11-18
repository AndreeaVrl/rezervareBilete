package beans.exception;

import java.io.Serializable;


public class ApplicationException extends Exception implements Serializable{
	  /**
	 * 
	 */
	 private static final long serialVersionUID = -2313007835278202319L;
	
	private String message;
	
	
	public ApplicationException(String message) { super(message); }
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
