package it.rezervare.beans.dao.Interfaces;

import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.TipCard;

public interface IModalitatePlata {

	void save(ModalitatePlata plata);
	TipCard getTipCardById(Integer id);
}
