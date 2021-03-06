package it.rezervare.beans.model.hibernateBeans;

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
@Table(name = "pachete")
public class Pachet {

	@Id
	@GeneratedValue
	@Column(name = "id_pachet")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@Column(name = "taxa_pachet")
	private BigDecimal taxaPachet;

	@Column(name = "caracteristici")
	private String caracteristici;

	@ManyToOne
	@JoinColumn(name = "id_companie")
	private Companie companie;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pachet")
	private Set<Bilet> bilet = new HashSet<Bilet>();
	
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

	public BigDecimal getTaxaPachet() {
		return taxaPachet;
	}

	public void setTaxaPachet(BigDecimal taxaPachet) {
		this.taxaPachet = taxaPachet;
	}

	public String getCaracteristici() {
		return caracteristici;
	}

	public void setCaracteristici(String caracteristici) {
		this.caracteristici = caracteristici;
	}

	public Companie getCompanie() {
		return companie;
	}

	public void setCompanie(Companie companie) {
		this.companie = companie;
	}

	public Set<Bilet> getBilet() {
		return bilet;
	}

	public void setBilet(Set<Bilet> bilet) {
		this.bilet = bilet;
	}


}
