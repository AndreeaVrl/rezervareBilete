package it.rezervare.beans.model.hibernateBeans;

import java.io.Serializable;
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
public class Tara implements Serializable{

	@Id 
	@GeneratedValue
	@Column(name = "id_tara")
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taraClient")
	private Set<Client> clienti = new HashSet<Client>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taraAeroport")
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

	public Set<Aeroport> getAeroporturi() {
		return aeroporturi;
	}

	public void setAeroporturi(Set<Aeroport> aeroporturi) {
		this.aeroporturi = aeroporturi;
	}

	public Set<Client> getClienti() {
		return clienti;
	}

	public void setClienti(Set<Client> clienti) {
		this.clienti = clienti;
	}

}
