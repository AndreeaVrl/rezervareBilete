package it.rezervare.beans.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IUserHelper;
import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;

@Controller
public class UserController {
	
	private final IUserHelper userHelper;
	
	@Autowired
	public UserController(final IUserHelper userHelper) {
		this.userHelper = userHelper;
	}

	@RequestMapping(value = { "/provizoriu"}, method = {RequestMethod.GET})
	public ModelAndView goToChangeUserAccount(final ModelAndView model) {
		model.setViewName("provizoriu");
		return model;
	}
	@RequestMapping(value = { "/goToChangeAccount"}, method = {RequestMethod.GET})
	public ModelAndView goToChangeUserAccount(final ModelAndView model, final HttpServletRequest request) {
		return userHelper.goToChangeUserAccountPAge(model, request);
	}
	@RequestMapping(value = { "/changePhoneNumber"}, method = {RequestMethod.POST})
	public ModelAndView changeUserAccount(final ModelAndView model,@ModelAttribute("client") final Client client, final HttpServletRequest request) {
		return userHelper.changeUserAccount(model, client, request);
	}
	@RequestMapping(value = { "/rezervari"}, method = {RequestMethod.GET})
	public ModelAndView reservations(final ModelAndView model, final HttpServletRequest request) {
		model.setViewName("provizoriu");
		return userHelper.reservations(model, request);
	}
	@RequestMapping(value = "/deleteReservation", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String deleteReservation(final Integer id, final ModelAndView model, final HttpServletRequest request) {
		return userHelper.deleteReservation(id, model, request);
	}
	@RequestMapping(value = { "/checkin"}, method = {RequestMethod.GET})
	public ModelAndView checkin(final ModelAndView model, final HttpServletRequest request) {
		model.setViewName("checkin");
		model.addObject("bilet", new Bilet());
		return model;
	}
	@RequestMapping(value = { "/doCheckin"}, method = {RequestMethod.POST})
	public ModelAndView getPDF(final ModelAndView model,@ModelAttribute("bilet") final Bilet bilet, final HttpServletRequest request) {
		return userHelper.doCheckin(model, bilet, request);
	}
	@RequestMapping(value = "download")
	public void downloadReport(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		userHelper.downloadReport(request, response);
	}
}
