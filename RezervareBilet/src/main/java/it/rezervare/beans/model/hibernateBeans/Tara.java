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
@Table(name = "tari")
public class Tara {

	@Id
	@GeneratedValue
	@Column(name = "id_tara")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tara")
	private Set<Localitate> localitati = new HashSet<Localitate>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taraClient")
	private Set<Client> clienti = new HashSet<Client>();

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

	public Set<Localitate> getLocalitati() {
		return localitati;
	}

	public void setLocalitati(Set<Localitate> localitati) {
		this.localitati = localitati;
	}

	public Set<Client> getClienti() {
		return clienti;
	}

	public void setClienti(Set<Client> clienti) {
		this.clienti = clienti;
	}

}
