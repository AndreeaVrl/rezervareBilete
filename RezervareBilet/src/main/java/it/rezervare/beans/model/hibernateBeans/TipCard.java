package it.rezervare.beans.model;

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
@Table(name = "tipuri_carduri")
public class TipCard {

	@Id
	@GeneratedValue
	@Column(name = "id_tip_card")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipCard")
	private Set<ModalitatePlata> modalitatiPlata = new HashSet<ModalitatePlata>();

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

	public Set<ModalitatePlata> getModalitatiPlata() {
		return modalitatiPlata;
	}

	public void setModalitatiPlata(Set<ModalitatePlata> modalitatiPlata) {
		this.modalitatiPlata = modalitatiPlata;
	}

}
