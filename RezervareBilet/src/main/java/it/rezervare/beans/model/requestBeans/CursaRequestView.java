package it.rezervare.beans.model.requestBeans;

public class CursaRequestView {

	private String contryFrom;
	private Integer airportFrom;
	private String countryTo;
	private Integer airportTo;
	 
	public String getContryFrom() {
		return contryFrom;
	}
	public void setContryFrom(final String contryFrom) {
		this.contryFrom = contryFrom;
	}
	public Integer getAirportFrom() {
		return airportFrom;
	}
	public void setAirportFrom(final Integer airportFrom) {
		this.airportFrom = airportFrom;
	}
	public String getCountryTo() {
		return countryTo;
	}
	public void setCountryTo(final String countryTo) {
		this.countryTo = countryTo;
	}
	public Integer getAirportTo() {
		return airportTo;
	}
	public void setAirportTo(final Integer airportTo) {
		this.airportTo = airportTo;
	}
	@Override
	public String toString() {
		return "CursaRequestView [contryFrom=" + contryFrom + ", airportFrom=" + airportFrom + ", countryTo="
				+ countryTo + ", airportTo=" + airportTo + "]";
	}
}
