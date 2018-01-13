package it.rezervare.beans.helper.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IRoutHelper;
import it.rezervare.beans.model.Graph;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Service
@Lazy
public class RoutHelper implements IRoutHelper{

	private ICursaDAO cursaDAO;
	private IZborDAO zborDAO; 
	
	@Autowired
	public RoutHelper(ICursaDAO cursaDAO, IZborDAO zborDAO) {
		this.cursaDAO = cursaDAO;
		this.zborDAO = zborDAO;
	}
	
	@Override
	public ModelAndView getRout(ModelAndView model, CursaRequestView cursaRequestView) {
		Graph graph = new Graph();
		/*List<Cursa> allRaces = cursaDAO.getAllFlights();
		for(Cursa cursaInLista : allRaces) {
			graph.addEdge(cursaInLista.getAeroport_1().getDenumire(), cursaInLista.getAeroport_2().getDenumire());
		}
		System.out.println("\n graph = ["+ graph +"] \n");
		LinkedList<String> visited = new LinkedList<>();
        visited.add(cursaRequestView.getAirportFrom());
        System.out.println("From=["+cursaRequestView.getAirportFrom()+"] to = ["+cursaRequestView.getAirportTo()+"]");
        List<LinkedList<String>> routes = new Route().depthFirst(graph, visited,cursaRequestView.getAirportTo());
        System.out.println("\n routes = ["+ routes +"] \n");
        List<LinkedList<Integer>> curseList = new ArrayList<LinkedList<Integer>>();
        for(LinkedList<String> ruta : routes) {
        	LinkedList<Integer> curseLinkedList = new LinkedList<>();
        	for (int i=0; i< ruta.size(); i++) {
        		while((i+1) < ruta.size()) {
        			Cursa cursa = cursaDAO.getRouteByAirport(ruta.get(i), ruta.get(i+1));
        			curseLinkedList.add(cursa.getId());
        		}
        	}
        	curseList.add(curseLinkedList);
        }
        System.out.println(" curseList = ["+ curseList +"] ");
        List<LinkedList<List<Zbor>>> zboruriCautare = new ArrayList<>();
        Map<Integer,List<LinkedList<List<Zbor>>>> mapZboruri = new HashMap<>();
        Integer key = 1;
        for(LinkedList<Integer> cursaLista : curseList) {
        	LinkedList<List<Zbor>> zborGasit = new LinkedList<>();
        	for(Integer idCursa : cursaLista) {
        		List<Zbor> listaZboruri = zborDAO.getFlightList(idCursa);
        		zborGasit.add(listaZboruri);
        	}
        	zboruriCautare.add(zborGasit);
        	mapZboruri.put(key, zboruriCautare);
        	key++;
        }
        System.out.println("map = [" + mapZboruri + "]");
        model.addObject("map", mapZboruri);
        model.addObject("cursa", new CursaRequestView());
        model.setViewName("index");*/
        return model;
	}

}
