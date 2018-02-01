package it.rezervare.beans.dao.Interfaces;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Bilet;

public interface IBileteDAO {

	Bilet getBiletByIdLocAndIdZbor(Integer idLoc, Integer idZbor) throws ApplicationException;

}
