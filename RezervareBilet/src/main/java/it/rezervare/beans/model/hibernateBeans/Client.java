package it.rezervare.beans.model.hibernateBeans;

import java.util.Date;
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
@Table(name = "clienti")
public class Client {

	@Id
	@GeneratedValue
	@Column(name = "id_client")
	private Integer id;

	@Column(name = "nume")
	private String nume;

	@Column(name = "prenume")
	private String prenume;

	@Column(name = "data_nasterii")
	private Date dataNasterii;

	@Column(name = "email")
	private String email;

	@Column(name = "parola")
	private String parola;

	@Column(name = "telefon")
	private String telefon;

	@Column(name = "fact_adresa")
	private String factAdresa;

	@Column(name = "fact_localitate")
	private String factLocalitate;

	@Column(name = "fact_zipcode")
	private String factZipcode;

	@Column(name = "pj_firma")
	private String pjFirma;

	@Column(name = "pj_codfiscal")
	private String pjCodFiscal;

	@ManyToOne
	@JoinColumn(name = "id_tara")
	private Tara taraClient;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<ModalitatePlata> modalitatiPlata = new HashSet<ModalitatePlata>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientBilet")
	private Set<Bilet> bilete = new HashSet<Bilet>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientRezervare")
	private Set<Bilet> bileteRezervate = new HashSet<Bilet>();

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(final String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(final String prenume) {
		this.prenume = prenume;
	}

	public Date getDataNasterii() {
		return dataNasterii;
	}

	public void setDataNasterii(final Date dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(final String parola) {
		this.parola = parola;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(final String telefon) {
		this.telefon = telefon;
	}

	public String getFactAdresa() {
		return factAdresa;
	}

	public void setFactAdresa(final String factAdresa) {
		this.factAdresa = factAdresa;
	}

	public String getFactLocalitate() {
		return factLocalitate;
	}

	public void setFactLocalitate(final String factLocalitate) {
		this.factLocalitate = factLocalitate;
	}

	public String getFactZipcode() {
		return factZipcode;
	}

	public void setFactZipcode(final String factZipcode) {
		this.factZipcode = factZipcode;
	}

	public String getPjFirma() {
		return pjFirma;
	}

	public void setPjFirma(final String pjFirma) {
		this.pjFirma = pjFirma;
	}

	public String getPjCodFiscal() {
		return pjCodFiscal;
	}

	public void setPjCodFiscal(final String pjCodFiscal) {
		this.pjCodFiscal = pjCodFiscal;
	}

	public Tara getTaraClient() {
		return taraClient;
	}

	public void setTaraClient(final Tara taraClient) {
		this.taraClient = taraClient;
	}

	public Set<ModalitatePlata> getModalitatiPlata() {
		return modalitatiPlata;
	}

	public void setModalitatiPlata(final Set<ModalitatePlata> modalitatiPlata) {
		this.modalitatiPlata = modalitatiPlata;
	}

	public Set<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(final Set<Bilet> bilete) {
		this.bilete = bilete;
	}

	public Set<Bilet> getBileteRezervate() {
		return bileteRezervate;
	}

	public void setBileteRezervate(final Set<Bilet> bileteRezervate) {
		this.bileteRezervate = bileteRezervate;
	}

}
