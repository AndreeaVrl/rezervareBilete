package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.IPachetDAORadu;
import it.rezervare.beans.model.hibernateBeans.Pachet;

@Transactional
@Repository
public class PachetDAORadu implements IPachetDAORadu {
	
	final private SessionFactory sessionFactory;

	@Autowired
	private PachetDAORadu(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pachet> getAllPackages() {
		final Session session = sessionFactory.getCurrentSession();
		List<Pachet> packageList = new ArrayList<>();
		try {
			final Criteria criteria = session.createCriteria(Pachet.class);
			packageList = criteria.list();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return packageList;
	}

}
