package it.rezervare.beans.helper.helperinterface;

import org.springframework.web.servlet.ModelAndView;

import it.rezervare.beans.model.requestBeans.CursaRequestView;

public interface IDijkstraAlgorithmHelper {

	ModelAndView getRoutWithDijkstraAlgorithm(ModelAndView model, CursaRequestView cursa);

}
