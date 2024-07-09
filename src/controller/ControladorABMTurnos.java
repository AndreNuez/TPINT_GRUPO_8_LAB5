package controller;

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
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import negocioImpl.TurnoNegocio;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.PacienteNegocio;

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

	@RequestMapping("ABMTurno.do")
	public ModelAndView eventoABMTurnos(HttpSession session, Turno turno) {
		ModelAndView MV = new ModelAndView();
		turnoNg.Add(turno);

		// Pasar las listas al JSP

		/*
		 * MV.addObject("especialidades", especialidades); MV.addObject("medicos",
		 * medicos);
		 */

		// Establecer el nombre de la vista
		MV.setViewName("ABMTurno");

		return MV;
	}

	@RequestMapping(value = "buscarPacientePorDni.do", method = RequestMethod.GET)
	public ModelAndView buscarPacientePorDni(@RequestParam("dni") String dni, HttpSession session) {
	    ModelAndView MV = new ModelAndView("ABMTurno");
	    
	    // Mensaje de depuración: Imprimir el DNI recibido
	    if (dni == null || dni.isEmpty()) {
	        System.out.println("DNI ACA LLEGA");
	        // Puedes manejar esto según tus necesidades (por ejemplo, redirigir o mostrar un mensaje de error)
	    } else {
	        // Obtener el paciente
	        Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni);

	        // Mensaje de depuración: Verificar si el paciente fue encontrado
	        if (paciente != null) {
	            System.out.println("Paciente encontrado: " + paciente.getNombre() + " " + paciente.getApellido());
	            session.setAttribute("paciente", paciente);
	            MV.addObject("paciente", paciente);
	        } else {
	            System.out.println("Paciente no encontrado para DNI: " + dni);
	        }
	    }

	    return MV;
	}

}
