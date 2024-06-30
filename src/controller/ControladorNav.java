package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorNav {

	@RequestMapping("navPages.do")
	public ModelAndView eventoRedireccionarPages(String inicioJsp, String medicosJsp, String pacientesJsp,
			String turnosJsp) {
		ModelAndView MV = new ModelAndView();

		if (inicioJsp != null && inicioJsp.equals("Inicio"))
			MV.setViewName("Principal");
		if (medicosJsp != null && medicosJsp.equals("Medicos"))
			MV.setViewName("ABMMedico");
		if (pacientesJsp != null && pacientesJsp.equals("Pacientes"))
			MV.setViewName("ABMPaciente");
		if (turnosJsp != null && turnosJsp.equals("Turnos"))
			MV.setViewName("ABMTurnos");
		return MV;
	}

	@RequestMapping("navLogOut.do")
	public ModelAndView eventoRedireccionarLogOut(HttpSession session) {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Login");
		return MV;
	}
}
