package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.dao.Interfaces.ITipuriCarduriDAO;
import it.rezervare.beans.model.hibernateBeans.TipCard;

@Repository
@Transactional
public class TipuriCarduriDAO implements ITipuriCarduriDAO {

final private SessionFactory sessionFactory;
	
	@Autowired
	public TipuriCarduriDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipCard> getCardTypes() throws ApplicationException {
		System.out.println("ENTER TipuriCarduriDAO - getCardTypes()");
		List<TipCard> tipuriCarduri = new ArrayList<>();
		try {
			final Session session = sessionFactory.getCurrentSession();
			final Criteria criteria = session.createCriteria(TipCard.class);
			tipuriCarduri = criteria.list();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\n EXIT TipuriCarduriDAO - getCardTypes()\n");
		return tipuriCarduri;
	}
}
