package it.rezervare.beans.dao.Interfaces;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

public interface IClientDAO {

	Client getClientAccount(UserRequestBean userRequestBean) throws ApplicationException;

	void updateUser(Client client);

	Client getClientByEmail(UserRequestBean userRequestBean) throws ApplicationException;

	Client getClientByEmail(String email) throws ApplicationException;

	void insertClient(Client client) throws ApplicationException;

}
