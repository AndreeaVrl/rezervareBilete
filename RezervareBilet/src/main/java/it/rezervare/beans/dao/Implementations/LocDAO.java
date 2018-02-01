package it.rezervare.beans.dao.Implementations;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.dao.Interfaces.ILocDAO;
import it.rezervare.beans.model.hibernateBeans.Loc;

@Transactional
@Repository
public class LocDAO implements ILocDAO {

	final private SessionFactory sessionFactory;
	
	@Autowired
	public LocDAO(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Loc getSeatByRowAndColumn(final Integer row, final String column) throws ApplicationException {
		System.out.println(" ENTER LocDAO getSeatByRowAndColumn() with row = ["+row+"] column = ["+column+"]");
		Loc loc = new Loc();
		try {
			final Criteria cr = sessionFactory.getCurrentSession().createCriteria(Loc.class);
			cr.add(Restrictions.eq("rand", row));
			cr.add(Restrictions.eq("coloana", column));
			loc = (Loc) cr.uniqueResult();
		} catch(final Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println(" EXIT LocDAO getSeatByRowAndColumn() with loc = ["+loc+"] ");
		return loc;
	}
}
