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
import entidad.Localidad;
import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Provincia;
import negocio.IEspecialidadNegocio;
import negocio.IJornadaNegocio;
import negocio.ILocalidadNegocio;
import negocio.INacionalidadNegocio;
import negocio.IProvinciaNegocio;
import negocioImpl.MedicoNegocio;

@Controller
public class ControladorListarMedicos {

	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;

	@Autowired
	@Qualifier("servicioEspecialidad")
	private IEspecialidadNegocio especialidadNegocio;

	@Autowired
	@Qualifier("servicioNacionalidad")
	private INacionalidadNegocio nacionalidadNegocio;

	@Autowired
	@Qualifier("servicioJornada")
	private IJornadaNegocio jornadaNegocio;
	
	@Autowired
	@Qualifier("servicioProvincia")
	private IProvinciaNegocio provinciaNegocio;
    
    @Autowired
	@Qualifier("servicioLocalidad")
	private ILocalidadNegocio localidadNegocio;

	@RequestMapping("AddMedico.do")
	public ModelAndView eventoRedireccionarPrincipal(String btnAgregarMedico, HttpSession session) {

		ModelAndView MV = new ModelAndView();

		MV.setViewName("ABMMedico");
		List<Especialidad> especialidades = especialidadNegocio.ReadAll();
		List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
		List<Jornada> jornadas = jornadaNegocio.ReadAll();
		List<Provincia> provincias = provinciaNegocio.ReadAll();
        List<Localidad> localidades = localidadNegocio.ReadAll();
        MV.addObject("provincias", provincias);
        MV.addObject("localidades", localidades);
		MV.addObject("especialidades", especialidades);
		MV.addObject("nacionalidades", nacionalidades);
		MV.addObject("jornadas", jornadas);
		MV.addObject("editar", false);
		
		return MV;

	}

	@RequestMapping("EditarMedico.do")
	public ModelAndView eventoEditarMedico(@RequestParam("legajo") int legajo, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(legajo);
		MV.addObject("medico", medico);
		
		List<Especialidad> especialidades = especialidadNegocio.ReadAll();
		List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
		List<Jornada> jornadas = jornadaNegocio.ReadAll();
		List<Provincia> provincias = provinciaNegocio.ReadAll();
        List<Localidad> localidades = localidadNegocio.ReadAll();
        MV.addObject("provincias", provincias);
        MV.addObject("localidades", localidades);
		MV.addObject("especialidades", especialidades);
		MV.addObject("nacionalidades", nacionalidades);
		MV.addObject("jornadas", jornadas);
		MV.addObject("editar", true);
		MV.setViewName("ABMMedico");
		return MV;
	}

	@RequestMapping("EliminarMedico.do")
	public ModelAndView eventoEliminarPaciente(@RequestParam("legajo") int legajo, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(legajo); // Implementa este m�todo en tu negocio
		
		boolean eliminacion = medicoNg.Delete(medico);
		MV.addObject("eliminacion", eliminacion);

		List<Medico> medicos = medicoNg.ReadAll();
		MV.addObject("medicos", medicos);
		MV.setViewName("ListarMedicos");

		return MV;
	}
}
