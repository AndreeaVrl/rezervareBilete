package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import it.rezervare.beans.model.hibernateBeans.Tara;

public interface ITaraDAO {

	public void inserTara(Tara tara);

	Tara getTaraByDenumire(String denumire);
	
	List<Tara> getAllCountrys();

	void updateCountry(Tara tara);

	Tara getCountryById(Integer id);

}
