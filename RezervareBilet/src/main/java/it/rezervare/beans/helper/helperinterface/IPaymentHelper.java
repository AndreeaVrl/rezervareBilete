package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;

public interface IPaymentHelper {

	ModelAndView createPayment(ModelAndView model, ModalitatePlata modalitatePlata, HttpServletRequest request);

}
