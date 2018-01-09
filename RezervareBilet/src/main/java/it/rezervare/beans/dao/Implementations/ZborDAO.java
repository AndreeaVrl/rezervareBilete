package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.model.hibernateBeans.Zbor;

@Transactional
@Repository
public class ZborDAO implements IZborDAO {

	final private SessionFactory sessionFactory;

	@Autowired
	private ZborDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Zbor> getFlightList (Integer idCursa) {
		List<Zbor> listaZboruri = new ArrayList<>();
		System.out.println(" ENTER  ZborDAO.getFlightList() with idCursa = ["+idCursa+"] ");
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(Zbor.class);
			cr.add(Restrictions.eq("cursa.id", idCursa));
			listaZboruri = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" Exit ZborDAO.getFlightList() with listaZboruri = ["+listaZboruri+"] ");
		return listaZboruri;
	}
}
