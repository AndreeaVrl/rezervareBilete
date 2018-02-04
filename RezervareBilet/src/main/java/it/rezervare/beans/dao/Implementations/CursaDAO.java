package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.constants.Query;
import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.model.CursaDijkstraAlgoritm;
import it.rezervare.beans.model.hibernateBeans.Cursa;

@Repository
@Transactional
public class CursaDAO implements ICursaDAO{
	final private SessionFactory sessionFactory;
	
	@Autowired
	public CursaDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CursaDijkstraAlgoritm> getAllFlights() throws ApplicationException {
		System.out.println(" ENTER CursaDAO getAllFlights() ");
		List<CursaDijkstraAlgoritm> allFlights = new ArrayList<>();
		try {
			final Session session = sessionFactory.getCurrentSession();
			final SQLQuery query = session.createSQLQuery(Query.GET_ALL_ROUTES);
			query.setResultTransformer(new AliasToBeanResultTransformer(CursaDijkstraAlgoritm.class));
			allFlights = query.list();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" EXIT CursaDAO getAllFlights() ");
		return allFlights;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cursa> getAll() throws ApplicationException {
		System.out.println(" ENTER CursaDAO getAllFlights() ");
		List<Cursa> allFlights = new ArrayList<>();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Cursa.class);
			allFlights = cr.list();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" EXIT CursaDAO getAllFlights() ");
		return allFlights;
	}
	
	
	@Override
	public Cursa getRouteByAirport(final String airportFrom, final String airportTo) throws ApplicationException {
		System.out.println(" ENTER CursaDAO.getRouteByAirport with airportFrom = ["+ airportFrom +"] airportTo = ["+airportTo+"]");
		Cursa cursa = new Cursa();
		try { 
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Cursa.class, "cursa");
			cr.createAlias("cursa.aeroport_1", "aeroport1"); 
			cr.createAlias("cursa.aeroport_2", "aeroport2");
			cr.add(Restrictions.eq("aeroport1.denumire", airportFrom));
			cr.add(Restrictions.eq("aeroport2.denumire", airportTo));
			cursa = (Cursa) cr.uniqueResult();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" EXIT CursaDAO.getRouteByAirport() ");
		return cursa;
	}
	
	@Override
	public void inserRoute(final Cursa route) {
		try {
			final Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(route);
			session.getTransaction().commit();
			session.close();
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("\n" + e.toString() + " " + e.getMessage() + "\n");
		}
	}
	
	@Override
	public Cursa getRouteById(final Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria cr = session.createCriteria(Cursa.class);
		cr.add(Restrictions.eq("id", id));
		return (Cursa) cr.uniqueResult();
	}
	
	@Override
	public void deleteRoute(final Cursa route){
		final Session session = sessionFactory.getCurrentSession();
	    session.delete(route);
	    session.flush() ;		
	}

}
