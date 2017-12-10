package it.rezervare.beans.model;

import java.util.Date;
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
@Table(name = "zboruri")
public class Zbor {

	@Id
	@GeneratedValue
	@Column(name = "id_zbor")
	private Long id;

	@Column(name = "data_plecare")
	private Date dataPlecare;

	@Column(name = "data_sosire")
	private Date dataSosire;

	@ManyToOne
	@JoinColumn(name = "id_pachet")
	private Pachet pachet;

	@ManyToOne
	@JoinColumn(name = "id_avion")
	private Avion avion;

	@ManyToOne
	@JoinColumn(name = "id_tara")
	private Cursa cursa;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "zbor")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPlecare() {
		return dataPlecare;
	}

	public void setDataPlecare(Date dataPlecare) {
		this.dataPlecare = dataPlecare;
	}

	public Date getDataSosire() {
		return dataSosire;
	}

	public void setDataSosire(Date dataSosire) {
		this.dataSosire = dataSosire;
	}

	public Pachet getPachet() {
		return pachet;
	}

	public void setPachet(Pachet pachet) {
		this.pachet = pachet;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Cursa getCursa() {
		return cursa;
	}

	public void setCursa(Cursa cursa) {
		this.cursa = cursa;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(Set<Bilet> bilete) {
		this.bilete = bilete;
	}

}
