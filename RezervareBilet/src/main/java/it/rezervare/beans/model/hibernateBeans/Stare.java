package it.rezervare.beans.model.hibernateBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stari")
public class Stare {

	@Id
	@Column(name = "id_stare")
	private Byte id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stare")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Byte getId() {
		return id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(Set<Bilet> bilete) {
		this.bilete = bilete;
	}

}
