package it.rezervare.beans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IRoutHelper;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class RouteController {
	
	private IRoutHelper routHelper;
	
	@Autowired
	public RouteController(IRoutHelper routHelper) {
		this.routHelper = routHelper;
	}

//	@RequestMapping(value = { "/getRoute"}, method = {RequestMethod.POST})
	public ModelAndView getRout(ModelAndView model, @ModelAttribute CursaRequestView cursa){
		try {
			System.out.println("\n ENTER RouteController wuth cursa = ["+ cursa.toString() +"]\n");
			routHelper.getRout(model, cursa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
}
