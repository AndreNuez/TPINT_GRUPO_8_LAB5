package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entidad.Localidad;
import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Provincia;
import negocio.ILocalidadNegocio;
import negocio.INacionalidadNegocio;
import negocio.IProvinciaNegocio;
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
    
    @Autowired
	@Qualifier("servicioProvincia")
	private IProvinciaNegocio provinciaNegocio;
    
    @Autowired
	@Qualifier("servicioLocalidad")
	private ILocalidadNegocio localidadNegocio;
  
	
	@RequestMapping("AddPaciente.do")
	public ModelAndView eventoRedireccionarPrincipal(String btnAgregarPaciente, HttpSession session) {

		ModelAndView MV = new ModelAndView();
        List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
        List<Provincia> provincias = provinciaNegocio.ReadAll();
        List<Localidad> localidades = localidadNegocio.ReadAll();
        MV.addObject("provincias", provincias);
        MV.addObject("localidades", localidades);
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
		
		List<Provincia> provincias = provinciaNegocio.ReadAll();
        List<Localidad> localidades = localidadNegocio.ReadAll();
        MV.addObject("provincias", provincias);
        MV.addObject("localidades", localidades);
        
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
