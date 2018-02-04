package it.rezervare.beans.model.requestBeans;

import java.util.List;

public class RezervareRequestBean {
	
	private List<String> locuri;

	public List<String> getLocuri() {
		return locuri;
	}

	public void setLocuri(final List<String> locuri) {
		this.locuri = locuri;
	}

	@Override
	public String toString() {
		return "RezervareRequestBean [locuri=" + locuri + "]";
	}

}
