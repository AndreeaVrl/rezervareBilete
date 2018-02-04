package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.IModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.TipCard;
@Repository
@Transactional
public class ModalitatePlataDAO implements IModalitatePlata {
	final private SessionFactory sessionFactory;
	
	@Autowired
	public ModalitatePlataDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(final ModalitatePlata plata) {
		System.out.println("ENTER ModalitatePlataDAO.save() ");
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(plata);
		session.getTransaction().commit();
		session.close();
		System.out.println("EXIT ModalitatePlataDAO.save() ");
	}
	@Override
	public TipCard getTipCardById(final Integer id) {
		final Session session = sessionFactory.openSession();
		final Criteria criteria = session.createCriteria(TipCard.class);
		criteria.add(Restrictions.eq("id", id));
		return (TipCard) criteria.uniqueResult();
	}
}
