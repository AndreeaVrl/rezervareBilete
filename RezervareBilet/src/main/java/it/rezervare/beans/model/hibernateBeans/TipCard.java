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
@Table(name = "tipuri_carduri")
public class TipCard {

	@Id
	@GeneratedValue
	@Column(name = "id_tip_card")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipCard")
	private Set<ModalitatePlata> modalitatiPlata = new HashSet<ModalitatePlata>();

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public Set<ModalitatePlata> getModalitatiPlata() {
		return modalitatiPlata;
	}

	public void setModalitatiPlata(final Set<ModalitatePlata> modalitatiPlata) {
		this.modalitatiPlata = modalitatiPlata;
	}

	@Override
	public String toString() {
		return "TipCard [id=" + id + ", denumire=" + denumire + ", modalitatiPlata=" + modalitatiPlata + "]";
	}

}
