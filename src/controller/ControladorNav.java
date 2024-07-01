package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import negocioImpl.MedicoNegocio;
import negocioImpl.PacienteNegocio;

@Controller
public class ControladorNav {
	
	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;

	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;

    @RequestMapping("navPages.do")
    public ModelAndView eventoRedireccionarPages(String inicioJsp, String medicosJsp, String pacientesJsp,
            String turnosJsp) {
        ModelAndView MV = new ModelAndView();

        if (inicioJsp != null && inicioJsp.equals("Inicio"))
            MV.setViewName("Principal");
        else if (medicosJsp != null && medicosJsp.equals("Medicos")) {
        	List<Medico> medicos = medicoNg.ReadAll();
        	MV.addObject("medicos", medicos);
            MV.setViewName("ListarMedicos");
           

        } else if (pacientesJsp != null && pacientesJsp.equals("Pacientes")) {
            MV.setViewName("ListarPacientes");
	    	List<Paciente> pacientes = pacienteNg.ReadAll();
	    	MV.addObject("pacientes", pacientes);
            MV.setViewName("ListarPacientes");

	    }
        
        else if (turnosJsp != null && turnosJsp.equals("Turnos"))
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

