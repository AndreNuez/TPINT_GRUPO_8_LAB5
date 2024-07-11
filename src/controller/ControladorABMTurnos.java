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

        // Obtener lista de especialidades
        List<Especialidad> especialidades = especialidadNg.ReadAll();
        List<Medico> medicos = medicoNg.ReadAll();
	    
        
        
        
        MV.addObject("especialidades", especialidades);
        MV.addObject("medicos", medicos);
 
        MV.setViewName("ABMTurno");

        return MV;
    }

	@RequestMapping(value = "buscarPacientePorDni.do", method = RequestMethod.POST)
	public ModelAndView buscarPacientePorDniPost(@RequestParam("dni") String dni, HttpSession session) {
	    ModelAndView MV = new ModelAndView("ABMTurno");
	    
	    if (dni != null && !dni.isEmpty()) {
	        Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni);
	        if (paciente != null) {
	            session.setAttribute("paciente", paciente);
	            MV.addObject("paciente", paciente);
	        } else {
	            MV.addObject("error", "El dni " + dni + " no corresponde a un Paciente.");
	        }
	    } else {
	        MV.addObject("error", "Ingrese un DNI");
	    }
	    
	    List<Especialidad> especialidades = especialidadNg.ReadAll();
	    MV.addObject("especialidades", especialidades);
	    
	    List<Medico> medicos = medicoNg.ReadAll();
	    MV.addObject("medicos", medicos);
	    
	    return MV;
	}


}
