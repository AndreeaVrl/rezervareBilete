package it.rezervare.beans.model.requestBeans;

import java.util.Date;

public class CursaRequestView {

	private Integer contryFrom;
	private Integer airportFrom;
	private Integer countryTo;
	private Integer airportTo;
	private Date departureDate;
	private Date flyBack;
	private Boolean retur;
	
	public Integer getContryFrom() {
		return contryFrom;
	}
	public void setContryFrom(final Integer contryFrom) {
		this.contryFrom = contryFrom;
	}
	public Integer getAirportFrom() {
		return airportFrom;
	}
	public void setAirportFrom(final Integer airportFrom) {
		this.airportFrom = airportFrom;
	}
	public Integer getCountryTo() {
		return countryTo;
	}
	public void setCountryTo(final Integer countryTo) {
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
}
