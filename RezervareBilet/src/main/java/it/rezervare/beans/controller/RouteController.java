package it.rezervare.beans.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IRoutHelper;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class RouteController {
	
	private final IRoutHelper routHelper;
	
	@Autowired
	public RouteController(final IRoutHelper routHelper) {
		this.routHelper = routHelper;
	}

	@RequestMapping(value = { "/getAllRoutes"}, method = {RequestMethod.POST})
	public ModelAndView getRout(final ModelAndView model, @ModelAttribute final CursaRequestView cursa){
		try {
			System.out.println("\n ENTER RouteController wuth cursa = ["+ cursa.toString() +"]\n");
			routHelper.getRout(model, cursa);
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
