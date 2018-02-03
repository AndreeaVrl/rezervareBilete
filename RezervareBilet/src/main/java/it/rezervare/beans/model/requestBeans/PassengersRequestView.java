package it.rezervare.beans.model.requestBeans;

import java.util.List;

import it.rezervare.beans.model.hibernateBeans.Client;

public class PassengersRequestView {

	private List<Client> listaClienti;

	/*public PassengersRequestView(final List<Client> listaClienti) {
		this.listaClienti = listaClienti;
	}
	*/
	public List<Client> getListaClienti() {
		return listaClienti;
	}

	public void setListaClienti(final List<Client> listaClienti) {
		this.listaClienti = listaClienti;
	}

	@Override
	public String toString() {
		return "PassengersRequestView [listaClienti=" + listaClienti + "]";
	}
	
}
