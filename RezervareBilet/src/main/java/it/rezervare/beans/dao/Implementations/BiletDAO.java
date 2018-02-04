package it.rezervare.beans.dao.Implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.IBiletDAO;
import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;
@Repository
@Transactional
public class BiletDAO implements IBiletDAO {
	final private SessionFactory sessionFactory;
	
	@Autowired
	public BiletDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(final Bilet bilet) {
		System.out.println("ENTER ModalitatePlataDAO.save() ");
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(bilet);
		session.getTransaction().commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Bilet> getReservations(final Client client) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Bilet.class,"bilet");
		criteria.createAlias("bilet.clientRezervare", "client");
		criteria.createAlias("bilet.stare", "stare");
		criteria.add(Restrictions.eq("client.id", client.getId()));
		criteria.add(Restrictions.eq("stare.id", (byte) 1));
		return criteria.list();
	}
	@Override
	public Bilet getBiletById(final Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Bilet.class);
		criteria.add(Restrictions.eq("id", id));
		return (Bilet) criteria.uniqueResult();
	}
	
	@Override
	public void updateBilet(final Bilet bilet){
		sessionFactory.getCurrentSession().update(bilet);
	}
}
