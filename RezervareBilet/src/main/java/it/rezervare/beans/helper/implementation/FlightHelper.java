package it.rezervare.beans.helper.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IFlightHelper;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Service
@Lazy
public class FlightHelper implements IFlightHelper {
	
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView getSelectedFlight(final ModelAndView model, @ModelAttribute("flightChosen") final FlightChosenRequestBean flight, final HttpServletRequest request) {
		System.out.println("\n ENTER FlightHelper.getSelectedFlight() \n");
		try {
			System.out.println("flight = ["+flight+"]");
			final HttpSession session = request.getSession();
			final CursaRequestView cursa = (CursaRequestView) session.getAttribute("cursaRequestView");
			System.out.println("cursa="+cursa);
			Map<Integer,LinkedList<List<Zbor>>> mapZboruriPlecare = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("allRoutesMap");
			if(CollectionUtils.isEmpty(mapZboruriPlecare)) {
				mapZboruriPlecare = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("zboruriCautare");
				model.addObject("zboruriCautare", mapZboruriPlecare);
			}else {
				model.addObject("allRoutesMap", mapZboruriPlecare);
			}
			System.out.println("mapZboruriPlecare = ["+mapZboruriPlecare+"]");
			Map<Integer,LinkedList<List<Zbor>>> mapZboruriRetur = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("mapZboruriRetur");
			if(CollectionUtils.isEmpty(mapZboruriRetur)) {
				mapZboruriRetur = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("zboruriCautareRetur");
				model.addObject("zboruriCautareRetur", mapZboruriRetur);
			} else {
				model.addObject("mapZboruriRetur", mapZboruriRetur);
			}
			System.out.println("mapZboruriRetur = ["+mapZboruriRetur+"]");
			session.setAttribute("flag",session.getAttribute("flag"));
	        model.addObject("cursa", cursa);
	        model.addObject("flightChosen",flight);
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie", e.getMessage());
		} 
		System.out.println("\n EXIT FlightHelper.getSelectedFlight() \n");
		model.setViewName("index");
		return model;
	}

}
