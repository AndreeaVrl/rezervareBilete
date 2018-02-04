package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.model.hibernateBeans.Avion;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.utils.PDFHelper;

@Transactional
@Repository
public class ZborDAO implements IZborDAO {

	final private SessionFactory sessionFactory;

	@Autowired
	private ZborDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zbor> getFlightList (final Integer idCursa, final Date date) throws ApplicationException {
		List<Zbor> listaZboruri = new ArrayList<>();
		System.out.println(" ENTER  ZborDAO.getFlightList() with idCursa = ["+idCursa+"] date = ["+date+"]");
		try {
			final Date fromDate = PDFHelper.getDateWithoutTime(date);
			final Date toDate = PDFHelper.getDateWithoutTime(PDFHelper.getTomorrowDate(date));
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Zbor.class, "zbor");
			cr.createAlias("zbor.cursa", "cursa"); 
			cr.add(Restrictions.eq("cursa.id", idCursa));
			//cr.add(Restrictions.eq("zbor.dataPlecare", date));
			cr.add(Restrictions.ge("zbor.dataPlecare", fromDate));
			cr.add(Restrictions.le("zbor.dataPlecare", toDate));
			listaZboruri = cr.list();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" Exit ZborDAO.getFlightList() with listaZboruri = ["+listaZboruri+"] ");
		return listaZboruri;
	}

	@Override
	public Zbor getFlightById(final Integer id) throws ApplicationException {
		System.out.println(" ENTER  ZborDAO.getFlightById() with id = [" + id + "]");
		Zbor zbor = new Zbor();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Zbor.class);
			cr.add(Restrictions.eq("id", id));
			zbor = (Zbor) cr.uniqueResult();
			Hibernate.initialize(zbor);
			Hibernate.initialize(zbor.getAvion());
			Hibernate.initialize(zbor.getAvion().getTipAvion());
			Hibernate.initialize(zbor.getAvion().getTipAvion().getLocuri());
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" Exit ZborDAO.getFlightById() with zbor = [" + zbor + "] ");
		return zbor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zbor> getAllFlights() {
		final Session session = sessionFactory.getCurrentSession();
		List<Zbor> flightsList = new ArrayList<>();
		try {
			final Criteria criteria = session.createCriteria(Zbor.class);
			flightsList = criteria.list();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return flightsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Avion> getAllAirplanes() {
		final Session session = sessionFactory.getCurrentSession();
		List<Avion> airplanesList = new ArrayList<>();
		try {
			final Criteria criteria = session.createCriteria(Avion.class);
			airplanesList = criteria.list();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return airplanesList;
	}

	@Override
	public void insertFlight(final Zbor flight) {
		try {
			final Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(flight);
			session.getTransaction().commit();
			session.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Avion getAirplaneById(final Integer id) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria cr = session.createCriteria(Avion.class);
		cr.add(Restrictions.eq("id", String.valueOf(id)));
		return (Avion) cr.uniqueResult();
	}
	
	@Override
	public void updateFlight(final Zbor flight) {
		sessionFactory.getCurrentSession().saveOrUpdate(flight);
	}

}
