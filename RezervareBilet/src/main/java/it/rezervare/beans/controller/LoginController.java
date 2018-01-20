package it.rezervare.beans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.helper.helperinterface.ILoginHelper;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

@Controller
public class LoginController {

	private final ILoginHelper loginHelper;

	@Autowired
	public LoginController(final ILoginHelper loginHelper) {
		this.loginHelper = loginHelper;
	}
	@RequestMapping(value = { "/goToLoginPage"}, method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView goToLoginPage(final ModelAndView model, final HttpServletRequest request){
		System.out.println("\nENTER LoginController - login() ");
		return loginHelper.goToLoginPage(model,request);
	}
	@RequestMapping(value = { "/login"}, method = {RequestMethod.POST})
	public ModelAndView login(final ModelAndView model,@ModelAttribute("login") final UserRequestBean loginBean, final HttpServletRequest request ) {
		System.out.println("\nENTER LoginController - login() ");
		return loginHelper.login(model,loginBean,request);
	}
	@RequestMapping(value = { "/goToChangePasswordPage"}, method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView goToChangePasswordPage(final ModelAndView model, final HttpServletRequest request){
		System.out.println("\nENTER LoginController - login() ");
		return loginHelper.goToChangePasswordPage(model,request);
	}
	@RequestMapping(value = { "/changepassword" }, method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView changePassword(final ModelAndView model, @ModelAttribute("loginBean") final UserRequestBean loginBean, final HttpServletRequest request) {
		System.out.println("\nENTER LoginController - changePassword() \n");
		return loginHelper.changePassword(model, loginBean, request);
	}
}
