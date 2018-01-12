package it.rezervare.beans.constants;

public class Query {
	
	public static final String GET_DISTINCT_AIRPORTS = 
			"SELECT DISTINCT(a.denumire) airportName " +
			"  FROM aeroporturi a " +
			" INNER join curse c " +
			"    ON a.id_aeroport = c.id_aeroport_1 ";

}
