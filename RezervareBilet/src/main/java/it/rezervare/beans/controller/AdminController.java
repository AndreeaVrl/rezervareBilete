package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.AeroportRequestBean;

@Controller
public class AdminController {

	private static IAdminHelper adminHelper;

	@Autowired
	public AdminController(final IAdminHelper adminHelper) {
		AdminController.adminHelper = adminHelper;
	}

	@RequestMapping(value = "/editCountry", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView editCountry(@ModelAttribute("tara") final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.editCountry(tara, model, request);
	}

	@RequestMapping(value = "/addCountry", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Tara addCountry(@ModelAttribute("tara") final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.addCountry(tara, model, request);
	}

	@RequestMapping(value = "/deleteCountry", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Tara deleteCountry(@ModelAttribute("tara") final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.deleteCountry(tara, model, request);
	}
	
	@RequestMapping(value = "/addAirport", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AeroportRequestBean addAirport(@ModelAttribute("aeroport") final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.addAirport(aeroport, model, request);
	}
	
	@RequestMapping(value = "/editAirport", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AeroportRequestBean editAirport(@ModelAttribute("aeroport") final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.editAirport(aeroport, model, request);
	}
	
	@RequestMapping(value = "/deleteAirport", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AeroportRequestBean deleteAirport(@ModelAttribute("aeroport") final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		return adminHelper.deleteAirport(aeroport, model, request);
	}

}
