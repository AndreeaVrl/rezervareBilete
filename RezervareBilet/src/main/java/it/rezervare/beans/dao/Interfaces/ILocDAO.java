package it.rezervare.beans.dao.Interfaces;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Loc;

public interface ILocDAO {

	Loc getSeatByRowAndColumn(Integer row, String column) throws ApplicationException;

}
