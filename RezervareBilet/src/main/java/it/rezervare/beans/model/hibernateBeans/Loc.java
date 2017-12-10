package it.rezervare.beans.model;

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

@Entity
@Table(name = "locuri")
public class Loc {

	@Id
	@GeneratedValue
	@Column(name = "id_loc")
	private Long id;

	@Column(name = "rand")
	private Integer rand;

	@Column(name = "coloana")
	private Integer coloana;

	@ManyToOne
	@JoinColumn(name = "id_tip_avion")
	private TipAvion locTipAvion;

	@ManyToOne
	@JoinColumn(name = "id_tip_loc")
	private TipLoc tipLoc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loc")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRand() {
		return rand;
	}

	public void setRand(Integer rand) {
		this.rand = rand;
	}

	public Integer getColoana() {
		return coloana;
	}

	public void setColoana(Integer coloana) {
		this.coloana = coloana;
	}

	public TipAvion getLocTipAvion() {
		return locTipAvion;
	}

	public void setLocTipAvion(TipAvion locTipAvion) {
		this.locTipAvion = locTipAvion;
	}

	public TipLoc getTipLoc() {
		return tipLoc;
	}

	public void setTipLoc(TipLoc tipLoc) {
		this.tipLoc = tipLoc;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(Set<Bilet> bilete) {
		this.bilete = bilete;
	}

}
