package it.rezervare.beans.model.requestBeans;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AdminRequestBean {

	private Integer id;
	private String denumire;
	private Integer idTara;
	private String denumireTara;
	private Integer distanta;
	private Integer idRoute;
	private Integer departureId;
	private Integer arrivalId;
	private Integer companyId;
	private Integer airlineId;
	private Date departureDate;
	private Date arrivalDate;
	private Integer standardPrice;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public Integer getIdTara() {
		return idTara;
	}

	public void setIdTara(final Integer idTara) {
		this.idTara = idTara;
	}

	public String getDenumireTara() {
		return denumireTara;
	}

	public void setDenumireTara(final String denumireTara) {
		this.denumireTara = denumireTara;
	}

	public Integer getDistanta() {
		return distanta;
	}

	public void setDistanta(final Integer distanta) {
		this.distanta = distanta;
	}

	public Integer getIdRoute() {
		return idRoute;
	}

	public void setIdRoute(final Integer idRoute) {
		this.idRoute = idRoute;
	}

	public Integer getDepartureId() {
		return departureId;
	}

	public void setDepartureId(final Integer departureId) {
		this.departureId = departureId;
	}

	public Integer getArrivalId() {
		return arrivalId;
	}

	public void setArrivalId(final Integer arrivalId) {
		this.arrivalId = arrivalId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(final Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(final Integer airlineId) {
		this.airlineId = airlineId;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(final Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(final Integer standardPrice) {
		this.standardPrice = standardPrice;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
