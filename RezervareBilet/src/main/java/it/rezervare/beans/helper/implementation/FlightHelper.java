package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.dao.Interfaces.IBileteDAO;
import it.rezervare.beans.dao.Interfaces.ILocDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IFlightHelper;
import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Loc;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;
import it.rezervare.beans.model.requestBeans.RezervareRequestBean;

@Service
@Lazy
public class FlightHelper implements IFlightHelper {
	
	private final IZborDAO zborDAO;
	private final ILocDAO locDAO;
	private final IBileteDAO bileteDAO;
	
	@Autowired
	public FlightHelper(final IZborDAO zborDAO, final ILocDAO locDAO, final IBileteDAO bileteDAO) {
		this.zborDAO = zborDAO;
		this.locDAO = locDAO;
		this.bileteDAO = bileteDAO; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView getSelectedFlight(final ModelAndView model, @ModelAttribute("flightChosen") final FlightChosenRequestBean flight, final HttpServletRequest request) {
		System.out.println("\n ENTER FlightHelper.getSelectedFlight() with flight = ["+flight+"] \n");
		try {///!Lista cu cursele
			final HttpSession session = request.getSession();
			final Map<Integer,LinkedList<List<Zbor>>> mapZboruriPlecare = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("allRoutesMap");
			final Map<Integer,LinkedList<List<Zbor>>> mapZboruriRetur = (Map<Integer, LinkedList<List<Zbor>>>) session.getAttribute("mapZboruriRetur");
			final Map<String,Integer[][]> locuri = new HashMap<>();
			final List<Zbor> zboruri = new ArrayList<>();
//			final LinkedList<List<Zbor>> zborAles = !CollectionUtils.isEmpty(mapZboruriPlecare) ? mapZboruriPlecare.get(flight.getDeparturFlight()) : new LinkedList<>();
			if(!StringUtils.isEmptyOrWhitespaceOnly(flight.getDepartureCompannies())) {
				final String[] departureChoosenFlight = flight.getDepartureCompannies().split(";") ;
				System.out.println("departureChoosenFlight=["+departureChoosenFlight+"]");
				final List<Zbor> listaZboruri = getListaZboruri(departureChoosenFlight);
				zboruri.addAll(listaZboruri);
				System.out.println("listaZboruri = ["+listaZboruri+"]");
				createPlane(listaZboruri,locuri);
			}
//			final LinkedList<List<Zbor>> zborAlesRetur = !CollectionUtils.isEmpty(mapZboruriPlecare) ? mapZboruriPlecare.get(flight.getReturFlight()) : new LinkedList<>();
			if(!StringUtils.isEmptyOrWhitespaceOnly(flight.getReturnCompannies())) {
				final String[] returnChoosenFlight = flight.getReturnCompannies().split(";");
				System.out.println("returnChoosenFlight=["+returnChoosenFlight+"]");
				final List<Zbor> listaZboruriretur = getListaZboruri(returnChoosenFlight);
				System.out.println("listaZboruriretur = ["+listaZboruriretur+"]");
				zboruri.addAll(listaZboruriretur);
				createPlane(listaZboruriretur,locuri);
			}
			System.out.println("mapLocuri = ["+locuri+"]");
			final Map<String, String> routMap = getRoutMap(zboruri);
			model.addObject("routMap",routMap);
			model.addObject("zboruriCautareRetur", mapZboruriRetur);
			model.addObject("zboruriCautare", mapZboruriPlecare);
			model.addObject("mapAvioane", locuri);
			model.addObject("flight",flight);
			model.addObject("zboruri");
			session.removeAttribute("flight");
			session.setAttribute("flight", flight);
			model.addObject("rezervare", new RezervareRequestBean());
			model.setViewName("locuri");
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie", e.getMessage());
		} 
		System.out.println("\n EXIT FlightHelper.getSelectedFlight() \n");
	//	model.setViewName("index");
		return model;
	}
	private Map<String, String> getRoutMap(final List<Zbor> listaZboruri) {
		final Map<String, String> routMap = new HashMap<>();
		for(final Zbor zbor : listaZboruri) {
			routMap.put(String.valueOf(zbor.getId()), zbor.getCursa().getAeroport_1().getDenumire() + "-" + zbor.getCursa().getAeroport_2().getDenumire());
		}
		return routMap;
	}
	@Transactional
	private List<Zbor> getListaZboruri(final String[] departureArray) throws NumberFormatException, ApplicationException{
		final List<Zbor> listaZboruri = new ArrayList<>();
		for(final String departure : departureArray ) {
			final Zbor zbor = zborDAO.getFlightById(Integer.valueOf(departure));
			Hibernate.initialize(zbor);
			Hibernate.initialize(zbor.getAvion());
			Hibernate.initialize(zbor.getAvion().getTipAvion());
			Hibernate.initialize(zbor.getAvion().getTipAvion().getLocuri());
			listaZboruri.add(zbor);
		}
		return listaZboruri;
	}
	
	private void createPlane(final List<Zbor> listaZboruri,final Map<String,Integer[][]> locuri) throws ApplicationException{
		try {
			final Set<Integer> randuri = new HashSet<>();
			final Set<Integer> coloane = new HashSet<>();
			for(final Zbor zbor : listaZboruri) {
				for(final Loc loc : zbor.getAvion().getTipAvion().getLocuri()) {
					randuri.add(loc.getRand());
					final char col = loc.getColoana().toLowerCase().charAt(0);
					final Integer colNumber = col - 'a' + 1;
					coloane.add(colNumber);
				}
				final Integer nrRanduri = Collections.max(randuri);
				System.out.println("nrRanduri="+nrRanduri);
				final Integer nrColoane = Collections.max(coloane);
				System.out.println("nrColoane="+nrColoane);
				final Integer[][] avion = new Integer[nrRanduri][nrColoane];
				for(int i = 0;i<nrRanduri;i++) {
					System.out.println("i="+i);
					for(int j = 0; j<nrColoane; j++) {
						System.out.println("j="+j);
						final Loc loc= locDAO.getSeatByRowAndColumn((i+1), String.valueOf((char)((j+1) + 64)));
						avion[i][j] = loc.getTipLoc().getId();
						if(!loc.getTipLoc().getId().equals(0)) {
							final Bilet bilet = bileteDAO.getBiletByIdLocAndIdZbor(loc.getId(), zbor.getId());
							if(bilet!= null) {
								avion[i][j] = 4;
							}
						}
					}
				}
				locuri.put(zbor.getId()+";"+nrRanduri+";"+nrColoane, avion);
			}
		} catch(final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionsMessages.GENERIC_ERROR);
		}
	}
}
