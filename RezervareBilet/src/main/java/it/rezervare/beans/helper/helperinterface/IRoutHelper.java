package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.requestBeans.CursaRequestView;

public interface IRoutHelper {
	
	ModelAndView getRout(ModelAndView model, CursaRequestView cursaRequestView, HttpServletRequest request);

}
