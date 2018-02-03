package it.rezervare.beans.dao.Interfaces;

import java.util.Date;
import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Zbor;

public interface IZborDAO {
	List<Zbor> getFlightList(Integer idCursa, Date date) throws ApplicationException;

	Zbor getFlightById(Integer id) throws ApplicationException;
}
