package it.rezervare.beans.helper.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import beans.exception.ApplicationException;
import it.rezervare.beans.dao.Interfaces.IAeroportDAO;
import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.requestBeans.AeroportRequestBean;

@Service
@Lazy
@Transactional
@Repository
public class AdminHelper implements IAdminHelper {

	private final ITaraDAO taraDAO;
	private final IAeroportDAO aeroportDAO;

	@Autowired
	public AdminHelper(final ITaraDAO taraDAO, final IAeroportDAO aeroportDAO) {
		this.taraDAO = taraDAO;
		this.aeroportDAO = aeroportDAO;
	}

	@Override
	@Transactional
	public ModelAndView loadAdminPage(final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n\nenter AdminHelper - loadAdminPage\n\n");

		final List<Tara> allCountries = taraDAO.getAllCountrys();
		for (final Tara t : allCountries) {
			Hibernate.initialize(t.getClienti());
			Hibernate.initialize(t.getAeroporturi());
		}
		request.getSession().setAttribute("countriesList", allCountries);

		final List<Aeroport> allAirports = aeroportDAO.getAllAirports();
		for (final Aeroport a : allAirports) {
			Hibernate.initialize(a.getCurseAeroport_1());
			Hibernate.initialize(a.getCurseAeroport_2());
		}
		request.getSession().setAttribute("airportsList", allAirports);

		model.setViewName("admin");
		System.out.println("\n\nexit AdminHelper - loadAdminPage\n\n");
		return model;
	}

	@Override
	public ModelAndView editCountry(final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Enter editCountry");
		if (tara != null) {
			System.out.println(tara.getId());
			System.out.println(tara.getDenumire());

			final Tara countryDb = taraDAO.getCountryById(tara.getId());
			countryDb.setDenumire(tara.getDenumire());
			taraDAO.updateCountry(countryDb);
			System.out.println("Country updated");
		} else {
			System.out.println("Tara is null");
		}

		return model;
	}

	@Override
	public Tara addCountry(final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Enter addCountry");
		System.out.println(tara.getDenumire());
		taraDAO.inserTara(tara);
		System.out.println("ID of newest country:" + tara.getId());
		return tara;
	}

	@Override
	public Tara deleteCountry(final Tara tara, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n\n\nEnter deleteCountry - Country Id: " + tara.getId());
		final Tara countryToDelete = taraDAO.getCountryById(tara.getId());
		if (countryToDelete != null) {
			System.out.println(countryToDelete.getDenumire());
		} else {
			System.out.println("Country is null");
		}

		if (countryToDelete.getClienti().isEmpty() && countryToDelete.getAeroporturi().isEmpty()) {
			taraDAO.deleteCountry(countryToDelete);
			System.out.println("Country deleted");
		} else {
			System.out.println("This country can't be deleted");
		}

		return tara;
	}

	@Override
	public AeroportRequestBean addAirport(final AeroportRequestBean aeroportBean, final ModelAndView model, final HttpServletRequest request) {
		System.out.println(aeroportBean.getDenumire());
		System.out.println(aeroportBean.getIdTara());

		final Tara taraAeroport = taraDAO.getCountryById(aeroportBean.getIdTara());
		final Aeroport aeroport = new Aeroport();

		aeroport.setDenumire(aeroportBean.getDenumire());
		aeroport.setTara(taraAeroport);

		aeroportDAO.addAirport(aeroport);

		return aeroportBean;
	}

	@Override
	public AeroportRequestBean editAirport(final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("editAirport");
		System.out.println(aeroport);

		try {
			final Aeroport airportToEdit = aeroportDAO.getAirportById(aeroport.getId());
			airportToEdit.setDenumire(aeroport.getDenumire());
			aeroportDAO.updateAirport(airportToEdit);
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}

		return aeroport;
	}

	@Override
	public AeroportRequestBean deleteAirport(final AeroportRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("deleteAirport - " + aeroport.getId());
		try {
			final Aeroport airportToDelete = aeroportDAO.getAirportById(aeroport.getId());
			aeroportDAO.deleteCountry(airportToDelete);
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}

		return aeroport;
	}

}
