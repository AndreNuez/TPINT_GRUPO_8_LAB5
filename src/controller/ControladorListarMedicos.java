package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import entidad.Provincia;
import negocio.IEspecialidadNegocio;
import negocio.IJornadaNegocio;
import negocio.ILocalidadNegocio;
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
		List<Jornada> jornadas = jornadaNegocio.ReadAll();
		List<Provincia> provincias = provinciaNegocio.ReadAll();
		List<Localidad> localidades = localidadNegocio.ReadAll();
		MV.addObject("provincias", provincias);
		MV.addObject("localidades", localidades);
		MV.addObject("especialidades", especialidades);
		MV.addObject("jornadas", jornadas);
		MV.addObject("editar", false);

		return MV;

	}

	@RequestMapping("EditarMedico.do")
	public ModelAndView eventoEditarMedico(@RequestParam("legajo") int legajo, HttpSession session)
			throws ParseException {
		ModelAndView MV = new ModelAndView();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(legajo);
		MV.addObject("medico", medico);
		MV.addObject("fecNacMed", fechaFormatoVista(medico.getfNac()));
		List<Especialidad> especialidades = especialidadNegocio.ReadAll();
		List<Jornada> jornadas = jornadaNegocio.ReadAll();
		List<Provincia> provincias = provinciaNegocio.ReadAll();
		List<Localidad> localidades = localidadNegocio.ReadAll();
		MV.addObject("provincias", provincias);
		MV.addObject("localidades", localidades);
		MV.addObject("especialidades", especialidades);
		MV.addObject("jornadas", jornadas);
		MV.addObject("editar", true);

		MV.setViewName("ABMMedico");

		return MV;
	}

	@RequestMapping("EliminarMedico.do")
	public ModelAndView eventoEliminarPaciente(@RequestParam("legajo") int legajo, HttpSession session) {
		ModelAndView MV = new ModelAndView();
		Medico medico = medicoNg.obtenerMedicoPorLegajo(legajo); // Implementa este método en tu negocio

		boolean eliminacion = medicoNg.Delete(medico);
		MV.addObject("eliminacion", eliminacion);

		List<Medico> medicos = medicoNg.ReadAll();
		MV.addObject("medicos", medicos);
		MV.setViewName("ListarMedicos");

		return MV;
	}

	private String fechaFormatoVista(String fecha) throws ParseException {
		String originalDateString = fecha;
		if (originalDateString.contains("/")) {
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Parse and format the date string
			Date parsedDate = originalFormat.parse(originalDateString);
			originalDateString = targetFormat.format(parsedDate);
		}
		return originalDateString;
	}

}
