package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Paciente;
import negocio.INacionalidadNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.PacienteNegocio;



@Controller
public class ControladorListarPaciente {
	
	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;
	
    @Autowired
    @Qualifier("servicioNacionalidad")
    private INacionalidadNegocio nacionalidadNegocio;
  
	
	@RequestMapping("AddPaciente.do")
	public ModelAndView eventoRedireccionarPrincipal(String btnAgregarPaciente, HttpSession session) {

		ModelAndView MV = new ModelAndView();
        List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
        MV.addObject("nacionalidades", nacionalidades);
        MV.addObject("editar", false);
        MV.setViewName("ABMPaciente");
        
		return MV;
	}
	
	@RequestMapping("EditarPaciente.do")
	public ModelAndView eventoEditarPaciente(@RequestParam("dni") String dni, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni); // Implementa este método en tu negocio
		MV.addObject("paciente", paciente);
		
		MV.addObject("editar", true);
		MV.setViewName("ABMPaciente");
		return MV;
	}
	
	@RequestMapping("EliminarPaciente.do")
	public ModelAndView eventoEliminarPaciente(@RequestParam("dni") String dni, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Paciente paciente = pacienteNg.obtenerPacientePorDNI(dni); // Implementa este método en tu negocio
		pacienteNg.Delete(paciente);
		
		List<Paciente> pacientes = pacienteNg.ReadAll();
    	MV.addObject("pacientes", pacientes);
		MV.setViewName("ListarPacientes");
    	
		return MV;
	}
	
}
