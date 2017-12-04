package it.rezervare.beans.DAO.Interfaces;

import it.rezervare.beans.model.Localitate;
import it.rezervare.beans.model.Tara;

public interface ITaraDAO {

	public void inserTara(Tara tara);

	Tara getTaraByDenumire(String denumire);

	void insertLocalitate(Localitate localitate);

}
