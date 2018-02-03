package it.rezervare.beans.model.hibernateBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "avioane")
public class Avion {

	@Id
	@Column(name = "id_avion")
	private String id;

	@ManyToOne
	@JoinColumn(name = "id_tip_avion")
	private TipAvion tipAvion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avion")
	@BatchSize(size = 100) 
	private Set<Zbor> zboruri = new HashSet<Zbor>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public TipAvion getTipAvion() {
		return tipAvion;
	}

	public void setTipAvion(final TipAvion tipAvion) {
		this.tipAvion = tipAvion;
	}

	public Set<Zbor> getZboruri() {
		return zboruri;
	}

	public void setZboruri(final Set<Zbor> zboruri) {
		this.zboruri = zboruri;
	}

}
