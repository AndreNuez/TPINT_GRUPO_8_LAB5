package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Especialidad;
import entidad.Nacionalidad;
import negocio.IEspecialidadNegocio;
import negocio.INacionalidadNegocio;
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

	@RequestMapping("AddMedico.do")
	public ModelAndView eventoRedireccionarPrincipal(String btnAgregarMedico, HttpSession session) {

		ModelAndView MV = new ModelAndView();

		MV.setViewName("ABMMedico");
		 List<Especialidad> especialidades = especialidadNegocio.ReadAll();
         List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
         
         MV.addObject("especialidades", especialidades);
         MV.addObject("nacionalidades", nacionalidades);
		return MV;

	}
}
