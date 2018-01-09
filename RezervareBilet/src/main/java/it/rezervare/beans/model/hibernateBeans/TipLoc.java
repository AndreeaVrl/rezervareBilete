package it.rezervare.beans.model.hibernateBeans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipuri_locuri")
public class TipLoc {

	@Id
	@GeneratedValue
	@Column(name = "id_tip_loc")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@Column(name = "taxa_rezervare")
	private BigDecimal taxaRezervare;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipLoc")
	private Set<Loc> locuri = new HashSet<Loc>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public BigDecimal getTaxaRezervare() {
		return taxaRezervare;
	}

	public void setTaxaRezervare(BigDecimal taxaRezervare) {
		this.taxaRezervare = taxaRezervare;
	}

	public Set<Loc> getLocuri() {
		return locuri;
	}

	public void setLocuri(Set<Loc> locuri) {
		this.locuri = locuri;
	}

}
