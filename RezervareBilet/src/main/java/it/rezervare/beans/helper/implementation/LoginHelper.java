package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import beans.exception.ExceptionsMessages;
import it.rezervare.beans.constants.ApplicationConstants;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.dao.Interfaces.IOperatorDAO;
import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.ILoginHelper;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;
import it.rezervare.beans.model.requestBeans.UserRequestBean;
import it.rezervare.beans.utils.MD5Utils;

@Service
@Lazy
public class LoginHelper implements ILoginHelper {
	
	private final IClientDAO clientDAO;
	private final IOperatorDAO operatorDAO;
	private final ITaraDAO taraDAO;
	
	@Autowired
	public LoginHelper(final IClientDAO clientDAO,final IOperatorDAO operatorDAO, final ITaraDAO taraDAO) {
		this.clientDAO = clientDAO;
		this.operatorDAO = operatorDAO;
		this.taraDAO = taraDAO;
	}

	@Override
	public ModelAndView goToLoginPage(final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Enter LoginHelper - goToLoginPage ");
		model.addObject("loginBean", new UserRequestBean());
		model.setViewName("login");
		System.out.println("Exit LoginHelper.goToLoginPage() ");
		return model;
	}
	
	@Override
	public ModelAndView goToChangePasswordPage(final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Enter LoginHelper - goToLoginPage ");
		final HttpSession session = request.getSession();
		final UserRequestBean user = (UserRequestBean) session.getAttribute(ApplicationConstants.ERROR_LOGIN);
		model.addObject("loginBean", user != null ? user : new UserRequestBean());
		model.setViewName("changepassword");
		System.out.println("Exit LoginHelper.goToLoginPage() ");
		return model;
	}
	
	@Override
	public ModelAndView login(final ModelAndView model, final UserRequestBean loginBean, final HttpServletRequest request) {
		System.out.println("ENTER LoginHelper.login with loginBean = ["+loginBean+"]\n");
		try {
			if(loginBean == null || StringUtils.isEmpty(loginBean.getUserName()) || StringUtils.isEmpty(loginBean.getPassword())) {
				throw new ApplicationException("Va rugam, completati datele de autentificare!");
			}
			final HttpSession session = request.getSession();
			final String password = MD5Utils.convertStringToMD5(loginBean.getPassword());
			loginBean.setPassword(password);
			System.out.println("password=["+password+"]");
			final String rezervareInCurs = (String) session.getAttribute("utNelogat");
			System.out.println("rezervareInCurs=["+rezervareInCurs+"]");
			
			final Client isClientUer = (Client) session.getAttribute(ApplicationConstants.CLIENT);
			final Operator isOperatorUser = (Operator) session.getAttribute(ApplicationConstants.OPERATOR);
			
			List<Tara> tari = new ArrayList<>();
			tari = taraDAO.getAllCountrys();
			model.addObject("tari",tari);
			session.removeAttribute("tari");
			session.setAttribute("tari", tari);
			
			if(isClientUer == null && isOperatorUser == null) {
				if(loginBean != null && (!StringUtils.isEmpty(loginBean.getUserName()) || !StringUtils.isEmpty(loginBean.getPassword()))) {
					final Client client = clientDAO.getClientAccount(loginBean);
					if(client == null) {
						final Operator operator = operatorDAO.getOperator(loginBean);
						if(operator != null) {
							session.setAttribute(ApplicationConstants.OPERATOR, operator);
							if("1".equals(rezervareInCurs)) {
								model.setViewName("billingData");
								model.addObject("factura",new Client());
								final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
								model.addObject("flight",flight);
								model.addObject("succes", "Logarea s-a realizat cu success! Continuati rezervarea!");
								System.out.println("operator-continua rezervarea");
							}else {
								model.setViewName("admin");
								model.addObject("operator", new Operator());
								model.addObject("tara", new Tara());
								model.addObject("succes", "Logarea s-a realizat cu success!");
							}
						} else {
							session.removeAttribute(ApplicationConstants.ERROR_LOGIN);
							session.setAttribute(ApplicationConstants.ERROR_LOGIN, loginBean);
							model.addObject("login", loginBean);
							model.setViewName("login");
							throw new ApplicationException("Numele de utilizator sau parola sunt incorecte!");
						}
					} else {
						session.setAttribute(ApplicationConstants.CLIENT, client);
						if("1".equals(rezervareInCurs)) {
							System.out.println("client-continua rezervarea");
							model.setViewName("billingData");
							model.addObject("factura",client);
							final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
							model.addObject("flight",flight);
							model.addObject("succes", "Logarea s-a realizat cu success! Continuati rezervarea!");
						}else {
							model.addObject("succes", "Logarea s-a realizat cu success!");
							model.addObject("client",client);
							model.addObject("cursa", new CursaRequestView());
							model.addObject("flightChosen",new FlightChosenRequestBean());
							model.setViewName("index");
						}
					}
					
				} else {
					throw new ApplicationException("Completati datele de autentificare!");
				}
			} else if (isClientUer != null) {
				model.setViewName("index");
			} else {
				model.setViewName("admin");
				model.addObject("operator", new Operator());
				model.addObject("tara", new Tara());
			}
		} catch (final Exception e) {
			model.addObject("exceptie", e.getMessage());
			model.setViewName("login");
		}
		System.out.println("Exit LoginHelper.login()");
		return model;
	}
	
