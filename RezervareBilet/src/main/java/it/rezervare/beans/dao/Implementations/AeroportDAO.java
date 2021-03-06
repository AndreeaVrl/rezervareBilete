package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.constants.Query;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.model.Node;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.requestBeans.AeroportAjaxView;

@Repository
@Transactional
public class AeroportDAO implements IAeroportDAO {
	final private SessionFactory sessionFactory;
	
	@Autowired
	private AeroportDAO (final SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getDistinctAireports() throws ApplicationException {
		System.out.println(" ENTER AeroportDAO - getDistinctAireportName()");
		List<Node> distinctAirportsNameList = new ArrayList<>();
		try {
			final Session session = sessionFactory.getCurrentSession();
			final SQLQuery query = session.createSQLQuery(Query.GET_DISTINCT_AIRPORTS);
			query.addScalar("id", StandardBasicTypes.INTEGER);
			query.addScalar("airportName", StandardBasicTypes.STRING);
			query.setResultTransformer(new AliasToBeanResultTransformer(Node.class));
			distinctAirportsNameList = query.list();
		} catch(final Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Ne pare rau, a aparut o problema! Reveniti mai tarziu!");
		}
		System.out.println(" EXIT AeroportDAO - getDistinctAireportName() with distinctAirportsNameList = ["+distinctAirportsNameList+"]");
		return distinctAirportsNameList;
	}
	@Override
	public Aeroport getAirportById(final Integer id) throws ApplicationException {
		Aeroport cursa = new Aeroport();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Aeroport.class);
			cr.add(Restrictions.eq("id", id));
			cursa= (Aeroport) cr.uniqueResult();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		return cursa;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aeroport> getAllAirports() {
		final Session session = sessionFactory.getCurrentSession();
		List<Aeroport> airports = new ArrayList<>();
		try {
			final Criteria criteria = session.createCriteria(Aeroport.class);
			airports = criteria.list();
		} catch (final Exception e) {
			System.out.println("Exception: [" + e.toString() + "] message: [" + e.getMessage() + "]\n");
		}
		return airports;
	}
	
	@Override
	public void addAirport(final Aeroport aeroport) {
		try {
			final Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(aeroport);
			session.getTransaction().commit();
			session.close();
		} catch (final Exception e) {
			e.printStackTrace();
			System.out.println("\n" + e.toString() + " " + e.getMessage() + "\n");
		}
	}
	
	@Override
	public void updateAirport(final Aeroport airport) {
		sessionFactory.getCurrentSession().saveOrUpdate(airport);
	}
	
	@Override
	public void deleteCountry(final Aeroport airport){
		final Session session = sessionFactory.getCurrentSession();
	    session.delete(airport);
	    session.flush() ;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aeroport> getAirportsByCountryId(final Integer id) {
		System.out.println("\n ENTER getAirportsByCountryId id=["+id+"] \n");
		List<Aeroport> airports = new ArrayList<>();
		try {
		
			final Session session = sessionFactory.getCurrentSession();
			final SQLQuery query = session.createSQLQuery(Query.GET_AIRPORTS_BY_COUNTRY_ID);
			query.setInteger("id", id);
			query.setResultTransformer(new AliasToBeanResultTransformer(AeroportAjaxView.class));
			airports = query.list();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n EXIT getAirportsByCountryId airports=["+airports+"] \n");
		return airports;
	}
}
