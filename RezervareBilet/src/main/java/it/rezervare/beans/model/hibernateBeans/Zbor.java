package it.rezervare.beans.model.hibernateBeans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "zboruri")
@BatchSize(size = 100) 
public class Zbor {

	@Id
	@GeneratedValue
	@Column(name = "id_zbor")
	private Integer id;

	@Column(name = "data_plecare")
	private Date dataPlecare;

	@Column(name = "data_sosire")
	private Date dataSosire;

	@Column(name = "pret_standard")
	private BigDecimal pret;

	@ManyToOne
	@JoinColumn(name = "id_companie")
	private Companie companie;

	@ManyToOne
	@JoinColumn(name = "id_avion")
	private Avion avion;

	@ManyToOne
	@JoinColumn(name = "id_cursa")
	private Cursa cursa;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "zbor")
	@BatchSize(size = 100) 
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Date getDataPlecare() {
		return dataPlecare;
	}

	public void setDataPlecare(final Date dataPlecare) {
		this.dataPlecare = dataPlecare;
	}

	public Date getDataSosire() {
		return dataSosire;
	}

	public void setDataSosire(final Date dataSosire) {
		this.dataSosire = dataSosire;
	}

	public Companie getCompanie() {
		return companie;
	}

	public void setCompanie(final Companie companie) {
		this.companie = companie;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(final Avion avion) {
		this.avion = avion;
	}

	public Cursa getCursa() {
		return cursa;
	}

	public void setCursa(final Cursa cursa) {
		this.cursa = cursa;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(final Set<Bilet> bilete) {
		this.bilete = bilete;
	}

	public BigDecimal getPret() {
		return pret;
	}

	public void setPret(final BigDecimal pret) {
		this.pret = pret;
	}

	@Override
	public String toString() {
		return "Zbor [id=" + id + ", dataPlecare=" + dataPlecare + ", dataSosire=" + dataSosire + ", pret=" + pret
				+ ", companie=" + companie + ", cursa=" + cursa.getId() + "]";
	}

}
