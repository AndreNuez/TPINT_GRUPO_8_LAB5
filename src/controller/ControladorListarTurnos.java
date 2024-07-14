package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entidad.Especialidad;
import entidad.Jornada;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import negocio.IEspecialidadNegocio;
import negocio.IJornadaNegocio;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.PacienteNegocio;
import negocioImpl.TurnoNegocio;



@Controller
public class ControladorListarTurnos {
	
	@Autowired
	@Qualifier("servicioTurno")
	private TurnoNegocio turnoNg;

	@Autowired
	@Qualifier("servicioEspecialidad")
	private EspecialidadNegocio especialidadNg;

	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;
	
	@Autowired
	@Qualifier("servicioEspecialidad")
	private IEspecialidadNegocio especialidadNegocio;

	@Autowired
	@Qualifier("servicioJornada")
	private IJornadaNegocio jornadaNegocio;
	
	@RequestMapping("AddTurno.do")
	public ModelAndView eventoRedireccionarPrincipal(HttpSession session, String btnAgregarTurno ) {
		ModelAndView MV = new ModelAndView();
		
		//  lista de turnos
		List<Turno> turnos = turnoNg.ReadAll();
		List<Especialidad> especialidades = especialidadNg.ReadAll();
		List<Medico> medicos = medicoNg.ReadAll();
		
		// Pasar las listas al JSP
		MV.addObject("turnos", turnos);
		MV.addObject("especialidades", especialidades);
		MV.addObject("medicos", medicos);
		MV.addObject("hayTurno", false);
		// Establecer el nombre de la vista
		MV.setViewName("ABMTurno");
		if (session.getAttribute("paciente") != null)
			session.setAttribute("paciente", null);
		return MV;
	}
	@RequestMapping("EditarTurno.do")
	public ModelAndView eventoEditarPaciente(@RequestParam("id") Long id, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		
		MV.addObject("mostrarCampos", true);
		MV.addObject("editar", true);
		
		Turno turno = (Turno) turnoNg.turnoPorId(id);
		List<Especialidad> especialidades = especialidadNegocio.ReadAll();
		List<Jornada> jornadas = jornadaNegocio.ReadAll();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(turno.getMedico().getLegajo());
		
		MV.addObject("especialidades", especialidades);
		MV.addObject("jornadas", jornadas);
		MV.addObject("medico", medico);

		
		
		MV.addObject("turno", turno);
		MV.setViewName("ABMTurno");
		return MV;
	}
	
	/*@RequestMapping("EliminarPaciente.do")
	public ModelAndView eventoEliminarPaciente(@RequestParam("dni") String dni, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Paciente paciente = pacienteNg.obtenerPacientePorId(dni); // Implementa este método en tu negocio
		pacienteNg.Delete(paciente);
		
		List<Paciente> pacientes = pacienteNg.ReadAll();
    	MV.addObject("pacientes", pacientes);
		MV.setViewName("ListarPacientes");
    	
		return MV;
	}*/
	
}
