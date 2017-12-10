package it.rezervare.beans.model.hibernateBeans;

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
@Table(name = "tipuri_avioane")
public class TipAvion {

	@Id
	@GeneratedValue
	@Column(name = "id_tip_avion")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipAvion")
	private Set<Avion> Avioane = new HashSet<Avion>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "locTipAvion")
	private Set<Loc> locuri = new HashSet<Loc>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Set<Avion> getAvioane() {
		return Avioane;
	}

	public void setAvioane(Set<Avion> avioane) {
		Avioane = avioane;
	}

	public Set<Loc> getLocuri() {
		return locuri;
	}

	public void setLocuri(Set<Loc> locuri) {
		this.locuri = locuri;
	}

}
