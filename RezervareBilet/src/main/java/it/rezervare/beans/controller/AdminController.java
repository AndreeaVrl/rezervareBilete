package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;

@Controller
public class AdminController {

	private static IAdminHelper adminHelper;

	@Autowired
	public AdminController(IAdminHelper adminHelper) {
		AdminController.adminHelper = adminHelper;
	}

	@RequestMapping(value = "/addCountry", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addCountry(@ModelAttribute("tara") Tara tara, ModelAndView model, HttpServletRequest request) {
		return adminHelper.addCountry(tara, model, request);
	}


}
