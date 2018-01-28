package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.Node;
import it.rezervare.beans.model.hibernateBeans.Aeroport;

public interface IAeroportDAO {

	List<Node> getDistinctAireports() throws ApplicationException ;

	Aeroport getAirportById(Integer id) throws ApplicationException;

}
