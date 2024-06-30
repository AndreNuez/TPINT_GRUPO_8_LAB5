package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorABMLPacientes {

	@RequestMapping("ABMPaciente.do")
	public ModelAndView eventoAMBMedico()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Principal");
		return MV;
	}
}
