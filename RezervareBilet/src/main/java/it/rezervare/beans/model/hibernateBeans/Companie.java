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
@Table(name = "companii")
public class Companie {

	@Id
	@GeneratedValue
	@Column(name = "id_companie")
	private Integer id;

	@Column(name = "denumire")
	private String denumire;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "companie")
	private Set<Pachet> pachete = new HashSet<Pachet>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "companie")
	private Set<Zbor> zboruri = new HashSet<Zbor>();

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

	public Set<Pachet> getPachete() {
		return pachete;
	}

	public void setPachete(Set<Pachet> pachete) {
		this.pachete = pachete;
	}

	public Set<Zbor> getZboruri() {
		return zboruri;
	}

	public void setZboruri(Set<Zbor> zboruri) {
		this.zboruri = zboruri;
	}

}
