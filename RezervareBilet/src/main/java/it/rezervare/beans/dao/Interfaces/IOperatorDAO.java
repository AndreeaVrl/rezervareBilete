package it.rezervare.beans.dao.Interfaces;

import beans.exception.ApplicationException;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

public interface IOperatorDAO {

	Operator getOperator(UserRequestBean userRequestBean) throws ApplicationException;

	void updateUser(Operator operator);

	Operator getOperatorByUsename(UserRequestBean userRequestBean) throws ApplicationException;

	Operator getOperatorById(Integer id);

}
