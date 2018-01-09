package it.rezervare.beans.helper.helperinterface;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import it.rezervare.beans.model.hibernateBeans.Tara;

public interface IFromToHelper {
	
	public ModelAndView chooseCountry(ModelAndView model, Tara tara, HttpServletRequest request);

}
