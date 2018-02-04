package it.rezervare.beans.helper.helperinterface;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Bilet;
import it.rezervare.beans.model.hibernateBeans.Client;

public interface IUserHelper {
	public ModelAndView goToChangeUserAccountPAge(ModelAndView model, HttpServletRequest request);

	ModelAndView changeUserAccount(ModelAndView model, Client client, HttpServletRequest request);

	ModelAndView reservations(ModelAndView model, HttpServletRequest request);
	
	String deleteReservation(final Integer id, final ModelAndView model, final HttpServletRequest request);

	ModelAndView doCheckin(ModelAndView model, Bilet bilet, HttpServletRequest request);

	void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
