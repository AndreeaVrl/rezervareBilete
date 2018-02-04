package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

public interface ILoginHelper {

	ModelAndView goToLoginPage(final ModelAndView model,final HttpServletRequest request);

	ModelAndView login(final ModelAndView model, final UserRequestBean loginBean, final HttpServletRequest request);

	ModelAndView changePassword(ModelAndView model, UserRequestBean loginBean, HttpServletRequest request);

	ModelAndView goToChangePasswordPage(ModelAndView model, HttpServletRequest request);

	ModelAndView createNewAccount(ModelAndView model, Client clientBean, HttpServletRequest request);

	ModelAndView logout(ModelAndView model, HttpServletRequest request);
}
 