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
import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class IndexController {
	private IFromToHelper fromToRoute;
	private IAdminHelper adminHelper;

	@Autowired
	public IndexController(IFromToHelper fromToRoute, IAdminHelper adminHelper) {
		this.fromToRoute = fromToRoute;
		this.adminHelper = adminHelper;
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public ModelAndView login(ModelAndView model) {
		System.out.println("\n ENTER IndexController \n");
		model.addObject("cursa", new CursaRequestView());
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = { "/goToAdminPage" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView goToAdmin(@ModelAttribute("tara") Tara tara, ModelAndView model, HttpServletRequest request) {
		System.out.println("\n ENTER goToAdminPage \n");
		return adminHelper.loadAdminPage(model, request);
	}

	@RequestMapping(value = { "/index" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView chooseCountry(ModelAndView model, @ModelAttribute("tara") Tara tara,
			HttpServletRequest request) {
		return fromToRoute.chooseCountry(model, tara, request);
	}
}
