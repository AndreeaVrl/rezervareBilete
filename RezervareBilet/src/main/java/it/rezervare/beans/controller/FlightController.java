package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IFlightHelper;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Controller
public class FlightController {
	
	private final IFlightHelper flightHelper;
	@Autowired
	public FlightController(final IFlightHelper flightHelper) {
		this.flightHelper = flightHelper;
	}
	@RequestMapping(value = { "/getChosenFlight" }, method = { RequestMethod.POST})
	public ModelAndView getSelectedFlight(final ModelAndView model, @ModelAttribute("flightChosen") final FlightChosenRequestBean flight, final HttpServletRequest request) {
		System.out.println("\n ENTER FlightController.getSelectedFlight() with chosen=["+flight+"]");
		return flightHelper.getSelectedFlight(model, flight, request);
	}

}
