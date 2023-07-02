package tn.esprit.asi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.asi.entities.Formateur;
import tn.esprit.asi.reposetories.FormateurRep;

@Service
public class FormateurService {
	
	@Autowired
	FormateurRep formateurRep;
	
	public List<Formateur> listeAllFormateur() {
		return (List<Formateur>) formateurRep.findAll();
	}
	
	
	public Formateur addFormateur(Formateur f) {
		f.setDATE_DERN_MODIF(new Date());
		return formateurRep.save(f);
	}
	
	public List<Formateur> listFormateurs() {
		return (List<Formateur>) formateurRep.findAll();
	}

}
