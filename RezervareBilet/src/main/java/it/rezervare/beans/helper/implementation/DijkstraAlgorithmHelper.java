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
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IDijkstraAlgorithmHelper;
import it.rezervare.beans.model.CursaDijkstraAlgoritm;
import it.rezervare.beans.model.Edge;
import it.rezervare.beans.model.Graphs;
import it.rezervare.beans.model.Node;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;
import it.rezervare.beans.utils.DijkstraAlgorithm;

@Service
@Lazy
public class DijkstraAlgorithmHelper implements IDijkstraAlgorithmHelper {

	private final ICursaDAO cursaDAO;
	private final IZborDAO zborDAO; 
	private final IAeroportDAO aeroportDAO;
	
	@Autowired
	public DijkstraAlgorithmHelper(final ICursaDAO cursaDAO, final IZborDAO zborDAO, 
			final IAeroportDAO aeroportDAO) {
		this.cursaDAO = cursaDAO;
		this.zborDAO = zborDAO;
		this.aeroportDAO = aeroportDAO;
	}
	@Override
	public ModelAndView getRoutWithDijkstraAlgorithm(final ModelAndView model, final CursaRequestView cursaRequestView, final HttpServletRequest request) {
		System.out.println("ENTER DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
		try {
			/*final Aeroport aeroportPlecare = aeroportDAO.getAirportById(cursaRequestView.getAirportFrom());
			final Aeroport aeroposrtSosire = aeroportDAO.getAirportById(cursaRequestView.getAirportTo());
			final Cursa cursa = cursaDAO.getRouteByAirport(aeroportPlecare.getDenumire(), aeroposrtSosire.getDenumire());
			if(cursa != null && !StringUtils.isEmpty(cursa.getId())) {
				final Map<Integer,LinkedList<List<Zbor>>> zborGasit = new HashMap<>();
				final LinkedList<List<Zbor>> linkedList = new LinkedList<>();
				final List<Zbor> listaZboruri = zborDAO.getFlightList(cursa.getId(), cursaRequestView.getDepartureDate());
				linkedList.add(listaZboruri);
				zborGasit.put(1, linkedList);
				model.addObject("zboruriCautare", zborGasit);
			} else {
				
			}*/
			final HttpSession session = request.getSession();
			session.removeAttribute("allRoutesMap");
			session.removeAttribute("cursaRequestView");
			session.removeAttribute("mapZboruriRetur");
			session.setAttribute("cursaRequestView", cursaRequestView);
			
			model.addObject("flightChosen",new FlightChosenRequestBean());
			final List<Node> nodesList = aeroportDAO.getDistinctAireports();
			if (nodesList.isEmpty()) {
				throw new ApplicationException("Ne pare rau, lista aeroporturilor nu a fost actualizata!");
			}
			final Map<Integer, Node> nodesMap = new HashMap<>();
			for(final Node node : nodesList) {
				nodesMap.put(node.getId(), node);
			}
			final List<Edge> edgesList = new ArrayList<>();
			final Node nodeFrom = nodesMap.get(cursaRequestView.getAirportFrom());
			final Node nodeTo = nodesMap.get(cursaRequestView.getAirportTo());
			if(nodeFrom == null || nodeTo == null ) {
				throw new ApplicationException("Punctele de plecare - sosire nu au fost stablite corect!");
			}
			final List<CursaDijkstraAlgoritm> allRaces = cursaDAO.getAllFlights();
			for(final CursaDijkstraAlgoritm cursaInLista : allRaces) {
				final Edge edge = new Edge(nodesMap.get(cursaInLista.getIdAeroportFrom()), nodesMap.get(cursaInLista.getIdAeroportTo()),cursaInLista.getDistanta());
				edgesList.add(edge);
			}
			if(allRaces.isEmpty() || edgesList.isEmpty()) {
				throw new ApplicationException();
			}
			final Graphs graph = new Graphs(nodesList, edgesList);
	        final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        
	        //cautare curse plecare
			final LinkedList<Cursa> curseList = dijkstraGetRoutes(dijkstra,nodeFrom,nodeTo);
	    	final Map<Integer,LinkedList<List<Zbor>>> zborGasit = dijkstraGetRoutesFlight(curseList, cursaRequestView.getDepartureDate());
	    	model.addObject("zboruriCautare", zborGasit);
	    	session.removeAttribute("zboruriCautare");
	    	session.setAttribute("zboruriCautare", zborGasit);

	    	//curse retur
	    	session.removeAttribute("zboruriCautareRetur");
			if(cursaRequestView.getRetur()) {
				final LinkedList<Cursa> curseListRetur = dijkstraGetRoutes(dijkstra,nodeTo,nodeFrom);
		    	final Map<Integer,LinkedList<List<Zbor>>> zborGasitRetur = dijkstraGetRoutesFlight(curseListRetur, cursaRequestView.getFlyBack());
		    	model.addObject("zboruriCautareRetur", zborGasitRetur);
		    	session.setAttribute("zboruriCautareRetur", zborGasitRetur);
			}
			model.addObject("flag", "0");
	    	session.removeAttribute("flag");
	    	session.setAttribute("flag", "0");
			model.addObject("cursa", cursaRequestView);
			model.setViewName("index");
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("cursa", cursaRequestView);
			model.setViewName("index");
			model.addObject("exceptie", e.getMessage());
			
		}
	        System.out.println("EXIT DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
        return model;
	}
	
	private LinkedList<Cursa> dijkstraGetRoutes(final DijkstraAlgorithm dijkstra, final Node nodeFrom, final Node nodeTo) throws ApplicationException {
		System.out.println("\n ENTER DijkstraAlgorithmHelper.dijkstraGetRoutes() \n");
		dijkstra.execute(nodeFrom);
		final LinkedList<Node> path = dijkstra.getPath(nodeTo);
		final LinkedList<Cursa> curseList = new LinkedList<Cursa>();
		try {
		if(path == null) {
			throw new ApplicationException();
		}
    	for (int i=0; i< path.size(); i++) {
    		if((i+1) < path.size()) {
    			final Cursa cursaGasita = cursaDAO.getRouteByAirport(path.get(i).getName(), path.get(i+1).getName());
    			curseList.add(cursaGasita);
    		}
    	}
		} catch (final Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("\n Exit DijkstraAlgorithmHelper.dijkstraGetRoutes() \n");
		return curseList;
	}
	
	private Map<Integer,LinkedList<List<Zbor>>> dijkstraGetRoutesFlight(final LinkedList<Cursa> curseList, final Date date) throws ApplicationException{
		System.out.println("\n ENTER DijkstraAlgorithmHelper.dijkstraGetRoutesFlight() \n");
		final Map<Integer,LinkedList<List<Zbor>>> zborGasit = new HashMap<>();
    	final Integer key = 1;
    	final LinkedList<List<Zbor>> zborCautat = new LinkedList<>();
        for(final Cursa cursainLista : curseList) {
        	final List<Zbor> listaZboruri = zborDAO.getFlightList(cursainLista.getId(), date);
        	if(CollectionUtils.isEmpty(listaZboruri)) {
        		throw new ApplicationException();
        	}
    		//final Zbor zborIeftin = Collections.min(listaZboruri, new ZborComparator());
        	final ArrayList<Zbor> min =new  ArrayList<Zbor>();
    		for(final Zbor zbor : listaZboruri){
    		    if(min.size()==0 || zbor.getPret() == min.get(0).getPret()) {
    		           min.add(zbor);
    		    }
    		    else if(zbor.getPret().compareTo(min.get(0).getPret()) < 0){
    		           min.clear();
    		           min.add(zbor);
    		    }               
    		}
    		zborCautat.add(min);
        }
        
        zborGasit.put(key,zborCautat);
		System.out.println("\n EXIT DijkstraAlgorithmHelper.dijkstraGetRoutesFlight() \n");
		return zborGasit;
	}
	
}
