package it.rezervare.beans.model.requestBeans;

public class CursaRequestView {

	private String contryFrom;
	private String airportFrom;
	private String countryTo;
	private String airportTo;
	
	public String getContryFrom() {
		return contryFrom;
	}
	public void setContryFrom(String contryFrom) {
		this.contryFrom = contryFrom;
	}
	public String getAirportFrom() {
		return airportFrom;
	}
	public void setAirportFrom(String airportFrom) {
		this.airportFrom = airportFrom;
	}
	public String getCountryTo() {
		return countryTo;
	}
	public void setCountryTo(String countryTo) {
		this.countryTo = countryTo;
	}
	public String getAirportTo() {
		return airportTo;
	}
	public void setAirportTo(String airportTo) {
		this.airportTo = airportTo;
	}
	@Override
	public String toString() {
		return "CursaRequestView [contryFrom=" + contryFrom + ", airportFrom=" + airportFrom + ", countryTo="
				+ countryTo + ", airportTo=" + airportTo + "]";
	}
}
