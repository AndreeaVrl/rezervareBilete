package it.rezervare.beans.dao.Interfaces;

import it.rezervare.beans.model.hibernateBeans.Localitate;
import it.rezervare.beans.model.hibernateBeans.Tara;

public interface ITaraDAO {

	public void inserTara(Tara tara);

	Tara getTaraByDenumire(String denumire);

	void insertLocalitate(Localitate localitate);

}
