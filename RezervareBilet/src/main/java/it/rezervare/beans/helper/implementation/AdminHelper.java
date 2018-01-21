package it.rezervare.beans.helper.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.model.hibernateBeans.Tara;

@Service
@Lazy
@Transactional
@Repository
public class AdminHelper implements IAdminHelper {

	private ITaraDAO taraDAO;

	@Autowired
	public AdminHelper(ITaraDAO taraDAO) {
		this.taraDAO = taraDAO;
	}

	@Override
	public ModelAndView loadAdminPage(ModelAndView model, HttpServletRequest request) {
		System.out.println("\n\nenter AdminHelper - loadAdminPage\n\n");

		List<Tara> allCountries = taraDAO.getAllCountrys();
		request.getSession().setAttribute("countriesList", allCountries);

		model.setViewName("admin");
		System.out.println(allCountries);
		System.out.println("\n\nexit AdminHelper - loadAdminPage\n\n");
		return model;
	}

	@Override
	public ModelAndView editCountry(Tara tara, ModelAndView model, HttpServletRequest request) {
		System.out.println("ENTER EDIT COUNTRY");
		if (tara != null) {
			System.out.println(tara.getId());
			System.out.println(tara.getDenumire());

			Tara countryDb = taraDAO.getCountryById(tara.getId());
			countryDb.setDenumire(tara.getDenumire());
			taraDAO.updateCountry(countryDb);
			System.out.println("Country updated");
		} else {
			System.out.println("Tara is null");
		}

		return model;
	}

	@Override
	public Tara addCountry(Tara tara, ModelAndView model, HttpServletRequest request) {
		System.out.println("ENTER ADD COUNTRY");
		System.out.println(tara.getDenumire());
		taraDAO.inserTara(tara);
		System.out.println("ID of newest country:" + tara.getId());
		return tara;
	}

}
