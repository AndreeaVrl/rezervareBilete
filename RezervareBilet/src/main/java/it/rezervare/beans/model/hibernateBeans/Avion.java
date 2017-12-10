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
@Table(name = "avioane")
public class Avion {

	@Id
	@GeneratedValue
	@Column(name = "id_avion")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_tip_avion")
	private TipAvion tipAvion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avion")
	private Set<Zbor> zboruri = new HashSet<Zbor>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipAvion getTipAvion() {
		return tipAvion;
	}

	public void setTipAvion(TipAvion tipAvion) {
		this.tipAvion = tipAvion;
	}

	public Set<Zbor> getZboruri() {
		return zboruri;
	}

	public void setZboruri(Set<Zbor> zboruri) {
		this.zboruri = zboruri;
	}

}
