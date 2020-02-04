package petSitting.frontBoot.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //explique que cette classe est un bean à charger qui contient de la config
public class CustomWebMcvConfigurer implements WebMvcConfigurer {

	//CONFIGURATION DES LANGUES
	
	@Override //gère dans les url le paramètre "lang" pour changer la langue
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean //choix de la langue par défaut. Définit la gestion de la langue, objet dans lequel on définit la langue
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr=new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRENCH);
		return slr;
	}
	
	@Bean //objet qui gère un paramètre qu'on utilise pour modifier la langue
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
}

