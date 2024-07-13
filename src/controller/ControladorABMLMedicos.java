package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Especialidad;
import entidad.Jornada;
import entidad.Localidad;
import entidad.Medico;
import entidad.PerfilUsuario;
import entidad.Provincia;
import entidad.Usuario;
import negocio.IEspecialidadNegocio;
import negocio.IJornadaNegocio;
import negocioImpl.LocalidadNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.ProvinciaNegocio;
import negocioImpl.UsuarioNegocio;

@Controller
public class ControladorABMLMedicos {

	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;

	@Autowired
	@Qualifier("servicioUsuario")
	private UsuarioNegocio usuarioNg;

	@Autowired
	@Qualifier("servicioEspecialidad")
	private IEspecialidadNegocio especialidadNegocio;

	@Autowired
	@Qualifier("servicioJornada")
	private IJornadaNegocio jornadaNegocio;
	
	@Autowired
	@Qualifier("servicioProvincia")
	private ProvinciaNegocio provinciaNg;
	
	@Autowired
	@Qualifier("servicioLocalidad")
	private LocalidadNegocio localidadNg;

	@RequestMapping("ABMMedico.do")
	public ModelAndView eventoAMBMedico(Medico medico, int selProvincia, int selLocalidad, HttpSession session, String btnGrabar, String btnActualizar) {
		ModelAndView MV = new ModelAndView();
		boolean confirmacion = false;
		Localidad localidad= localidadNg.getLocalidadById(selLocalidad);
		Provincia provincia= provinciaNg.getProvinciaById(selProvincia);

		if (btnGrabar != null && btnGrabar.equals("Grabar")) {
			// primero se graba el usuario
			Usuario userMed = medico.getUsuario();
			userMed.setPerfil(PerfilUsuario.MEDICO.ordinal());
			medico.setActivo(true);
			userMed.setActivo(true);
			medico.setUsuario(userMed);
			medico.setProvincia(provincia);
			medico.setLocalidad(localidad);
			confirmacion = medicoNg.Add(medico);
			
			List<Medico> medicos = medicoNg.ReadAll();
			MV.addObject("medicos", medicos);

			MV.addObject("confirmacion", confirmacion);
			MV.setViewName("ListarMedicos");

		} else if (btnActualizar != null && btnActualizar.equals("Actualizar")) {
			
			medico.setProvincia(provincia);
			medico.setLocalidad(localidad);
			confirmacion = medicoNg.Update(medico);
			
			List<Medico> medicos = medicoNg.ReadAll();
			MV.addObject("medicos", medicos);

			MV.addObject("confirmacion", confirmacion);
			MV.setViewName("ListarMedicos");

		}
		
		return MV;
	}

}
