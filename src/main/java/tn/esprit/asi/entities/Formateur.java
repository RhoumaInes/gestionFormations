package tn.esprit.asi.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESP_ENSEIGNANT")
public class Formateur {	
	@Id
	@Column(name = "ID_ENS")
	private String id;
	private String NOM_ENS;
	private String TYPE_ENS;
	@Temporal(TemporalType.DATE)
	private Date DATE_REC;
	private String NIVEAU;
	
	@Temporal(TemporalType.DATE)
	private Date DATE_SAISIE;
	@Temporal(TemporalType.DATE)
	private Date DATE_DERN_MODIF;
	
	private String ETAT;
	private String OBSERVATION;
	private String PWD_ENS;
	private String PRENOM_ENS;
	private int CIN;
	private String SEXE_ENS;
	private String ETAT_CIVIL_ENS;
	private String TEL1;
	private String TEL2;
	private String CNSS_ENS;
	@Temporal(TemporalType.DATE)
	private Date DN_ENS;
	@Temporal(TemporalType.DATE)
	private Date DE_ENS;
	@Temporal(TemporalType.DATE)
	private Date DS_ENS;
	
	private int CHEFCOMITE;
	private int COMITE_ID_GRP;
	private int FK_CMT;
	private String TYPE_UP;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="UP")
	private UP UP_code;
	@Temporal(TemporalType.DATE)
	private Date DATE_DEB_UP;
	private String MAIL_ENS;
	private String CODE_GRADE_ENTREE;
	private String LIB_GRADE_ENTREE;
	private String CODE_GRADE_ACTUEL;
	private String LIB_GRADE_ACTUELLE;
	private String ORGANISME;
	private String PWD_INIT;
	
}
