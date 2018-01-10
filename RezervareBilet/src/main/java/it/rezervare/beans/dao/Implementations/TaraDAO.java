package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.ITaraDAO;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tara> getAllCountrys() {
		System.out.println("\nENTER TaraDAO - getAllCountrys \n");
		Session session = sessionFactory.getCurrentSession();
		List<Tara> denumireTari = new ArrayList<>();
		try {
			Criteria criteria = session.createCriteria(Tara.class);
			denumireTari = criteria.list();
			System.out.println("\nTarile: ");
			for (Tara tara : denumireTari) {
				System.out.println(String.format("\n%-10s %-10s \n", "ID", "DENUMIRE"));
				System.out.println(String.format("%-10s %-10s ", tara.getId(), tara.getDenumire()));
			}

		} catch (Exception e) {
			System.out.println("Exception: [" + e.toString() + "] message: [" + e.getMessage() + "]\n");
		}
		System.out.println("\nEXIT TaraDAO - getAllCountrys");
		return denumireTari;
	}

	@Override
	public void updateCountry(Tara tara) {
		sessionFactory.getCurrentSession().saveOrUpdate(tara);
	}
	
	@Override
	public Tara getCountryById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Tara.class);
		cr.add(Restrictions.eq("id", id));
		return (Tara) cr.uniqueResult();
	}

}
