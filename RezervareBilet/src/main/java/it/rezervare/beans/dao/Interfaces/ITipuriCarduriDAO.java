package it.rezervare.beans.dao.Interfaces;

import java.util.List;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.TipCard;

public interface ITipuriCarduriDAO {

	List<TipCard> getCardTypes() throws ApplicationException;

}
