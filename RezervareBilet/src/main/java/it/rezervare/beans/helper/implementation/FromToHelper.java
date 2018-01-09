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

import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.IFromToHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;

@Service
@Lazy
public class FromToHelper implements IFromToHelper {

	private final ITaraDAO taraDAO;
	
	@Autowired
	public FromToHelper(ITaraDAO taraDAO){
		this.taraDAO = taraDAO;
	}
	
	@Override
	public ModelAndView chooseCountry(ModelAndView model, @ModelAttribute("tara") Tara tara, HttpServletRequest request){
		System.out.println("\nENTER FromToHelper -> chooseCountry() <-\n");
		HttpSession session = request.getSession();
		try{
			List<Tara> tari = new ArrayList<>();
			tari = taraDAO.getAllCountrys();
			model.addObject("tari",tari);
			model.setViewName("index");
		}catch(Exception exc){
			System.out.println("\n"+exc.toString()+" "+exc.getMessage()+"\n");
		}
		return model;
	}
}
