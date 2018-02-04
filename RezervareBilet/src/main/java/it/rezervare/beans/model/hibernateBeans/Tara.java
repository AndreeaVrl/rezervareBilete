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

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "tari")
public class Tara implements Serializable {

	private static final long serialVersionUID = 4934957665220869784L;

	@Id
	@GeneratedValue
	@Column(name = "id_tara")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taraClient")
	@BatchSize(size = 200)
	private Set<Client> clienti = new HashSet<Client>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taraAeroport")
	@BatchSize(size = 200)
	private Set<Aeroport> aeroporturi = new HashSet<Aeroport>();

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(final String denumire) {
		this.denumire = denumire;
	}

	public Set<Aeroport> getAeroporturi() {
		return aeroporturi;
	}

	public void setAeroporturi(final Set<Aeroport> aeroporturi) {
		this.aeroporturi = aeroporturi;
	}

	public Set<Client> getClienti() {
		return clienti;
	}

	public void setClienti(final Set<Client> clienti) {
		this.clienti = clienti;
	}

}
