package tn.esprit.asi.controls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.asi.entities.Formateur;
import tn.esprit.asi.services.FormateurService;

@RestController
@Api(tags = "Gestion des formateurs")
@RequestMapping("/formateur")
public class FormateurController {
	@Autowired
	FormateurService formateurService;
	
	@PostMapping("/add-formateur")
	@ResponseBody
	public Formateur addEvent(@RequestBody Formateur f)
	{
		return formateurService.addFormateur(f);
	}
	
	@PutMapping("/edit-formateur")
	@ResponseBody
	public Formateur editFormateur(@RequestBody Formateur f)
	{
		return formateurService.addFormateur(f);
	}
	
	@GetMapping("/liste-formateur")
	@ResponseBody
	public List<Formateur> listingForm() {
		return formateurService.listeAllFormateur();
	}
	

}
