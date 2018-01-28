package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.CursaDijkstraAlgoritm;
import it.rezervare.beans.model.hibernateBeans.Cursa;

public interface ICursaDAO {

	List<CursaDijkstraAlgoritm> getAllFlights() throws ApplicationException;

	Cursa getRouteByAirport(String airportFrom, String airportTo) throws ApplicationException;

	List<Cursa> getAll() throws ApplicationException;

}
