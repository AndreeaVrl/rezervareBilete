package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Tara;

public interface IAdminHelper {

	ModelAndView loadAdminPage(ModelAndView model, HttpServletRequest request);

	ModelAndView editCountry(Tara tara, ModelAndView model, HttpServletRequest request);

	Tara addCountry(Tara tara, ModelAndView model, HttpServletRequest request);

}
