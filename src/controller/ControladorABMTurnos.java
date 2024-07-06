package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Turno;
import entidad.Especialidad;
import entidad.Medico;
import negocioImpl.TurnoNegocio;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.MedicoNegocio;

@Controller
public class ControladorABMTurnos {
	
	@Autowired
	@Qualifier("servicioTurno")
	private TurnoNegocio turnoNg;

	@Autowired
	@Qualifier("servicioEspecialidad")
	private EspecialidadNegocio especialidadNg;

	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;
	
	@RequestMapping("ABMTurnos.do")
	public ModelAndView eventoABMTurnos(HttpSession session) {
		ModelAndView MV = new ModelAndView();
		
		// Obtener la lista de turnos
		List<Turno> turnos = turnoNg.ReadAll();
		List<Especialidad> especialidades = especialidadNg.ReadAll();
		List<Medico> medicos = medicoNg.ReadAll();
		
		// Pasar las listas al JSP
		MV.addObject("turnos", turnos);
		MV.addObject("especialidades", especialidades);
		MV.addObject("medicos", medicos);
		
		// Establecer el nombre de la vista
		MV.setViewName("ListarTurnos");
		
		return MV;
	}
}
