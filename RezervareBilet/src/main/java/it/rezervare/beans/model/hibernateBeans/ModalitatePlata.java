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
@Table(name = "mod_plati")
public class ModalitatePlata {

	@Id
	@GeneratedValue
	@Column(name = "id_mod_plata")
	private Integer id;

	@Column(name = "card_numar")
	private String cardNumar;

	@Column(name = "card_an")
	private Integer cardAn;

	@Column(name = "card_luna")
	private Integer cardLuna;

	@Column(name = "card_detinator")
	private String cardDetinator;

	@Column(name = "op_numar")
	private String opNumar;

	@Column(name = "paypal_cont")
	private String paypalCont;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "id_tip_card")
	private TipCard tipCard;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modalitatePlata")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumar() {
		return cardNumar;
	}

	public void setCardNumar(String cardNumar) {
		this.cardNumar = cardNumar;
	}

	public Integer getCardAn() {
		return cardAn;
	}

	public void setCardAn(Integer cardAn) {
		this.cardAn = cardAn;
	}

	public Integer getCardLuna() {
		return cardLuna;
	}

	public void setCardLuna(Integer cardLuna) {
		this.cardLuna = cardLuna;
	}

	public String getCardDetinator() {
		return cardDetinator;
	}

	public void setCardDetinator(String cardDetinator) {
		this.cardDetinator = cardDetinator;
	}

	public String getOpNumar() {
		return opNumar;
	}

	public void setOpNumar(String opNumar) {
		this.opNumar = opNumar;
	}

	public String getPaypalCont() {
		return paypalCont;
	}

	public void setPaypalCont(String paypalCont) {
		this.paypalCont = paypalCont;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TipCard getTipCard() {
		return tipCard;
	}

	public void setTipCard(TipCard tipCard) {
		this.tipCard = tipCard;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(Set<Bilet> bilete) {
		this.bilete = bilete;
	}

}
