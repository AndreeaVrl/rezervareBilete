package it.rezervare.beans.dao.Interfaces;

import java.util.Date;
import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Avion;
import it.rezervare.beans.model.hibernateBeans.Zbor;

public interface IZborDAO {
	
	List<Zbor> getFlightList(Integer idCursa, Date date) throws ApplicationException;

	Zbor getFlightById(Integer id) throws ApplicationException;

	List<Zbor> getAllFlights();

	List<Avion> getAllAirplanes();

	void insertFlight(Zbor flight);

	Avion getAirplaneById(Integer id);

	void updateFlight(Zbor flight);
}
