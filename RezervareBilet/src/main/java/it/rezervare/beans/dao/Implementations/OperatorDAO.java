package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IOperatorDAO;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

@Transactional
@Repository
public class OperatorDAO implements IOperatorDAO {
	
	final private SessionFactory sessionFactory;
	
	@Autowired
	public OperatorDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Operator getOperator(final UserRequestBean userRequestBean) throws ApplicationException {
		System.out.println("Enter ClientDAO.getOperator()");
		Operator operator = new Operator();
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Operator.class);
			criteria.add(Restrictions.eq("utilizator", StringUtils.trimAllWhitespace(userRequestBean.getUserName())));
			criteria.add(Restrictions.eq("parola", userRequestBean.getPassword()));
			operator = (Operator) criteria.uniqueResult();
		} catch(final Exception e) {
			throw new ApplicationException("Acest operator nu a putut fi identificat!");
		}
		System.out.println("Enter ClientDAO.getOperator() with operator = ["+operator+"]");
		return operator;
	}
	@Override
	public Operator getOperatorByUsename(final UserRequestBean userRequestBean) throws ApplicationException {
		System.out.println("Enter ClientDAO.getOperatorByUsename()");
		Operator operator = new Operator();
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Operator.class);
			criteria.add(Restrictions.eq("utilizator", StringUtils.trimAllWhitespace(userRequestBean.getUserName())));
			operator = (Operator) criteria.uniqueResult();
		} catch(final Exception e) {
			throw new ApplicationException("A aparut o problema, reveniti mai tarziu!");
		}
		System.out.println("Enter ClientDAO.getOperatorByUsename() with operator = ["+operator+"]");
		return operator;
	}
	@Override
	public void updateUser(final Operator operator){
		sessionFactory.getCurrentSession().update(operator);
	}
	
	@Override
	public Operator getOperatorById(final Integer id) {
		return (Operator) sessionFactory.getCurrentSession().createCriteria(Operator.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
}
