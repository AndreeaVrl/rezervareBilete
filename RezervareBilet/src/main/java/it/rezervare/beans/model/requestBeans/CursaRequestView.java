package it.rezervare.beans.model.requestBeans;

import java.util.Date;

public class CursaRequestView {

	private String contryFrom;
	private Integer airportFrom;
	private String countryTo;
	private Integer airportTo;
	private Date departureDate;
	private Date flyBack;
	private Boolean retur;
	 
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
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getFlyBack() {
		return flyBack;
	}
	public void setFlyBack(final Date flyBack) {
		this.flyBack = flyBack;
	}
	public Boolean getRetur() {
		return retur;
	}
	public void setRetur(final Boolean retur) {
		this.retur = retur;
	}
	@Override
	public String toString() {
		return "CursaRequestView [contryFrom=" + contryFrom + ", airportFrom=" + airportFrom + ", countryTo="
				+ countryTo + ", airportTo=" + airportTo + ", departureDate=" + departureDate +"]";
	}
}
