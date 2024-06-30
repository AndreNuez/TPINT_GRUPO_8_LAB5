package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorABMLMedicos {

	@RequestMapping("ABMMedico.do")
	public ModelAndView eventoAMBMedico()
	{
		//Crear toda la logica para dara de alta al medico
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Principal");
		return MV;
	}
	
}
