package it.rezervare.beans.helper.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.constants.ApplicationConstants;
import it.rezervare.beans.dao.Interfaces.IBiletDAO;
import it.rezervare.beans.dao.Interfaces.IClientDAO;
import it.rezervare.beans.dao.Interfaces.ILocDAO;
import it.rezervare.beans.dao.Interfaces.IModalitatePlata;
import it.rezervare.beans.dao.Interfaces.IOperatorDAO;
import it.rezervare.beans.dao.Interfaces.IPachetDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IPaymentHelper;
import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;
import it.rezervare.beans.model.hibernateBeans.Loc;
import it.rezervare.beans.model.hibernateBeans.ModalitatePlata;
import it.rezervare.beans.model.hibernateBeans.Operator;
import it.rezervare.beans.model.hibernateBeans.Pachet;
import it.rezervare.beans.model.hibernateBeans.Stare;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.hibernateBeans.TipCard;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.CursaRequestView;
import it.rezervare.beans.model.requestBeans.FlightChosenRequestBean;
import it.rezervare.beans.model.requestBeans.PassengersRequestView;
import it.rezervare.beans.model.requestBeans.RezervareRequestBean;

@Service
@Lazy
public class PaymentHelper implements IPaymentHelper {

	public IPachetDAO pachetDAO;
	public IClientDAO clientDAO;
	public IZborDAO zborDAO;
	public IOperatorDAO operatorDAO;
	public ILocDAO locDAO;
	public IModalitatePlata modalitatePlataDAO;
	public IBiletDAO biletDAO;
	private final JavaMailSender mailSender;
	@Autowired
	public PaymentHelper(final IPachetDAO pachetDAO, final IClientDAO clientDAO, final IModalitatePlata modalitatePlataDAO,
			final IZborDAO zborDAO,final IOperatorDAO operatorDAO, final ILocDAO locDAO, final IBiletDAO biletDAO, final JavaMailSender mailSender) {
		this.pachetDAO = pachetDAO;
		this.clientDAO = clientDAO;
		this.zborDAO = zborDAO;
		this.operatorDAO = operatorDAO;
		this.locDAO = locDAO;
		this.modalitatePlataDAO = modalitatePlataDAO;
		this.biletDAO = biletDAO;
		this.mailSender = mailSender;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView createPayment(final ModelAndView model, @ModelAttribute final ModalitatePlata modalitatePlata, final HttpServletRequest request) {
		System.out.println("ENTER PaymentHelper.createPayment() with modalitatePlata = ["+modalitatePlata+"]");
		try {
			final HttpSession session = request.getSession();
			final TipCard tipCard = modalitatePlataDAO.getTipCardById(new Integer(1));
			final Client clientRez = (Client) session.getAttribute(ApplicationConstants.CLIENT);
			modalitatePlata.setClient(clientRez);
			modalitatePlata.setTipCard(tipCard);
			modalitatePlataDAO.save(modalitatePlata);
			final PassengersRequestView pasageri = (PassengersRequestView) session.getAttribute("datePasageri");
			final List<Client> listaClienti = new ArrayList<>();
			for(final Client client : pasageri.getListaClienti()) {
				clientDAO.insertClient(client);
				final Integer id = clientDAO.getLastInIserted();
				client.setId(id);
				listaClienti.add(client);
			}
			final FlightChosenRequestBean flight = (FlightChosenRequestBean) session.getAttribute("flight");
			final Pachet pachet = pachetDAO.getPachetById(Integer.valueOf(flight.getPackageChosen()));
			final RezervareRequestBean rezervare = (RezervareRequestBean) session.getAttribute("locuriRezervare");
			Integer i = 0;
			final List<Bilet> listaBilete = new ArrayList<>();
			for(final String loc : rezervare.getLocuri()) {
				i++;
				final Bilet bilet = new Bilet();
				bilet.setClientBilet(listaClienti.get(i-1));
				if(i == flight.getPassengers()) {
					i = 0;
				}
				bilet.setPachet(pachet);
				bilet.setClientRezervare(clientRez);
				final String[] splitRezervare = loc.split("-");
				final String keyZbor = splitRezervare[0];
				final Zbor zbor = zborDAO.getFlightById(Integer.valueOf(keyZbor));
				bilet.setZbor(zbor);
				final Operator operator = operatorDAO.getOperatorById(new Integer(1));
				bilet.setOperator(operator);
				final Stare stare = new Stare();
				stare.setId((byte) 1);
				stare.setDenumire("valabil");
				bilet.setStare(stare);
				final String rand = splitRezervare[1];
				final String coloana = splitRezervare[2];
				final Loc locFromDAO = locDAO.getSeatByRowAndColumnAndSeatType(Integer.valueOf(rand), String.valueOf((char)((Integer.valueOf(coloana)+1) + 64)),zbor.getAvion().getTipAvion());
				bilet.setLoc(locFromDAO);
				bilet.setModalitatePlata(modalitatePlata);
				biletDAO.save(bilet);
				listaBilete.add(bilet);
			}
			sendMail(listaBilete,clientRez,request);
			final List<Tara> listaTari =  (List<Tara>) session.getAttribute("tari");
			model.addObject("tari",listaTari);
			model.addObject("cursa", new CursaRequestView());
			model.addObject("flightChosen",new FlightChosenRequestBean());
			model.setViewName("index");
			session.removeAttribute("listaBilete");
			session.setAttribute("listaBilete", listaBilete);
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("exceptie", e.getMessage());
		} 
		System.out.println("\nEXIT PaymentHelper.createPayment()\n");
		return model;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	private void sendMail(final List<Bilet> listaBilete, final Client clientRez, final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(clientRez.getEmail());
		System.out.println("TO = ["+mailMessage.getTo()+"]");
		System.out.println("FROM = ["+mailMessage.getFrom()+"]");
		mailMessage.setSubject("Rezervare bilete avion");
		final Map<String, String> routMap = (Map<String, String>) session.getAttribute("routMap");
		final StringBuilder createMessage = new StringBuilder("Rezervarea dumneavoastra a fost inregistrata.\n\n");
		for ( final Bilet bilet : listaBilete) {
			BigDecimal suma = new BigDecimal(0);
			createMessage.append("Numar bilet:"+bilet.getId()+"\n");
			createMessage.append("Nume pasager:"+bilet.getClientBilet().getNume()+" "+bilet.getClientBilet().getPrenume()+"\n");
			createMessage.append("Zbor:"+routMap.get(String.valueOf(bilet.getZbor().getId()))+"\n");
			System.out.println("ruta = ["+String.valueOf(bilet.getZbor().getId())+"]");
			createMessage.append("Pachet facilitati:"+bilet.getPachet().getDenumire()+" ["+bilet.getPachet().getCaracteristici()+"] \n");
			createMessage.append("Loc:"+bilet.getLoc().getRand()+bilet.getLoc().getColoana()+"\n");
			createMessage.append("Pret zbor:"+bilet.getZbor().getPret()+"\n");
			createMessage.append("Pret loc:"+bilet.getPachet().getTaxaPachet()+"\n");
			suma = bilet.getZbor().getPret().add(bilet.getPachet().getTaxaPachet());
			createMessage.append("Pret bilet:"+suma+"\n\n");
		}
		mailMessage.setText(createMessage.toString());
		System.out.println("Mail = ["+mailMessage.getText()+"]");
		mailSender.send(mailMessage);
	}
}
