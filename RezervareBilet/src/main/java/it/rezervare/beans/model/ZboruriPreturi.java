package it.rezervare.beans.model;

import java.math.BigDecimal;
import java.util.LinkedList;

import it.rezervare.beans.model.hibernateBeans.Zbor;

public class ZboruriPreturi {

	private LinkedList<Zbor> zboruri;
//	private LinkedList<Companie> companii;
	private BigDecimal pret;
	
	public LinkedList<Zbor> getZboruri() {
		return zboruri;
	}
	public void setZboruri(LinkedList<Zbor> zboruri) {
		this.zboruri = zboruri;
	}
/*	public LinkedList<Companie> getCompanii() {
		return companii;
	}
	public void setCompanii(LinkedList<Companie> companii) {
		this.companii = companii;
	}*/
	public BigDecimal getPret() {
		return pret;
	}
	public void setPret(BigDecimal pret) {
		this.pret = pret;
	}
	
}
