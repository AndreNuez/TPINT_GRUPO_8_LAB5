package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Paciente;
import negocioImpl.PacienteNegocio;

@Controller
public class ControladorABMLPacientes {
	
	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;
	
	
	@RequestMapping("ABMPaciente.do")
	public ModelAndView eventoAMBPaciente(Paciente paciente, HttpSession session)
	{
		ModelAndView MV = new ModelAndView();
		paciente.setActivo(true);
		
		boolean confirmacion=pacienteNg.Add(paciente);
		MV.addObject("confirmacion",confirmacion);
		MV.setViewName("ABMPaciente");
		
		return MV;
	}
}
