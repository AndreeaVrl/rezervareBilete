package it.rezervare.beans.helper.implementation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import it.rezervare.beans.utils.CreatePDF;

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
			session.removeAttribute("suma");
			session.setAttribute("suma",suma);
			session.removeAttribute("bilet");
			session.setAttribute("bilet",bilet);
			model.setViewName("pdfCheckin");
		} catch (final Exception e ) {
			e.printStackTrace();
			model.addObject("exceptie",ExceptionsMessages.GENERIC_ERROR);
		}
		System.out.println("\n EXIT UserHelper.doCheckin() \n");
		return model;
	}
	
	@Override
	public void downloadReport(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		System.out.println("Enter downloadReport \n");
		try {
			final HttpSession session = request.getSession();
			final ServletContext servletContext = request.getSession().getServletContext();
			final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			System.out.println("tempDirectory = ["+tempDirectory+"]");
			final String temperotyFilePath = tempDirectory.getAbsolutePath();
			final BigDecimal suma = (BigDecimal) session.getAttribute("suma");
			final Bilet bilet = (Bilet) session.getAttribute("bilet");
			final Bilet biletForItext = biletDAO.getBiletById(bilet.getId());
			final String titlePdf = "Check-in";
			final String fileName = "Checkin.pdf";
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			CreatePDF.createPDF(temperotyFilePath + "\\" + fileName,biletForItext,suma,titlePdf);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
			final OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (final Exception e ) {
			e.printStackTrace();
		}
		System.out.println("Exit downloadReport \n");
	}

	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(final String fileName) {
		
		System.out.println("START - convertPDFToByteArrayOutputStream");
		
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			inputStream = new FileInputStream(fileName);
			final byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}

}