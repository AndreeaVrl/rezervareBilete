package it.rezervare.beans.helper.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.constants.ApplicationConstants;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.dao.Interfaces.ITipuriCarduriDAO;
import it.rezervare.beans.helper.helperinterface.IRezervareHelper;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.hibernateBeans.TipCard;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;
import it.rezervare.beans.model.requestBeans.PassengersRequestView;
import it.rezervare.beans.model.requestBeans.RezervareRequestBean;
import it.rezervare.beans.model.requestBeans.UserRequestBean;

@Service
@Lazy
public class RezervareHelper implements IRezervareHelper {

	public IClientDAO clientDAO;	
	public ITipuriCarduriDAO tipCardDAO;
	
	@Autowired
	public RezervareHelper(final IClientDAO clientDAO, final ITipuriCarduriDAO tipCardDAO) {
		this.clientDAO = clientDAO;
		this.tipCardDAO = tipCardDAO;
	}
	
	@Override
	public ModelAndView goToPassengerData(final ModelAndView model, @ModelAttribute("rezervare") final RezervareRequestBean rezervare, final HttpServletRequest request) {
		System.out.println("ENTER RezervareHelper.goToPassengerData() with rezervare = ["+rezervare+"]");
		final HttpSession session = request.getSession();
		final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
		final List<Client> listaClient= new ArrayList<>();
		for(int i=0;i<flight.getPassengers();i++) {
			listaClient.add(new Client());
		}
		model.addObject("pasageri",new PassengersRequestView());
		model.addObject("flight",flight);
		model.setViewName("customerData");
		System.out.println("EXIT RezervareHelper.goToPassengerData() \n");
		return model;
	}
	
	@Override
	public ModelAndView goToBillingData(final ModelAndView model, @ModelAttribute("pasageri") final PassengersRequestView pasageri, final HttpServletRequest request) {
		System.out.println("ENTER RezervareHelper.goToBillingData() with pasageri = ["+pasageri+"]");
		final HttpSession session = request.getSession();
		final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
		try {
			/*for(final Client client : pasageri.getListaClienti()) {
					clientDAO.insertClient(client);
			}*/
			session.removeAttribute("datePasageri");
			session.setAttribute("datePasageri",pasageri);
			final Operator operator = (Operator) session.getAttribute(ApplicationConstants.OPERATOR);
			final Client client = (Client) session.getAttribute(ApplicationConstants.CLIENT);
			if(client == null && operator == null) {
				model.setViewName("login");
				model.addObject("login", new UserRequestBean());
				model.addObject("utNelogat","1");
				session.removeAttribute("utNelogat");
				session.setAttribute("utNelogat", "1");
			} else {
				model.setViewName("billingData");
			}
		} catch (final Exception e) {
			model.addObject("exceptie",e.getMessage());
		}
		model.addObject("factura",new Client());
		model.addObject("flight",flight);
		System.out.println("EXIT RezervareHelper.goToBillingData() \n");
		return model;
	}
	@Override
	public ModelAndView goPaymentPage(final ModelAndView model, @ModelAttribute("factura") final Client client, final HttpServletRequest request) {
		System.out.println("ENTER RezervareHelper.goToBillingData() with factura = ["+client+"]");
		final HttpSession session = request.getSession();
		try {
			final List<TipCard> tipuriCarduri = tipCardDAO.getCardTypes();	
			System.out.println("tipuriCarduri = ["+tipuriCarduri+"]");
			session.removeAttribute("factura");
			session.setAttribute("factura",client);
			model.addObject("modalitatePlata",new ModalitatePlata());
			model.addObject("tipuriCarduri", tipuriCarduri);
			model.setViewName("payment");
		} catch (final Exception e) {
			model.addObject("exceptie",e.getMessage());
		}
		System.out.println("EXIT RezervareHelper.goToBillingData() \n");
		return model;
	}
}
