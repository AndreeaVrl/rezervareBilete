package it.rezervare.beans.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.rezervare.beans.constants.Query;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.model.Node;

@Repository
@Transactional
public class AeroportDAO implements IAeroportDAO{
	final private SessionFactory sessionFactory;
	
	@Autowired
	private AeroportDAO (final SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getDistinctAireportName() {
		System.out.println(" ENTER AeroportDAO - getDistinctAireportName()");
		List<Node> distinctAirportsNameList = new ArrayList<>();
		try {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(Query.GET_DISTINCT_AIRPORTS);
			query.addScalar("airportName", StandardBasicTypes.STRING);
			query.setResultTransformer(new AliasToBeanResultTransformer(Node.class));
			distinctAirportsNameList = query.list();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(" EXIT AeroportDAO - getDistinctAireportName() with distinctAirportsNameList = ["+distinctAirportsNameList+"]");
		return distinctAirportsNameList;
	}
}
