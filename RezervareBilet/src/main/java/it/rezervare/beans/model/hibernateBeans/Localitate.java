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
@Table(name = "localitati")
public class Localitate {

	@Id
	@GeneratedValue
	@Column(name = "id_localitate")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@ManyToOne
	@JoinColumn(name = "id_tara")
	private Tara tara;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "localitate")
	private Set<Aeroport> aeroporturi = new HashSet<Aeroport>();

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

	public Tara getTara() {
		return tara;
	}

	public void setTara(Tara tara) {
		this.tara = tara;
	}

	public Set<Aeroport> getAeroporturi() {
		return aeroporturi;
	}

	public void setAeroporturi(Set<Aeroport> aeroporturi) {
		this.aeroporturi = aeroporturi;
	}

}
