package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

public interface IFlightHelper {

	ModelAndView getSelectedFlight(ModelAndView model, FlightChosenRequestBean flight, HttpServletRequest request);

}
