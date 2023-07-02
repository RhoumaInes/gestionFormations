package tn.esprit.asi.reposetories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Formateur;

@Repository
public interface FormateurRep extends CrudRepository<Formateur,String> {

}
