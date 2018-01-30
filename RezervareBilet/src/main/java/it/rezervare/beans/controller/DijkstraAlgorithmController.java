package it.rezervare.beans.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	public ModelAndView getRout(final ModelAndView model, @ModelAttribute final CursaRequestView cursa, final HttpServletRequest request) {
		try {
			System.out.println("\n ENTER DijkstraAlgorithmController with cursa = ["+ cursa.toString() +"]\n");
			dijkstraAlgorithmHelper.getRoutWithDijkstraAlgorithm(model, cursa,request);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
 