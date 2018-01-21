package it.rezervare.beans.dao.Implementations;


import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

@Repository
@Transactional
public class ClientDAO implements IClientDAO {
	final private SessionFactory sessionFactory;
	
	@Autowired
	public ClientDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Client getClientAccount(final UserRequestBean userRequestBean) throws ApplicationException {
		System.out.println("Enter ClientDAO.getClientAccount()");
		Client client = new Client();
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
			criteria.add(Restrictions.eq("email", StringUtils.trimAllWhitespace(userRequestBean.getUserName())));
			criteria.add(Restrictions.eq("parola", userRequestBean.getPassword()));
			client = (Client) criteria.uniqueResult();
		} catch(final Exception e) {
			throw new ApplicationException("Clientul nu a putut fi identificat!");
		}
		System.out.println("Exit ClientDAO.getClientAccount() with client = ["+client+"]");
		return client;
	}
	@Override
	public Client getClientByEmail(final UserRequestBean userRequestBean) throws ApplicationException {
		System.out.println("Enter ClientDAO.getClientByEmail()");
		Client client = new Client();
		try {
			final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
			criteria.add(Restrictions.eq("email", StringUtils.trimAllWhitespace(userRequestBean.getUserName())));
			client = (Client) criteria.uniqueResult();
		} catch(final Exception e) {
			throw new ApplicationException("A aparut o problema, reveniti mai tarziu!");
		}
		System.out.println("Exit ClientDAO.getClientByEmail() with client = ["+client+"]");
		return client;
	}
	
	@Override
	public void updateUser(final Client client){
		sessionFactory.getCurrentSession().update(client);
	}
}
