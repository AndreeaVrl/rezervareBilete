package it.rezervare.beans.helper.implementation;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ExceptionsMessages;
import it.rezervare.beans.constants.ApplicationConstants;
import it.rezervare.beans.dao.Interfaces.IBiletDAO;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.helper.helperinterface.IUserHelper;
import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.hibernateBeans.Stare;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;

@Service
@Lazy
public class UserHelper implements IUserHelper {
	
	private final IClientDAO clientDAO;
	private final IBiletDAO biletDAO;
	
	@Autowired
	public UserHelper(final IClientDAO clientDAO, final IBiletDAO biletDAO) {
		this.clientDAO = clientDAO;
		this.biletDAO = biletDAO;
	}
	
	@Override
	public ModelAndView goToChangeUserAccountPAge(final ModelAndView model, final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		final Client client = (Client) session.getAttribute(ApplicationConstants.CLIENT);
		model.addObject("client", client);
		model.setViewName("userAccount");
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView changeUserAccount(final ModelAndView model,@ModelAttribute("client") final Client client, final HttpServletRequest request) {
		System.out.println("\n ENTER UserHelper.goToChangeUserAccountPAge() \n");
		try {
			final HttpSession session = request.getSession();
			final List<Tara> tari = (List<Tara>) session.getAttribute("tari");
			model.addObject("tari",tari);
			session.removeAttribute("zboruriCautareRetur");
			session.removeAttribute("zboruriCautare");
			model.addObject("cursa", new CursaRequestView());
			model.addObject("flightChosen",new FlightChosenRequestBean());
			model.setViewName("index");
			final Client clientSesiune = (Client) session.getAttribute(ApplicationConstants.CLIENT);
			clientSesiune.setTelefon(client.getTelefon());
			clientDAO.updateUser(clientSesiune);
			model.addObject("succes","Datele dumneavoastra au fost actualizate cu success!");
		} catch (final Exception e ) {
			e.printStackTrace();
			model.addObject("exceptie",ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\n EXIT UserHelper.goToChangeUserAccountPAge() \n");
		return model;
	}
	@Override
	public ModelAndView reservations (final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n ENTER UserHelper.reservations \n");
		try {
			final HttpSession session = request.getSession();
			final Client client = (Client) session.getAttribute(ApplicationConstants.CLIENT);
			final List<Bilet> listaBilete = biletDAO.getReservations(client);
			model.addObject("listaBilete",listaBilete);
			model.setViewName("allReservations");
			for(final Bilet bilet : listaBilete) {
				System.out.println("\nid=["+bilet.getId()+"]");
				System.out.println("\ncursa=["+bilet.getZbor().getCursa().getAeroport_1().getDenumire()+"-"+bilet.getZbor().getCursa().getAeroport_2().getDenumire()+"]");
				System.out.println("\n pachet=["+bilet.getPachet().getDenumire()+"]");
				System.out.println("\n loc=["+bilet.getLoc().getRand()+bilet.getLoc().getColoana()+"]\n");
			}
		} catch (final Exception e ) {
			e.printStackTrace();
			model.addObject("exceptie",ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\n EXIT UserHelper.reservations() \n");
		return model;
	}

	@Override
	public String deleteReservation(final Integer id, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n ENTER UserHelper.deleteReservation() with id = ["+id+"]\n");
		 String mesaj = "";
		try {
			final Bilet bilet = biletDAO.getBiletById(id);
			final Stare stareBilet = new Stare();
			stareBilet.setId((byte) 2);
			stareBilet.setDenumire("anulat");
			bilet.setStare(stareBilet);
			biletDAO.updateBilet(bilet);
			mesaj = "Rezervarea a fost anulata!";
		} catch (final Exception e ) {
			e.printStackTrace();
			model.addObject("exceptie",ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\nEXIT UserHelper.deleteReservation() with mesaj=["+mesaj+"]\n");
		return mesaj;
	}
	@Override
	public ModelAndView doCheckin (final ModelAndView model, final Bilet bilet, final HttpServletRequest request) {
		System.out.println("\n ENTER UserHelper.doCheckin with bilet=["+bilet+"] \n");
		try {
			final HttpSession session = request.getSession();
			final Bilet biletById = biletDAO.getBiletById(bilet.getId());
			BigDecimal suma = new BigDecimal(0);
			suma = biletById.getZbor().getPret().add(biletById.getPachet().getTaxaPachet());
			model.addObject("suma", suma);
			model.addObject("bilet", biletById);
			model.setViewName("pdfCheckin");
		} catch (final Exception e ) {
			e.printStackTrace();
			model.addObject("exceptie",ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\n EXIT UserHelper.doCheckin() \n");
		return model;
	}
}