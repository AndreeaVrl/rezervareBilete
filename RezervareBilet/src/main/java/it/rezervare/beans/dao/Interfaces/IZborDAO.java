package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import it.rezervare.beans.model.hibernateBeans.Zbor;

public interface IZborDAO {

	List<Zbor> getFlightList(Long idCursa);

}
