package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.ZborComparator;
import it.rezervare.beans.helper.helperinterface.IDijkstraAlgorithmHelper;
import it.rezervare.beans.model.CursaDijkstraAlgoritm;
import it.rezervare.beans.model.Edge;
import it.rezervare.beans.model.Graphs;
import it.rezervare.beans.model.Node;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
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
	public ModelAndView getRoutWithDijkstraAlgorithm(final ModelAndView model, final CursaRequestView cursa) {
		System.out.println("ENTER DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
		try {
			final List<Node> nodesList = aeroportDAO.getDistinctAireports();
			if (nodesList.isEmpty()) {
				throw new ApplicationException("Ne pare rau, lista aeroporturilor nu a fost actualizata!");
			}
			final Map<Integer, Node> nodesMap = new HashMap<>();
			for(final Node node : nodesList) {
				nodesMap.put(node.getId(), node);
			}
			System.out.println(" nodesMap = ["+nodesMap+"]");
			final List<Edge> edgesList = new ArrayList<>();
			final Node nodeFrom = nodesMap.get(cursa.getAirportFrom());
			final Node nodeTo = nodesMap.get(cursa.getAirportTo());
			if(nodeFrom == null || nodeTo == null ) {
				throw new ApplicationException("Punctele de plecare - sosire nu au fost stablite corect!");
			}
			final List<CursaDijkstraAlgoritm> allRaces = cursaDAO.getAllFlights();
			System.out.println("allRaces = ["+allRaces+"]");
			for(final CursaDijkstraAlgoritm cursaInLista : allRaces) {
				final Edge edge = new Edge(nodesMap.get(cursaInLista.getIdAeroportFrom()), nodesMap.get(cursaInLista.getIdAeroportTo()),cursaInLista.getDistanta());
				edgesList.add(edge);
			}
			if(allRaces.isEmpty() || edgesList.isEmpty()) {
				throw new ApplicationException("Ne pare rau, inca nu gestionam zborul solicitat!");
			}
			System.out.println("Edge = [" + edgesList + "]");
			final Graphs graph = new Graphs(nodesList, edgesList);
			System.out.println("\n graph = ["+graph+"]\n");
	        final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        dijkstra.execute(nodeFrom);
			final LinkedList<Node> path = dijkstra.getPath(nodeTo);
			final LinkedList<Cursa> curseList = new LinkedList<Cursa>();
			if(path != null) {
		    	for (int i=0; i< path.size(); i++) {
		    		if((i+1) < path.size()) {
		    			final Cursa cursaGasita = cursaDAO.getRouteByAirport(path.get(i).getName(), path.get(i+1).getName());
		    			curseList.add(cursaGasita);
		    		}
		    	}
		    	System.out.println(" curseList = ["+ curseList +"] ");
		    	final Map<Integer,List<Zbor>> zborGasit = new HashMap<>();
		    	final Integer key = 1;
		    	final List<Zbor> zborCautat = new LinkedList<>();
		        for(final Cursa cursainLista : curseList) {
		        	final List<Zbor> listaZboruri = zborDAO.getFlightList(cursainLista.getId());
		    		final Zbor zborIeftin = Collections.min(listaZboruri, new ZborComparator());
		    		zborCautat.add(zborIeftin);
		    		/*final ArrayList<Zbor> min =new  ArrayList<Zbor>();
		    		for(final Zbor zbor : listaZboruri){
		    		    if(min.size()==0 || zbor.getPret() == min.get(0).getPret()) {
		    		           min.add(zbor);
		    		    }
		    		    else if(zbor.getPret().compareTo(min.get(0).getPret()) < 0){
		    		           min.clear();
		    		           min.add(zbor);
		    		    }               
		    		}*/
		        }
		        zborGasit.put(key,zborCautat);
		        System.out.println("zboruriCautare = [" + zborGasit + "]");
		        model.addObject("zboruriCautare", zborGasit);
		        model.addObject("cursa", new CursaRequestView());
				model.setViewName("index");
				System.out.println("PARCURGERE LISTA");
				for (final Map.Entry<Integer,List<Zbor>> zbor : zborGasit.entrySet()) {
					int nrListe = 1;
					for(final Zbor zborCautare : zbor.getValue()) {
						if(zbor.getValue().indexOf(zborCautare) == 0) {
							System.out.println("cautare:"+zborCautare.getCursa().getAeroport_1().getDenumire()
									+"-"+zborCautare.getCursa().getAeroport_2().getDenumire());
							nrListe++;
						} else {
							if(nrListe > 1) {
								System.out.println("-"+zborCautare.getCursa().getAeroport_2().getDenumire());
							}
						}
					}
				}
			} else {
				throw new ApplicationException("Ne pare rau, inca gestionam zborul solicitat!");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("cursa", new CursaRequestView());
			model.setViewName("index");
			model.addObject("exceptie", new ApplicationException(e.getMessage()));
			
		}
	        System.out.println("EXIT DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
        return model;
	}

}
