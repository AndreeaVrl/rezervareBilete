package it.rezervare.beans.helper.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
import it.rezervare.beans.dao.Interfaces.ICompanieDAO;
import it.rezervare.beans.dao.Interfaces.ICursaDAO;
import it.rezervare.beans.dao.Interfaces.IPachetDAORadu;
import it.rezervare.beans.dao.Interfaces.ITaraDAO;
import it.rezervare.beans.dao.Interfaces.IZborDAO;
import it.rezervare.beans.helper.helperinterface.IAdminHelper;
import it.rezervare.beans.model.hibernateBeans.Aeroport;
import it.rezervare.beans.model.hibernateBeans.Avion;
import it.rezervare.beans.model.hibernateBeans.Companie;
import it.rezervare.beans.model.hibernateBeans.Cursa;
import it.rezervare.beans.model.hibernateBeans.Pachet;
import it.rezervare.beans.model.hibernateBeans.Tara;
import it.rezervare.beans.model.hibernateBeans.Zbor;
import it.rezervare.beans.model.requestBeans.AdminRequestBean;

@Service
@Lazy
@Transactional
@Repository
public class AdminHelper implements IAdminHelper {

	private final ITaraDAO taraDAO;
	private final IAeroportDAO aeroportDAO;
	private final ICursaDAO cursaDAO;
	private final ICompanieDAO companieDAO;
	private final IPachetDAORadu pachetDAO;
	private final IZborDAO zborDAO;

	@Autowired
	public AdminHelper(final ITaraDAO taraDAO, final IAeroportDAO aeroportDAO, final ICursaDAO cursaDAO, final ICompanieDAO companieDAO, final IPachetDAORadu pachetDAO, final IZborDAO zborDAO) {
		this.taraDAO = taraDAO;
		this.aeroportDAO = aeroportDAO;
		this.cursaDAO = cursaDAO;
		this.companieDAO = companieDAO;
		this.pachetDAO = pachetDAO;
		this.zborDAO = zborDAO;
	}

