package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.model.hibernateBeans.Cursa;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class CursaDAO implements ICursaDAO{
	final private SessionFactory sessionFactory;
	
	@Autowired
	public CursaDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Cursa> getAllFlights(){
		List<Cursa> allFlights = new ArrayList<>();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Cursa.class);
			allFlights = cr.list();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return allFlights;
	}
	
	@Override
	public Cursa getRouteByAirport(String airportFrom, String airportTo) {
		System.out.println(" ENTER CursaDAO.getRouteByAirport with airportFrom = ["+ airportFrom +"] airportTo = ["+airportTo+"]");
		Cursa cursa = new Cursa();
		try { 
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Cursa.class);
			cr.add(Restrictions.eq("aeroport_1.denumire", airportFrom));
			cr.add(Restrictions.eq("aeroport_2.denumire", airportTo));
			cursa = (Cursa) cr.uniqueResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(" EXIT CursaDAO.getRouteByAirport() ");
		return cursa;
	}

}
