package petSitting.frontBoot.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Compte;
import model.Proprio;
import model.Sitter;
import repositories.CompteRepository;

@Controller
public class MenuController {
	
	@Autowired
	CompteRepository compteRepository;
	
	@GetMapping("/menu")
	public ModelAndView menuProprio(HttpSession session) {
		Compte proprio = new Compte();
		Optional<Compte> opt = compteRepository.findById(101);
		if(opt.isPresent()) {
			proprio= opt.get();
		}
		session.setAttribute("numC", proprio.getNumC());

		return new ModelAndView("menu", "compte", (Proprio) proprio );
	}
	
//	@GetMapping("/menu")
//	public ModelAndView menuSitter(HttpSession session) {
//		Compte proprio = new Compte();
//		Optional<Compte> opt = compteRepository.findById(100);
//		if(opt.isPresent()) {
//			proprio= opt.get();
//		}
//		session.setAttribute("numC", proprio.getNumC());
//		return new ModelAndView("menu", "compte", (Sitter) proprio );
//	}
	
	@GetMapping("/accueil")
	public String Accueil() {

		return "accueil";
	}
	
}
