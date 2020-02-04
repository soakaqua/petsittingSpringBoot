package petSitting.frontBoot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Annonce;
import model.Compte;
import model.Reponse;
import model.ReponsePK;
import model.Sitter;
import repositories.AnnonceRepository;
import repositories.CompteRepository;
import repositories.ReponseRepository;
import services.ReponseService;
import services.SitterService;

@Controller
public class SitterController2 {

	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	AnnonceRepository annonceRepository;
	
	@Autowired
	SitterService sitterService;
	
	@Autowired
	ReponseRepository reponseRepository;
	
	@Autowired
	ReponseService reponseService;
	

	@GetMapping("/sitter/afficherAnnoncesBySitter")
	public String menuProprio(@RequestParam(name="numC") Integer numC, Model model) {
		model.addAttribute("annonces", annonceRepository.selectAnnonceBySitter(numC));

		model.addAttribute("reponse", sitterService.afficherReponseBySitterAndAnnonce(numC));
		return "/sitter/afficherAnnoncesBySitter" ;	
		}

	
	@GetMapping("/sitter/delete")
	public String deleteReponse(@RequestParam(name="numA") Integer numA, @RequestParam(name="numC") Integer numC, Model model) {
		Optional<Compte> opt1 = compteRepository.findById(numC);
		Optional<Annonce> opt2 = annonceRepository.findById(numA);	
		ReponsePK pk = new ReponsePK((Sitter) opt1.get(), opt2.get());
		reponseRepository.deleteById(pk);
		model.addAttribute("numC", pk.getSitter().getNumC());
		return "redirect:/sitter/afficherAnnoncesBySitter";
	}
	
	@GetMapping("/sitter/editReponse")
	public String editReponse(@RequestParam(name="numA") Integer numA, @RequestParam(name="numC") Integer numC, Model model) {
		
		Optional<Compte> opt1 = compteRepository.findById(numC);
		Optional<Annonce> opt2 = annonceRepository.findById(numA);	
		ReponsePK pk = new ReponsePK((Sitter) opt1.get(), opt2.get());
		
		
		model.addAttribute("reponse", reponseRepository.findById(pk).get());
		model.addAttribute("annonce", opt2.get());
		return "/sitter/editReponse";
	}
	
	@GetMapping("/sitter/saveReponse")
	public String saveReponse(@ModelAttribute("reponse") Reponse reponse, Model model) {
		
		reponseService.save(reponse);
		model.addAttribute("numC", reponse.getKey().getSitter().getNumC());
		return "redirect:/sitter/afficherAnnoncesBySitter";
		
	}
	
}
