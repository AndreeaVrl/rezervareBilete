package it.rezervare.beans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IDijkstraAlgorithmHelper;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class DijkstraAlgorithmController {
	 
	private final IDijkstraAlgorithmHelper dijkstraAlgorithmHelper;	
	
	@Autowired
	public DijkstraAlgorithmController(final IDijkstraAlgorithmHelper dijkstraAlgorithmHelper) {
		this.dijkstraAlgorithmHelper = dijkstraAlgorithmHelper;
	}
	
	@RequestMapping(value = { "/getRoute"}, method = {RequestMethod.POST})
	public ModelAndView getRout(final ModelAndView model, @ModelAttribute final CursaRequestView cursa) {
		try {
			System.out.println("\n ENTER DijkstraAlgorithmController with cursa = ["+ cursa.toString() +"]\n");
			dijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm(model, cursa);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
 