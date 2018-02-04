package it.rezervare.beans.helper.helperinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.hibernateBeans.Aeroport;

public interface IIndexHelper {
	
	public ModelAndView goToIndexPage(ModelAndView model, HttpServletRequest request);

	List<Aeroport> getAirports(Integer id, ModelAndView model, HttpServletRequest request);

}
