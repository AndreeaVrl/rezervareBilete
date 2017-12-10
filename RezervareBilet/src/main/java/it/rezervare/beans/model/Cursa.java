package it.rezervare.beans.model;

import java.math.BigDecimal;
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
@Table(name = "curse")
public class Cursa {

	@Id
	@GeneratedValue
	@Column(name = "id_cursa")
	private Long id;

	@Column(name = "pret_standard")
	private BigDecimal pretStandard;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cursa")
	private Set<Zbor> zboruri = new HashSet<Zbor>();

	@ManyToOne
	@JoinColumn(name = "id_aeroport_1", referencedColumnName="id_aeroport")
	private Aeroport aeroport_1;

	@ManyToOne
	@JoinColumn(name = "id_aeroport_2", referencedColumnName="id_aeroport")
	private Aeroport aeroport_2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPretStandard() {
		return pretStandard;
	}

	public void setPretStandard(BigDecimal pretStandard) {
		this.pretStandard = pretStandard;
	}

	public Set<Zbor> getZboruri() {
		return zboruri;
	}

	public void setZboruri(Set<Zbor> zboruri) {
		this.zboruri = zboruri;
	}

	public Aeroport getAeroport_1() {
		return aeroport_1;
	}

	public void setAeroport_1(Aeroport aeroport_1) {
		this.aeroport_1 = aeroport_1;
	}

	public Aeroport getAeroport_2() {
		return aeroport_2;
	}

	public void setAeroport_2(Aeroport aeroport_2) {
		this.aeroport_2 = aeroport_2;
	}

}
