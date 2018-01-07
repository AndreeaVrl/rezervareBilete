package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import it.rezervare.beans.model.hibernateBeans.Cursa;

public interface ICursaDAO {

	List<Cursa> getAllFlights();

	Cursa getRouteByAirport(String airportFrom, String airportTo);

	
}
