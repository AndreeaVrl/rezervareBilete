package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IRoutHelper;
import it.rezervare.beans.model.Graph;
import it.rezervare.beans.model.Route;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.AeroportAjaxView;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Service
@Lazy
public class RoutHelper implements IRoutHelper{

	private final ICursaDAO cursaDAO;
	private final IZborDAO zborDAO; 
	private final IAeroportDAO aeroportDAO;
	@Autowired
	public RoutHelper(final ICursaDAO cursaDAO, final IZborDAO zborDAO, final IAeroportDAO aeroportDAO) {
		this.cursaDAO = cursaDAO;
		this.zborDAO = zborDAO;
		this.aeroportDAO = aeroportDAO;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ModelAndView getRout(final ModelAndView model, final CursaRequestView cursaRequestView, final HttpServletRequest request) {
		System.out.println("\n ENTER RoutHelper.getRout() retur = ["+cursaRequestView.getRetur()+"]\n");
		final HttpSession session = request.getSession();
		session.removeAttribute("allRoutesMap");
		session.removeAttribute("cursaRequestView");
		session.removeAttribute("mapZboruriRetur");
		session.removeAttribute("zboruriCautareRetur");
		session.removeAttribute("zboruriCautare");
		session.setAttribute("cursaRequestView", cursaRequestView);
		try {
			
			if(cursaRequestView == null || StringUtils.isEmpty(cursaRequestView.getContryFrom()) || StringUtils.isEmpty(cursaRequestView.getCountryTo())
					||cursaRequestView.getAirportFrom() == null || cursaRequestView.getAirportTo() == null ) {
				throw new ApplicationException("Completati zborul dorit!");
			}
			final List<Tara> tari = (List<Tara>) session.getAttribute("tari");
			model.addObject("tari",tari);
			model.addObject("cursa", cursaRequestView);
			session.removeAttribute("cursaRequestView");
			session.setAttribute("cursaRequestView", cursaRequestView);
			model.addObject("flightChosen",new FlightChosenRequestBean());
			model.setViewName("index");
			final Aeroport aeroportFrom = aeroportDAO.getAirportById(cursaRequestView.getAirportFrom());
			final AeroportAjaxView aeroportAjaxViewFrom = new AeroportAjaxView();
			aeroportAjaxViewFrom.setId(aeroportFrom.getId());
			aeroportAjaxViewFrom.setDenumire(aeroportFrom.getDenumire());
			model.addObject("aeroportFrom",aeroportAjaxViewFrom);
			final Aeroport aeroportTo = aeroportDAO.getAirportById(cursaRequestView.getAirportTo());
			final AeroportAjaxView aeroportAjaxViewTo = new AeroportAjaxView();
			aeroportAjaxViewTo.setId(aeroportTo.getId());
			aeroportAjaxViewTo.setDenumire(aeroportTo.getDenumire());
			model.addObject("aeroportTo",aeroportAjaxViewTo);
			model.addObject("flag", "1");
			//TUR
	        final List<LinkedList<Integer>> cursePlecareList = getAllRoutes(cursaRequestView.getAirportFrom(), cursaRequestView.getAirportTo());
	        final Map<Integer,LinkedList<List<Zbor>>> mapZboruriPlecare = getAllFlights(cursePlecareList,cursaRequestView.getDepartureDate());
	        model.addObject("zboruriCautare", mapZboruriPlecare);
	        session.removeAttribute("zboruriCautare");
	        session.setAttribute("zboruriCautare", mapZboruriPlecare);
	        //RETUR
	        session.removeAttribute("zboruriCautareRetur");
	        if(cursaRequestView.getRetur()) {
	        	final List<LinkedList<Integer>> curseReturList = getAllRoutes(cursaRequestView.getAirportTo(),cursaRequestView.getAirportFrom());
		        final Map<Integer,LinkedList<List<Zbor>>> mapZboruriRetur = getAllFlights(curseReturList,cursaRequestView.getFlyBack());
		        model.addObject("zboruriCautareRetur", mapZboruriRetur);
		        session.setAttribute("zboruriCautareRetur", mapZboruriRetur);
	        }
	        
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie", e.getMessage());
		} 
		System.out.println("\n EXIT RoutHelper.getRout() \n");
        return model;
	}

	private List<LinkedList<Integer>> getAllRoutes(final Integer airportFrom, final Integer airportTo) throws ApplicationException {
		System.out.println("\n ENTER getAllRoutes \n");
		final List<LinkedList<Integer>> curseList = new ArrayList<LinkedList<Integer>>();
		try {
			final Graph graph = new Graph();
			final List<Cursa> allRaces = cursaDAO.getAll();
			for(final Cursa cursaInLista : allRaces) {
				graph.addEdge(cursaInLista.getAeroport_1().getDenumire(), cursaInLista.getAeroport_2().getDenumire());
			}
			System.out.println("\n graph = ["+ graph +"] \n");
			final LinkedList<String> visited = new LinkedList<>();
	        visited.add(aeroportDAO.getAirportById(airportFrom).getDenumire());
	        System.out.println("From=["+airportFrom+"] to = ["+airportTo+"]");
	        final List<LinkedList<String>> routes = new Route().depthFirst(graph, visited,aeroportDAO.getAirportById(airportTo).getDenumire());
	        System.out.println("\n routes = ["+ routes +"] \n");
	        for(final LinkedList<String> ruta : routes) {
	        	final LinkedList<Integer> curseLinkedList = new LinkedList<>();
	        	for (int i=0; i< ruta.size(); i++) {
	        		if((i+1) < ruta.size()) {
	        			final Cursa cursa = cursaDAO.getRouteByAirport(ruta.get(i), ruta.get(i+1));
	        			curseLinkedList.add(cursa.getId());
	        		}
	        	}
	        	curseList.add(curseLinkedList);
	        }
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("\n EXIT getAllRoutes with curseList = ["+curseList+"] \n");
		return curseList;
	}
	
	private Map<Integer,LinkedList<List<Zbor>>> getAllFlights (final List<LinkedList<Integer>> curseList, final Date date) throws ApplicationException {
		System.out.println("\n ENTER getAllFlights() for date = ["+date+"] \n");
        final Map<Integer,LinkedList<List<Zbor>>> mapZboruri = new HashMap<>();
        try {
	        Integer key = 1;
	        for(final LinkedList<Integer> cursaLista : curseList) {
	        	final LinkedList<List<Zbor>> zborGasit = new LinkedList<>();
	        	for(final Integer idCursa : cursaLista) {
	        		//data plecare sau sosire
	        		final List<Zbor> listaZboruri = zborDAO.getFlightList(idCursa,date);
	        		zborGasit.add(listaZboruri);
	        	}
	        	mapZboruri.put(key, zborGasit);
	        	key++;
	        }
	        for(final Map.Entry<Integer,LinkedList<List<Zbor>>> entry : mapZboruri.entrySet()) {
	        	if(CollectionUtils.isEmpty(entry.getValue())) {
		        	throw new ApplicationException();
		        } else {
		        	for (final List<Zbor> listaZboruri : entry.getValue()) {
		        		if(CollectionUtils.isEmpty(listaZboruri)) {
				        	throw new ApplicationException();
				        }
		        	}
		        }
		        
	        }
        } catch (final Exception e) {
        	throw new ApplicationException(e.getMessage());
        }
        System.out.println("\n EXIT getAllFlights() \n");
		return mapZboruri;
	}
}
