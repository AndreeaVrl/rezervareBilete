package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.IIndexHelper;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Service
@Lazy
public class IndexHelper implements IIndexHelper {

	private final ITaraDAO taraDAO;
	private final IAeroportDAO aeroportDAO;
	
	@Autowired
	public IndexHelper(final ITaraDAO taraDAO,final IAeroportDAO aeroportDAO){
		this.taraDAO = taraDAO;
		this.aeroportDAO = aeroportDAO;
	}
	
	@Override
	public ModelAndView goToIndexPage(final ModelAndView model, final HttpServletRequest request){
		System.out.println("\nENTER FromToHelper -> chooseCountry() <-\n");
		final HttpSession session = request.getSession();
		try{
			List<Tara> tari = new ArrayList<>();
			tari = taraDAO.getAllCountrys();
			model.addObject("tari",tari);
			session.removeAttribute("tari");
			session.setAttribute("tari", tari);
			session.removeAttribute("zboruriCautareRetur");
			session.removeAttribute("zboruriCautare");
			model.addObject("cursa", new CursaRequestView());
			model.addObject("flightChosen",new FlightChosenRequestBean());
			model.setViewName("index");
		}catch(final Exception exc){
			System.out.println("\n"+exc.toString()+" "+exc.getMessage()+"\n");
		}
		return model;
	}
	@Override
	public List<Aeroport> getAirports(final Integer id,final ModelAndView model, final HttpServletRequest request){
		System.out.println("\nENTER FromToHelper getAirports() with id=["+id+"]\n");
		List<Aeroport> aeroporturi = new ArrayList<>();
		try{
			aeroporturi = aeroportDAO.getAirportsByCountryId(id);
			System.out.println("aeroporturi = ["+aeroporturi+"]");
		}catch(final Exception exc){
			System.out.println("\n"+exc.toString()+" "+exc.getMessage()+"\n");
		}
		return aeroporturi;
	}
}
