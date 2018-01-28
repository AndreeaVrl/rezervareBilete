package beans.exception;

import java.io.Serializable;


public class ApplicationException extends Exception implements Serializable{
	  /**
	 * 
	 */
	 private static final long serialVersionUID = -2313007835278202319L;
	
	private String message;
	
	public ApplicationException() {
		
	}
	public ApplicationException(final String message) { 
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
}
