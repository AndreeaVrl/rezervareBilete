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

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "curse")
public class Cursa {

	@Id
	@GeneratedValue
	@Column(name = "id_cursa")
	private Integer id;

	@Column(name = "distanta")
	private int distanta;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cursa")
	@BatchSize(size = 500)
	private Set<Zbor> zboruri = new HashSet<Zbor>();

	@ManyToOne
	@JoinColumn(name = "id_aeroport_1", referencedColumnName = "id_aeroport")
	private Aeroport aeroport_1;

	@ManyToOne
	@JoinColumn(name = "id_aeroport_2", referencedColumnName = "id_aeroport")
	private Aeroport aeroport_2;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Set<Zbor> getZboruri() {
		return zboruri;
	}

	public void setZboruri(final Set<Zbor> zboruri) {
		this.zboruri = zboruri;
	}

	public Aeroport getAeroport_1() {
		return aeroport_1;
	}

	public void setAeroport_1(final Aeroport aeroport_1) {
		this.aeroport_1 = aeroport_1;
	}

	public Aeroport getAeroport_2() {
		return aeroport_2;
	}

	public void setAeroport_2(final Aeroport aeroport_2) {
		this.aeroport_2 = aeroport_2;
	}

	public int getDistanta() {
		return distanta;
	}

	public void setDistanta(final int distanta) {
		this.distanta = distanta;
	}

	@Override
	public String toString() {
		return "Cursa [id=" + id + "]";
	}

}
