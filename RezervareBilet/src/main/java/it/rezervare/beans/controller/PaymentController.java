package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.IPaymentHelper;
import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;

@Controller
public class PaymentController {
	
	private final IPaymentHelper paymentHelper;
	
	@Autowired
	public PaymentController(final IPaymentHelper paymentHelper) {
		this.paymentHelper = paymentHelper;
	}

	@RequestMapping(value = { "/payment" }, method = { RequestMethod.POST })
	public ModelAndView createPayment( @ModelAttribute("modalitatePlata") final ModalitatePlata modalitatePlata, final BindingResult result,final ModelAndView model, final HttpServletRequest request){
		System.out.println("\n ENTER PaymentController with modalitatePlata = ["+modalitatePlata+"] \n");
		paymentHelper.createPayment(model, modalitatePlata, request);
		return model;
	}
}
