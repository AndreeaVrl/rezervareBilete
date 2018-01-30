package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.helper.helperinterface.IFromToHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.AeroportRequestBean;
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class IndexController {
	private final IFromToHelper fromToRoute;
	private final IAdminHelper adminHelper;

	@Autowired
	public IndexController(final IFromToHelper fromToRoute, final IAdminHelper adminHelper) {
		this.fromToRoute = fromToRoute;
		this.adminHelper = adminHelper;
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public ModelAndView login(final ModelAndView model) {
		System.out.println("\n ENTER IndexController \n");
		model.addObject("cursa", new CursaRequestView());
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = { "/goToAdminPage" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView goToAdmin(@ModelAttribute("tara") final Tara tara, @ModelAttribute("aeroport") final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n ENTER goToAdminPage \n");
		return adminHelper.loadAdminPage(model, request);
	}

	@RequestMapping(value = { "/index" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView chooseCountry(final ModelAndView model, @ModelAttribute("tara") final Tara tara,
			final HttpServletRequest request) {
		return fromToRoute.chooseCountry(model, tara, request);
	}

	@RequestMapping(value = { "/goToLocuri" }, method = { RequestMethod.GET })
	public ModelAndView goToLocuri(final ModelAndView model) {
		model.setViewName("locuri");
		return model;
	}

}
