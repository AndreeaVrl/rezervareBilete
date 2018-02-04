package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.AdminRequestBean;

public interface IAdminHelper {

	ModelAndView loadAdminPage(ModelAndView model, HttpServletRequest request);

	ModelAndView editCountry(Tara tara, ModelAndView model, HttpServletRequest request);

	Tara addCountry(Tara tara, ModelAndView model, HttpServletRequest request);

	Tara deleteCountry(Tara tara, ModelAndView model, HttpServletRequest request);

	AdminRequestBean addAirport(AdminRequestBean aeroport, ModelAndView model, HttpServletRequest request);

	AdminRequestBean editAirport(AdminRequestBean aeroport, ModelAndView model, HttpServletRequest request);

	AdminRequestBean deleteAirport(AdminRequestBean aeroport, ModelAndView model, HttpServletRequest request);

	AdminRequestBean addRoute(AdminRequestBean routeBean, ModelAndView model, HttpServletRequest request);

	AdminRequestBean deleteRoute(AdminRequestBean adminRequestBean, ModelAndView model, HttpServletRequest request);

	AdminRequestBean addFlight(AdminRequestBean adminRequestBean, ModelAndView model, HttpServletRequest request);

	AdminRequestBean editFlight(AdminRequestBean adminRequestBean, ModelAndView model, HttpServletRequest request);

}
