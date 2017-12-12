package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.model.hibernateBeans.Localitate;
import it.rezervare.beans.model.hibernateBeans.Tara;

@Transactional
@Repository
public class TaraDAO implements ITaraDAO {

	final private SessionFactory sessionFactory;

	@Autowired
	private TaraDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void inserTara(Tara tara) {
		System.out.println("\nENTER TaraDAO - inserTara() with [" + tara.getDenumire() + "]\n");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(tara);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n" + e.toString() + " " + e.getMessage() + "\n");
		}
		System.out.println("\nEXIT TaraDAO - inserTara()\n");
	}

	@Override
	public Tara getTaraByDenumire(String denumire) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Tara.class);
		cr.add(Restrictions.eq("denumire", denumire));
		return (Tara) cr.uniqueResult();
	}

	@Override
	public void insertLocalitate(Localitate localitate) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(localitate);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n" + e.toString() + " " + e.getMessage() + "\n");
		}
	}

}
