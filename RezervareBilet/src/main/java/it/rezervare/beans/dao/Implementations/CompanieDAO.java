package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.ICompanieDAO;
import it.rezervare.beans.model.hibernateBeans.Companie;

@Transactional
@Repository
public class CompanieDAO implements ICompanieDAO {

	final private SessionFactory sessionFactory;

	@Autowired
	private CompanieDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Companie> getAllCompanies() {
		final Session session = sessionFactory.getCurrentSession();
		List<Companie> companiesList = new ArrayList<>();
		try {
			final Criteria criteria = session.createCriteria(Companie.class);
			companiesList = criteria.list();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return companiesList;
	}

}
