package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;

public interface IBiletDAO {

	void save(Bilet bilet);
	List<Bilet> getReservations(Client client);
	Bilet getBiletById(Integer id);
	void updateBilet(Bilet bilet);
}