	@Override
	public ModelAndView changePassword(final ModelAndView model, final UserRequestBean loginBean, final HttpServletRequest request) {
		System.out.println("\nENTER LoginHelper - changePassword() with loginBean = ["+loginBean+"] \n");
		try {
			final HttpSession session = request.getSession();
			if (loginBean.getPassword() != null && !"".equals(loginBean.getPassword()) 
					&& loginBean.getRepeatPassword() != null && !"".equals(loginBean.getRepeatPassword()) 
					&& loginBean.getPassword().equals(loginBean.getRepeatPassword()) ) {
				final Client client = clientDAO.getClientByEmail(loginBean);
				final Operator operator = operatorDAO.getOperatorByUsename(loginBean);
				if(client != null) {
					client.setParola(loginBean.getPassword());
					clientDAO.updateUser(client);
					session.setAttribute(ApplicationConstants.LOGGED_CLIENT_USER, client);
					model.addObject("succes", "Parola a fost schimbata cu succes!");
					model.addObject("loginBean",loginBean);
					model.setViewName("login");
				} else if(operator != null) {
					operator.setParola(loginBean.getPassword());
					operatorDAO.updateUser(operator);
					session.setAttribute(ApplicationConstants.LOGGED_OPERATOR_USER, operator);
					model.addObject("succes", "Parola a fost schimbata cu succes!");
					model.addObject("loginBean",loginBean);
					model.setViewName("login");
				} else {
					model.setViewName("changepassword");
					throw new ApplicationException("Userul specificat nu corespunde unui cont!");
				}
			} else {
				System.out.println("Parola invalida!");
				loginBean.setPassword("");
				loginBean.setRepeatPassword("");
				throw new ApplicationException("Noua parola nu este valida!");
			}
		} catch(final Exception e) {
			model.setViewName("changepassword");
			model.addObject("loginBean", loginBean != null ? loginBean : new UserRequestBean());
			model.addObject("exceptie", e.getMessage());
		}
		System.out.println("\nEXIT LoginHelper - changePassword()\n");
		return model;
	}

	@Override
	public ModelAndView createNewAccount(final ModelAndView model, final Client clientBean, final HttpServletRequest request) {
		System.out.println("\nENTER LoginHelper - createNewAccount()");
		System.out.println(clientBean.getNume());
		System.out.println(clientBean.getPrenume());
		System.out.println(clientBean.getEmail());
		System.out.println(clientBean.getParola());
		final HttpSession session = request.getSession();
		final String rezervareInCurs = (String) session.getAttribute("utNelogat");
		
		try {
			clientBean.setParola(MD5Utils.convertStringToMD5(clientBean.getParola()));
			System.out.println(clientBean.getParola());
			final Client clientExistent = clientDAO.getClientByEmail(clientBean.getEmail());
			if(clientExistent != null) {
				System.out.println("Email-ul exista deja");
				clientBean.setParola("");
				throw new ApplicationException(ExceptionsMessages.EMAIL_ALREADY_USED);
			}
			
			clientDAO.insertClient(clientBean);
			if("1".equals(rezervareInCurs)) {
				model.setViewName("billingData");
				model.addObject("factura",new Client());
				final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
				model.addObject("flight",flight);
				model.addObject("succes", "Contul a fost creat cu success! Continuati rezervarea!");
			}else {
				model.addObject("clientBean", new Client());
				model.addObject("succes", "Contul a fost creat cu success");
			}
		} catch (final ApplicationException e) {
			model.addObject("clientBean", clientBean != null ? clientBean : new Client());
			model.addObject("exceptie", e.getMessage());
		}
		
		model.setViewName("newaccount");
		return model;
	}
}
