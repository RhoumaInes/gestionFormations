package tn.esprit.asi.reposetories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.asi.entities.Formateur;

@Repository
public interface FormateurRep extends CrudRepository<Formateur,String> {
	
	@Query("SELECT f FROM Formateur f WHERE f.MAIL_ENS= :mail")
	Optional<Formateur> findByLogin(@Param("mail") String mail);

}
