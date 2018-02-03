package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.requestBeans.PassengersRequestView;
import it.rezervare.beans.model.requestBeans.RezervareRequestBean;

public interface IRezervareHelper {

	ModelAndView goToPassengerData(ModelAndView model, RezervareRequestBean flight, HttpServletRequest request);

	ModelAndView goToBillingData(ModelAndView model, PassengersRequestView pasageri, HttpServletRequest request);

	ModelAndView goPaymentPage(ModelAndView model, Client client, HttpServletRequest request);

}
