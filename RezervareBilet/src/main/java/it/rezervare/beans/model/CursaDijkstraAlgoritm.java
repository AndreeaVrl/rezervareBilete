package it.rezervare.beans.model;

public class CursaDijkstraAlgoritm {
	
	private Integer idCursa;
	private Integer idAeroportFrom;
	private Integer idAeroportTo;
	private int distanta;
	
	public Integer getIdCursa() {
		return idCursa;
	}
	public void setIdCursa(Integer idCursa) {
		this.idCursa = idCursa;
	}
	public Integer getIdAeroportFrom() {
		return idAeroportFrom;
	}
	public void setIdAeroportFrom(Integer idAeroportFrom) {
		this.idAeroportFrom = idAeroportFrom;
	}
	public Integer getIdAeroportTo() {
		return idAeroportTo;
	}
	public void setIdAeroportTo(int idAeroportTo) {
		this.idAeroportTo = idAeroportTo;
	}
	public Integer getDistanta() {
		return distanta;
	}
	public void setDistanta(int distanta) {
		this.distanta = distanta;
	}
	@Override
	public String toString() {
		return "CursaDijkstraAlgoritm [idCursa=" + idCursa + ", idAeroportFrom=" + idAeroportFrom + ", idAeroportTo="
				+ idAeroportTo + ", distanta=" + distanta + "]";
	}

}
