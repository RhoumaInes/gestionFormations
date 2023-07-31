package tn.esprit.asi.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ESP_DEPT")
public class Departement {
	@Id
	@Column(name = "CODE_DEPT")
	private String id;
	private String LIBELLE_DEPT;
	@Temporal(TemporalType.DATE)
	private Date DATE_CR;
	@Temporal(TemporalType.DATE)
	private Date DATE_DERNIER_MODIF;
	private String CODE_TRIE;
	/*@OneToMany(cascade = CascadeType.ALL , mappedBy = "depat")
	private Set<UP> listUp;*/
	
}
