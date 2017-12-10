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
@Table(name = "aeroporturi")
public class Aeroport {

	@Id
	@GeneratedValue
	@Column(name = "id_aeroport")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@ManyToOne
	@JoinColumn(name = "id_localitate")
	private Localitate localitate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aeroport_1")
	private Set<Cursa> curseAeroport_1 = new HashSet<Cursa>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aeroport_2")
	private Set<Cursa> curseAeroport_2 = new HashSet<Cursa>();

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

	public Localitate getLocalitate() {
		return localitate;
	}

	public void setLocalitate(Localitate localitate) {
		this.localitate = localitate;
	}

	public Set<Cursa> getCurseAeroport_1() {
		return curseAeroport_1;
	}

	public void setCurseAeroport_1(Set<Cursa> curseAeroport_1) {
		this.curseAeroport_1 = curseAeroport_1;
	}

	public Set<Cursa> getCurseAeroport_2() {
		return curseAeroport_2;
	}

	public void setCurseAeroport_2(Set<Cursa> curseAeroport_2) {
		this.curseAeroport_2 = curseAeroport_2;
	}

}
