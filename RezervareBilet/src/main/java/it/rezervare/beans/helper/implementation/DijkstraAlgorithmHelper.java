package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IDijkstraAlgorithmHelper;
import it.rezervare.beans.model.DiGraphs;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Service
@Lazy
public class DijkstraAlgorithmHelper implements IDijkstraAlgorithmHelper {

	private ICursaDAO cursaDAO;
	private IZborDAO zborDAO; 
	
	@Autowired
	public DijkstraAlgorithmHelper(ICursaDAO cursaDAO, IZborDAO zborDAO) {
		this.cursaDAO = cursaDAO;
		this.zborDAO = zborDAO;
	}
	@Override
	public ModelAndView getRoutWithDijkstraAlgorithm(ModelAndView model, CursaRequestView cursa) {
		System.out.println("ENTER DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
		DiGraphs<String> graph = new DiGraphs<String>();
		List<Cursa> allRaces = cursaDAO.getAllFlights();
		System.out.println("allRaces = ["+allRaces+"]");
		for(Cursa cursaInLista : allRaces) {
			graph.add(cursaInLista.getAeroport_1().getDenumire(), cursaInLista.getAeroport_2().getDenumire(), cursaInLista.getDistanta());
		}
		System.out.println("Graph = ["+graph+"]");
		List<String> path = graph.getPath(cursa.getAirportFrom(), cursa.getAirportTo());
		System.out.println(" cursa aferenta cautarii este = ["+path+"]");
		LinkedList<Cursa> curseList = new LinkedList<Cursa>();
    	for (int i=0; i< path.size(); i++) {
    		while((i+1) < path.size()) {
    			Cursa cursaGasita = cursaDAO.getRouteByAirport(path.get(i), path.get(i+1));
    			curseList.add(cursaGasita);
    		}
    	}
    	System.out.println(" curseList = ["+ curseList +"] ");
    	LinkedList<List<Zbor>> zboruriCautare = new LinkedList<>();
        for(Cursa cursainLista : curseList) {
        	LinkedList<List<Zbor>> zborGasit = new LinkedList<>();
    		List<Zbor> listaZboruri = zborDAO.getFlightList(cursainLista.getId());
//    		Zbor zborIeftin = Collections.min(listaZboruri, new ZborComparator());
    		ArrayList<Zbor> min =new  ArrayList<Zbor>();
    		for(Zbor zbor : listaZboruri){
    		    if(min.size()==0 || zbor.getPret() == min.get(0).getPret()) {
    		           min.add(zbor);
    		    }
    		    else if(zbor.getPret().compareTo(min.get(0).getPret()) < 0){
    		           min.clear();
    		           min.add(zbor);
    		    }               
    		}
    		zborGasit.add(listaZboruri);
        }
        System.out.println("zboruriCautare = [" + zboruriCautare + "]");
        
        model.addObject("zborurCautare", zboruriCautare);
        model.addObject("cursa", new CursaRequestView());
        model.setViewName("index");
        System.out.println("EXIT DijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm()");
        return model;
	}

}
