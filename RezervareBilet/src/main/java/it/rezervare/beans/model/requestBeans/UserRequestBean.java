package it.rezervare.beans.model.requestBeans;

public class UserRequestBean {

	private String userName;
	private String password;
	private String repeatPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(final String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public String toString() {
		return "UserRequestBean [userName=" + userName + ", password=" + password + ", repeatPassword=" + repeatPassword
				+ "]";
	}

}
