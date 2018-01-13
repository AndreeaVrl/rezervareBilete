package it.rezervare.beans.constants;

public class Query {
	
	public static final String GET_DISTINCT_AIRPORTS = 
			"SELECT a.denumire airportName, a.id_aeroport id " +
			"  FROM aeroporturi a ";

	public static final String GET_ALL_ROUTES = 
			"select c.id_cursa as idCursa, c.id_aeroport_1 as idAeroportFrom, c.id_aeroport_2 as idAeroportTo, c.distanta as distanta "
			+ "from curse c ";
}
