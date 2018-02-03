package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.dao.Interfaces.IBileteDAO;
import it.rezervare.beans.model.hibernateBeans.Bilet;
@Transactional
@Repository
public class BileteDAO implements IBileteDAO {
	
final private SessionFactory sessionFactory;
	
	@Autowired
	public BileteDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Bilet getBiletByIdLocAndIdZbor(final Integer idLoc, final Integer idZbor) throws ApplicationException {
		System.out.println(" ENTER BileteDAO getBiletByIdLocAndIdZbor() with idLoc = ["+idLoc+"] idZbor = ["+idZbor+"]");
		Bilet bilet = new Bilet();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Bilet.class, "bilet");
			cr.createAlias("bilet.zbor", "zbor");
			cr.createAlias("bilet.loc", "loc");
			cr.add(Restrictions.eq("zbor.id", idZbor));
			cr.add(Restrictions.eq("loc.id", idLoc));
			bilet = (Bilet) cr.uniqueResult();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" EXIT BileteDAO getBiletByIdLocAndIdZbor() with bilet = ["+bilet+"] ");
		return bilet;
	}
}
