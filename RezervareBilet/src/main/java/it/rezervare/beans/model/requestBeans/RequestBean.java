package it.rezervare.beans.model.requestBeans;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RequestBean {

	private Integer id;
	private Integer idTara;
	private Integer distanta;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getIdTara() {
		return idTara;
	}

	public void setIdTara(final Integer idTara) {
		this.idTara = idTara;
	}

	public Integer getDistanta() {
		return distanta;
	}

	public void setDistanta(final Integer distanta) {
		this.distanta = distanta;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
