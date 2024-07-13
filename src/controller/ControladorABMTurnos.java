package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entidad.Turno;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.PacienteNegocio;
import negocioImpl.TurnoNegocio;
import entidad.Especialidad;
import entidad.EstadoTurno;
import entidad.Medico;
import entidad.Paciente;

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

	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;
	
	@Autowired
	private Turno turno;
	
	@RequestMapping("ABMTurno.do")
	public ModelAndView eventoABMTurnos(HttpSession session, String dni, int legajo, int selHora, String txtFechaReserva,
			String btnGrabar, String btnActualizar) {
		ModelAndView MV = new ModelAndView();

		// Obtener lista de especialidades
		Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni);
		Medico medico = medicoNg.obtenerMedicoPorLegajo(legajo);
		
		turno.setEstado(EstadoTurno.PENDIENTE);
		turno.setFecha(txtFechaReserva);
		turno.setHora(selHora);
		turno.setPaciente(paciente);
		turno.setMedico(medico);		
		turno.setObservacion("");
		boolean confirmacion = turnoNg.Add(turno, medicoNg, pacienteNg);

		List<Turno> turnos = turnoNg.ReadAll();		

		MV.addObject("turnos", turnos);
		MV.addObject("confirmacion", confirmacion);
		MV.setViewName("ListarTurnos");

		return MV;
	}

	@RequestMapping(value = "buscarPacientePorDni.do", method = RequestMethod.POST)
	public ModelAndView buscarPacientePorDniPost(@RequestParam("dni") String dni, HttpSession session) {
		ModelAndView MV = new ModelAndView("ABMTurno");
		List<Especialidad> especialidades = especialidadNg.ReadAll();

		if (dni != null && !dni.isEmpty()) {
			Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni);
			if (paciente != null) {
				session.setAttribute("paciente", paciente);
				MV.addObject("paciente", paciente);
				MV.addObject("especialidades", especialidades);
				MV.addObject("mostrarCampos", true);
			} else {
				MV.addObject("error", "El dni " + dni + " no corresponde a un Paciente.");
			}
		} else {
			MV.addObject("error", "Ingrese un DNI");
		}

		/*
		 * List<Especialidad> especialidades = especialidadNg.ReadAll();
		 * MV.addObject("especialidades", especialidades);
		 */

		List<Medico> medicos = medicoNg.ReadAll();
		MV.addObject("medicos", medicos);
		MV.addObject("hayTurno", false);
		return MV;
	}

	@RequestMapping("buscarFecha.do")
	public ModelAndView buscarFechaABMTurnos(String txtFechaReserva, int selMedico, HttpSession session, Turno turno) {

		ModelAndView MV = new ModelAndView();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(selMedico);
		ArrayList<Integer> horasTurnos = turnoNg.turnosDisponiblesMedicoFecha(medico, txtFechaReserva);
		boolean hayTurno = horasTurnos.size() > 0 ? true : false;
		Paciente paciente = (Paciente) session.getAttribute("paciente");
		MV.addObject("paciente", paciente);
		MV.addObject("hayTurno", hayTurno);
		MV.addObject("mostrarCampos", true);
		if (!hayTurno) {
			MV.addObject("cantTurnos", "El medico " + medico.getApellido() + " no dispone de turnos para la fecha");
			List<Especialidad> especialidades = especialidadNg.ReadAll();
			MV.addObject("especialidades", especialidades);
			List<Medico> medicos = medicoNg.ReadAll();
			MV.addObject("medicos", medicos);
		}
		if (hayTurno) {
			MV.addObject("cantTurnos",
					"El medico " + medico.getApellido() + " cantidad de turnos para la fecha: " + horasTurnos.size());
			MV.addObject("medico", medico);
			MV.addObject("horasTurnos", horasTurnos);
			MV.addObject("fechaReserva", txtFechaReserva);
		}

		MV.setViewName("ABMTurno");
		return MV;
	}
}
