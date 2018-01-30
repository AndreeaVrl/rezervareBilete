package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.IFromToHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Service
@Lazy
public class FromToHelper implements IFromToHelper {

	private final ITaraDAO taraDAO;
	
	@Autowired
	public FromToHelper(final ITaraDAO taraDAO){
		this.taraDAO = taraDAO;
	}
	
	@Override
	public ModelAndView chooseCountry(final ModelAndView model, @ModelAttribute("tara") final Tara tara, final HttpServletRequest request){
		System.out.println("\nENTER FromToHelper -> chooseCountry() <-\n");
		final HttpSession session = request.getSession();
		try{
			List<Tara> tari = new ArrayList<>();
			tari = taraDAO.getAllCountrys();
			model.addObject("tari",tari);
			model.addObject("flightChosen",new FlightChosenRequestBean());
			model.setViewName("index");
		}catch(final Exception exc){
			System.out.println("\n"+exc.toString()+" "+exc.getMessage()+"\n");
		}
		return model;
	}
}
