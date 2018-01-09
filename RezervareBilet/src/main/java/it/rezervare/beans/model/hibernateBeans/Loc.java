package it.rezervare.beans.model.hibernateBeans;

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
	private Integer id;

	@Column(name = "rand")
	private Integer rand;

	@Column(name = "coloana")
	private String coloana;

	@ManyToOne
	@JoinColumn(name = "id_tip_avion")
	private TipAvion locTipAvion;

	@ManyToOne
	@JoinColumn(name = "id_tip_loc")
	private TipLoc tipLoc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loc")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRand() {
		return rand;
	}

	public void setRand(Integer rand) {
		this.rand = rand;
	}

	public String getColoana() {
		return coloana;
	}

	public void setColoana(String coloana) {
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
