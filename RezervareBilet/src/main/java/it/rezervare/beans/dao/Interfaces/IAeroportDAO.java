package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.Node;

public interface IAeroportDAO {

	List<Node> getDistinctAireports() throws ApplicationException ;

}
