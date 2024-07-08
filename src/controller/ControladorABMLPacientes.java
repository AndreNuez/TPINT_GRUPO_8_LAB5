package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entidad.Paciente;
import negocioImpl.PacienteNegocio;

@Controller
public class ControladorABMLPacientes {
	
	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;
	
	
	@RequestMapping("ABMPaciente.do")
	public ModelAndView eventoAMBPaciente(Paciente paciente, HttpSession session, String btnGrabar, String btnActualizar)
	{
		ModelAndView MV = new ModelAndView();
		paciente.setActivo(true);
		if (btnGrabar != null && btnGrabar.equals("Grabar")) {
			boolean confirmacion = pacienteNg.Add(paciente);
			MV.addObject("confirmacion", confirmacion);
			List<Paciente> pacientes = pacienteNg.ReadAll();
			MV.addObject("pacientes", pacientes);
			MV.setViewName("ListarPacientes");
		}else if (btnActualizar != null && btnActualizar.equals("Actualizar"))
		{			
			boolean confirmacion = pacienteNg.Update(paciente);
			MV.addObject("confirmacion", confirmacion);
			List<Paciente> pacientes = pacienteNg.ReadAll();
			MV.addObject("pacientes", pacientes);
			MV.setViewName("ListarPacientes");
			
		}
		return MV;
	}

}
