package it.rezervare.beans.dao.Implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.IBiletDAO;
import it.rezervare.beans.model.hibernateBeans.Bilet;
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
}
