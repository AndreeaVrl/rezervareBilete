package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.IPachetDAO;
import it.rezervare.beans.model.hibernateBeans.Pachet;

@Repository
@Transactional
public class PachetDAO implements IPachetDAO {
final private SessionFactory sessionFactory;
	
	@Autowired
	public PachetDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Pachet getPachetById(final Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Pachet.class);
		criteria.add(Restrictions.eq("id", id));
		return (Pachet) criteria.uniqueResult();
	}
}
