package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Tara;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class CursaDAO implements ICursaDAO{
	final private SessionFactory sessionFactory;
	
	@Autowired
	public CursaDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Cursa> getAllFlights(){
		List<Cursa> allFlights = new ArrayList<>();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Tara.class);
			allFlights = cr.list();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return allFlights;
	}

}
