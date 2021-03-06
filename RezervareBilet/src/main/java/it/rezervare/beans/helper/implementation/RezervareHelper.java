package it.rezervare.beans.helper.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import it.rezervare.beans.constants.ApplicationConstants;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.dao.Interfaces.ITipuriCarduriDAO;
import it.rezervare.beans.helper.helperinterface.IRezervareHelper;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.hibernateBeans.TipCard;
import it.rezervare.beans.model.hibernateBeans.Zbor;
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
		try {
			final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
			final List<Client> listaClient= new ArrayList<>();
			final Map<String,String> pretLoc = new HashMap<>();
			if(CollectionUtils.isEmpty(rezervare.getLocuri())) {
				throw new ApplicationException("Selectati locurile pasagerilor!");
			}
			for(final String loc : rezervare.getLocuri()) {
				final String[] splitRezervare = loc.split("-");
				pretLoc.put(loc, splitRezervare[3]);
			}
			for(int i=0;i<flight.getPassengers();i++) {
				listaClient.add(new Client());
			}
			session.removeAttribute("pretLoc");
			session.setAttribute("pretLoc", pretLoc);
			model.addObject("pasageri",new PassengersRequestView());
			model.addObject("flight",flight);
			model.setViewName("customerData");
			session.removeAttribute("locuriRezervare");
			session.setAttribute("locuriRezervare", rezervare);
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie", e.getMessage());
		} 
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
	@SuppressWarnings("unchecked")
	public ModelAndView goPaymentPage(final ModelAndView model, @ModelAttribute("factura") final Client client, final HttpServletRequest request) {
		System.out.println("ENTER RezervareHelper.goPaymentPage() with factura = ["+client+"]");
		final HttpSession session = request.getSession();
		try {
			final List<TipCard> tipuriCarduri = tipCardDAO.getCardTypes();	
			System.out.println("tipuriCarduri = ["+tipuriCarduri+"]");
			final Map<String,String> pretLoc = (Map<String, String>) session.getAttribute("pretLoc");
			final Map<String,BigDecimal> preturi = (Map<String, BigDecimal>) session.getAttribute("preturi");
			System.out.println("preturi=["+preturi+"]");
			final Map<String,BigDecimal> preturiFinale = new HashMap<>();
			final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
			BigDecimal rezultatTotal = new BigDecimal(0);
			final List<Zbor> listaZboruri = (List<Zbor>) session.getAttribute("zboruri");
			if(flight.getPackageChosen() == 1) {
				for(final Zbor zbor : listaZboruri) {
					BigDecimal test = new BigDecimal(0);
					test = zbor.getPret().multiply(new BigDecimal(flight.getPassengers()));
					rezultatTotal = rezultatTotal.add(test);
					System.out.println("rezultatTotal=["+rezultatTotal+"]");
				}
			} else {
				Integer i = 1;
				for(final Map.Entry<String, String> entry : pretLoc.entrySet()) {
					BigDecimal rezultat = new BigDecimal(0);
					final String[] key = entry.getKey().split("-");
					System.out.println("preturi.get(key[0])=["+preturi.get(key[0])+"]");
					final BigDecimal test = new BigDecimal(0);
					final BigDecimal pretLocAles = new BigDecimal(entry.getValue());
					rezultat = test.add(pretLocAles).add(preturi.get(key[0]));
					rezultatTotal = rezultatTotal.add(rezultat);
					System.out.println("rezultat = " + rezultat);
					System.out.println("key="+entry.getKey());
					preturiFinale.put(entry.getKey(), rezultat);
					i++;
					
				}
				System.out.println("rezultatTotal=["+rezultatTotal+"]");
				session.removeAttribute("preturiFinale");
				session.setAttribute("preturiFinale",preturiFinale);
			}
			session.removeAttribute("rezultatTotal");
			session.setAttribute("rezultatTotal",rezultatTotal);
			session.removeAttribute("factura");
			session.setAttribute("factura",client);
			final ModalitatePlata modalitatePlata = new ModalitatePlata();
			final TipCard tipCard = new TipCard();
			modalitatePlata.setTipCard(tipCard);
			model.addObject("modalitatePlata",modalitatePlata);
			model.addObject("tipuriCarduri", tipuriCarduri);
			model.setViewName("payment");
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie",e.getMessage());
		}
		System.out.println("EXIT RezervareHelper.goPaymentPage() \n");
		return model;
	}
}
