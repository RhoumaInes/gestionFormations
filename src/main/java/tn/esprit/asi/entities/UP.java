package tn.esprit.asi.entities;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ESP_DEPT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UP {
	@Id
	@Column(name = "UP")
	private String id;
	
	private String DESIGNANTION;
	@Column(name = "CODE_DEPT")
	private String codedept;
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CODE_DEPT")
	private Departement depat;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "UP_code")
	private Set<Formateur> listFormateurUp;*/

}
