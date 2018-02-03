package it.rezervare.beans.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IRezervareHelper;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.requestBeans.PassengersRequestView;
import it.rezervare.beans.model.requestBeans.RezervareRequestBean;

@Controller
public class ReserveController {

	private final IRezervareHelper reserveHelper;
	
	@Autowired
	public ReserveController(final IRezervareHelper reserveHelper) {
		this.reserveHelper = reserveHelper;
	}
	
	@RequestMapping(value = { "/reserve" }, method = { RequestMethod.POST })
	public ModelAndView goToPassengerData(final ModelAndView model, @ModelAttribute("rezervare") final RezervareRequestBean flight, final HttpServletRequest request) {
		return reserveHelper.goToPassengerData(model, flight, request);
	}
	
	@RequestMapping(value = { "/invoicing" }, method = { RequestMethod.POST })
	public ModelAndView goToInvoicing(final ModelAndView model, @ModelAttribute("pasageri") final PassengersRequestView pasageri,final BindingResult bindResult, final HttpServletRequest request) {
		return reserveHelper.goToBillingData(model, pasageri, request);
	}
	@InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	@RequestMapping(value = { "/makingPayment" }, method = { RequestMethod.POST })
	public ModelAndView goPaymentPage(final ModelAndView model, @ModelAttribute("factura") final Client client, final BindingResult bindResult, final HttpServletRequest request) {
		System.out.println("\n Enter goPaymentPage() \n");
		return reserveHelper.goPaymentPage(model, client, request);
	}
}
