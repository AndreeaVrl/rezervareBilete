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
@Table(name = "operatori")
public class Operator {

	@Id
	@GeneratedValue
	@Column(name = "id_operator")
	private Integer id;

	@Column(name = "nume")
	private String nume;

	@Column(name = "utilizator")
	private String utilizator;

	@Column(name = "parola")
	private String parola;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	@ManyToOne
	@JoinColumn(name = "id_drept")
	private Drept dreptOperator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getUtilizator() {
		return utilizator;
	}

	public void setUtilizator(String utilizator) {
		this.utilizator = utilizator;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(Set<Bilet> bilete) {
		this.bilete = bilete;
	}

	public Drept getDreptOperator() {
		return dreptOperator;
	}

	public void setDreptOperator(Drept dreptOperator) {
		this.dreptOperator = dreptOperator;
	}

}
