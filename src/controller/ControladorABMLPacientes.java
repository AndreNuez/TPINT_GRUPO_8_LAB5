package controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Localidad;
import entidad.Paciente;
import entidad.Provincia;
import negocioImpl.LocalidadNegocio;
import negocioImpl.PacienteNegocio;
import negocioImpl.ProvinciaNegocio;

@Controller
public class ControladorABMLPacientes {
	
	@Autowired
	@Qualifier("servicioPaciente")
	private PacienteNegocio pacienteNg;
	
	@Autowired
	@Qualifier("servicioProvincia")
	private ProvinciaNegocio provinciaNg;
	
	@Autowired
	@Qualifier("servicioLocalidad")
	private LocalidadNegocio localidadNg;
	
	@RequestMapping("ABMPaciente.do")
	public ModelAndView eventoAMBPaciente(@ModelAttribute("paciente") Paciente paciente, int selProvincia, int selLocalidad, HttpSession session, String btnGrabar, String btnActualizar)
	{
		ModelAndView MV = new ModelAndView();
		Localidad localidad= localidadNg.getLocalidadById(selLocalidad);
		Provincia provincia= provinciaNg.getProvinciaById(selProvincia);
		
		
		if (btnGrabar != null && btnGrabar.equals("Grabar")) {		
			
			if(pacienteNg.obtenerPacientePorDNI(paciente.getDni())==null) {
			paciente.setActivo(true);
			paciente.setProvincia(provincia);
			paciente.setLocalidad(localidad);
			boolean confirmacion = pacienteNg.Add(paciente);
			MV.addObject("confirmacion", confirmacion);
			List<Paciente> pacientes = pacienteNg.ReadAll();
			MV.addObject("pacientes", pacientes);
			MV.setViewName("ListarPacientes");
			}else {
				MV.addObject("error", true);
				List<Provincia> provincias = provinciaNg.ReadAll();
				List<Localidad> localidades = localidadNg.ReadAll();
				paciente.setProvincia(provincia);
				paciente.setLocalidad(localidad);
				MV.addObject("provincias", provincias);
				MV.addObject("localidades", localidades);
			}
			
		}
		else if (btnActualizar != null && btnActualizar.equals("Actualizar")) {			
			paciente.setActivo(true);
			paciente.setProvincia(provincia);
			paciente.setLocalidad(localidad);
			boolean modificacion = pacienteNg.Update(paciente);
			MV.addObject("modificacion", modificacion);
			List<Paciente> pacientes = pacienteNg.ReadAll();
			MV.addObject("pacientes", pacientes);
			MV.setViewName("ListarPacientes");			
		}
		return MV;
	}

}
