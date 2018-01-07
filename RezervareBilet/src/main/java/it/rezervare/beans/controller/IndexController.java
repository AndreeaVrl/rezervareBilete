package it.rezervare.beans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.requestBeans.CursaRequestView;

@Controller
public class IndexController {
	
	@RequestMapping(value = { "/"}, method = {RequestMethod.GET})
	public ModelAndView login(ModelAndView model){
		System.out.println("\n ENTER IndexController \n");
		model.addObject("cursa", new CursaRequestView());
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = { "/goToAdminPage"}, method = {RequestMethod.GET})
	public ModelAndView goToAdmin(ModelAndView model){
		System.out.println("\n ENTER goToAdminPage \n");
		model.setViewName("admin");
		return model;
	}
}