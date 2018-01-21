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
@Table(name = "drepturi")
public class Drept {

	@Id
	@GeneratedValue
	@Column(name = "id_drept")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@Column(name = "descriere")
	private String descriere;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dreptOperator")
	private Set<Operator> operatori = new HashSet<Operator>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public Set<Operator> getOperatori() {
		return operatori;
	}

	public void setOperatori(Set<Operator> operatori) {
		this.operatori = operatori;
	}

}
