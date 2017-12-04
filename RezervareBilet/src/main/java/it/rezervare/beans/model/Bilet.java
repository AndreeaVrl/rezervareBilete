package it.rezervare.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bilete")
public class Bilet {

	@Id
	@GeneratedValue
	@Column(name = "id_loc")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client clientBilet;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client clientRezervare;

	@ManyToOne
	@JoinColumn(name = "id_zbor")
	private Zbor zbor;

	@ManyToOne
	@JoinColumn(name = "idLoc")
	private Loc loc;

	@ManyToOne
	@JoinColumn(name = "id_mod_plata")
	private ModalitatePlata modalitatePlata;

	@ManyToOne
	@JoinColumn(name = "id_operator")
	private Operator operator;

	@ManyToOne
	@JoinColumn(name = "id_stare")
	private Stare stare;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClientBilet() {
		return clientBilet;
	}

	public void setClientBilet(Client clientBilet) {
		this.clientBilet = clientBilet;
	}

	public Client getClientRezervare() {
		return clientRezervare;
	}

	public void setClientRezervare(Client clientRezervare) {
		this.clientRezervare = clientRezervare;
	}

	public Zbor getZbor() {
		return zbor;
	}

	public void setZbor(Zbor zbor) {
		this.zbor = zbor;
	}

	public Loc getLoc() {
		return loc;
	}

	public void setLoc(Loc loc) {
		this.loc = loc;
	}

	public ModalitatePlata getModalitatePlata() {
		return modalitatePlata;
	}

	public void setModalitatePlata(ModalitatePlata modalitatePlata) {
		this.modalitatePlata = modalitatePlata;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Stare getStare() {
		return stare;
	}

	public void setStare(Stare stare) {
		this.stare = stare;
	}

}
