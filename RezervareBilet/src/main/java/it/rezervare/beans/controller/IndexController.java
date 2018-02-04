package it.rezervare.beans.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.helper.helperinterface.IIndexHelper;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.AeroportRequestBean;

@Controller
public class IndexController {
	private final IIndexHelper indexHelper;
	private final IAdminHelper adminHelper;

	@Autowired
	public IndexController(final IIndexHelper fromToRoute, final IAdminHelper adminHelper) {
		this.indexHelper = fromToRoute;
		this.adminHelper = adminHelper;
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public ModelAndView login(final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n ENTER IndexController \n");
		indexHelper.goToIndexPage(model, request);
		return model;
	}

	@RequestMapping(value = { "/goToAdminPage" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView goToAdmin(@ModelAttribute("tara") final Tara tara, @ModelAttribute("aeroport") final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n ENTER goToAdminPage \n");
		return adminHelper.loadAdminPage(model, request);
	}

	@RequestMapping(value = "/getAirportForContry", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<Aeroport> getAirports(final Integer id, final ModelAndView model, final HttpServletRequest request) {
		return indexHelper.getAirports(id, model, request);
	}
	@RequestMapping(value = { "/goToLocuri" }, method = { RequestMethod.GET })
	public ModelAndView goToLocuri(final ModelAndView model) {
		model.setViewName("locuri");
		return model;
	}

}
