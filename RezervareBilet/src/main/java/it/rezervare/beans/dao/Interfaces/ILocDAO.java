package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Loc;
import it.rezervare.beans.model.hibernateBeans.TipAvion;

public interface ILocDAO {

	List<Loc> getSeatByRowAndColumn(Integer row, String column) throws ApplicationException;

	Loc getSeatByRowAndColumnAndSeatType(Integer row, String column, TipAvion tipAvion) throws ApplicationException;

}