	@Override
	@Transactional
	public ModelAndView loadAdminPage(final ModelAndView model, final HttpServletRequest request) {
		System.out.println("\n\nenter AdminHelper - loadAdminPage\n\n");
		try {
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

				if (a.getId() == 1) {
					System.out.println("\n\n\n\n Linz Airport");
					final Set<Cursa> curse = a.getCurseAeroport_1();
					for (final Cursa c : curse) {
						System.out.println("\nRoute:");
						System.out.println(c.getAeroport_2().getDenumire());
					}

					System.out.println();
				}
			}
			request.getSession().setAttribute("airportsList", allAirports);

			final List<Cursa> routesList = cursaDAO.getAll();
			for (final Cursa c : routesList) {
				Hibernate.initialize(c.getZboruri());
			}
			request.getSession().setAttribute("routesList", routesList);

			final List<Companie> companiesList = companieDAO.getAllCompanies();
			for (final Companie c : companiesList) {
				Hibernate.initialize(c.getPachete());
			}
			request.getSession().setAttribute("companiesList", companiesList);
			
			final List<Pachet> packagesList = pachetDAO.getAllPackages();
			request.getSession().setAttribute("packagesList", packagesList);
			
			final List<Zbor> flightsList = zborDAO.getAllFlights();
			for(final Zbor z : flightsList) {
				Hibernate.initialize(z.getCursa().getAeroport_1().getTara());
				Hibernate.initialize(z.getCursa().getAeroport_2().getTara());
				Hibernate.initialize(z.getCompanie());
				Hibernate.initialize(z.getAvion().getTipAvion());
			}
			request.getSession().setAttribute("flightsList", flightsList);
			
			
			final List<Avion> airplanesList = zborDAO.getAllAirplanes();
			for(final Avion a : airplanesList) {
				Hibernate.initialize(a.getTipAvion());
			}
			
			request.getSession().setAttribute("airplanesList", airplanesList);
			

			// final List<Cursa> allRoutes = cursaDAO.getAll();
			// for (final Cursa c : allRoutes) {
			// Hibernate.initialize(c.getAeroport_1());
			// Hibernate.initialize(c.getAeroport_2());
			// }
			// request.getSession().setAttribute("routesList", allRoutes);
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}

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
	public AdminRequestBean addAirport(final AdminRequestBean aeroportBean, final ModelAndView model, final HttpServletRequest request) {
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
	public AdminRequestBean editAirport(final AdminRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
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
	public AdminRequestBean deleteAirport(final AdminRequestBean aeroport, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("deleteAirport - " + aeroport.getId());
		try {
			final Aeroport airportToDelete = aeroportDAO.getAirportById(aeroport.getId());
			aeroportDAO.deleteCountry(airportToDelete);
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}

		return aeroport;
	}

	@Override
	public AdminRequestBean addRoute(final AdminRequestBean routeBean, final ModelAndView model, final HttpServletRequest request) {
		System.out.println(routeBean);
		try {
			final Aeroport airportFrom = aeroportDAO.getAirportById(routeBean.getId());
			final Aeroport airportTo = aeroportDAO.getAirportById(routeBean.getIdTara());

			final Cursa route = new Cursa();
			route.setAeroport_1(airportFrom);
			route.setAeroport_2(airportTo);
			route.setDistanta(routeBean.getDistanta());

			cursaDAO.inserRoute(route);
			routeBean.setIdRoute(route.getId());
			routeBean.setDenumireTara(airportTo.getDenumire());
			System.out.println("Insert OK");
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}
		return routeBean;
	}

	@Override
	public AdminRequestBean deleteRoute(final AdminRequestBean adminRequestBean, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("start deleteRoute" + adminRequestBean.getId());
		final Cursa routeToDelete = cursaDAO.getRouteById(adminRequestBean.getId());
		cursaDAO.deleteRoute(routeToDelete);
		return adminRequestBean;
	}

	@Override
	public AdminRequestBean addFlight(final AdminRequestBean adminRequestBean, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Start - addFlight");
		System.out.println(adminRequestBean);
		final Zbor flight = new Zbor();
		
		flight.setCursa(cursaDAO.getRouteById(adminRequestBean.getIdRoute()));
		flight.setDataPlecare(adminRequestBean.getDepartureDate());
		flight.setDataSosire(adminRequestBean.getArrivalDate());
		flight.setCompanie(companieDAO.getCompanyById(adminRequestBean.getCompanyId()));
		flight.setAvion(zborDAO.getAirplaneById(adminRequestBean.getAirlineId()));
		flight.setPret(new BigDecimal(adminRequestBean.getStandardPrice()));
		zborDAO.insertFlight(flight);
		
		System.out.println("FLIGHT INSERTED");
		return adminRequestBean;
	}

	@Override
	public AdminRequestBean editFlight(final AdminRequestBean adminRequestBean, final ModelAndView model, final HttpServletRequest request) {
		System.out.println("Start - addFlight");
		System.out.println(adminRequestBean);
		
		try {
			final Zbor flight = zborDAO.getFlightById(adminRequestBean.getId());
			flight.setCursa(cursaDAO.getRouteById(adminRequestBean.getIdRoute()));
			flight.setDataPlecare(adminRequestBean.getDepartureDate());
			flight.setDataSosire(adminRequestBean.getArrivalDate());
			flight.setCompanie(companieDAO.getCompanyById(adminRequestBean.getCompanyId()));
			flight.setAvion(zborDAO.getAirplaneById(adminRequestBean.getAirlineId()));
			flight.setPret(new BigDecimal(adminRequestBean.getStandardPrice()));
			
			zborDAO.updateFlight(flight);
		} catch (final ApplicationException e) {
			e.printStackTrace();
		}
		
		return adminRequestBean;
	}

}
