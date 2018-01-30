package it.rezervare.beans.model.requestBeans;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AeroportRequestBean {

	private Integer id;
	private String denumire;
	private Integer idTara;
	private String denumireTara;

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}

}
