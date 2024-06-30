package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.Nacionalidad;
import negocio.INacionalidadNegocio;
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

		MV.setViewName("ABMPaciente");
		
        List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
     
        MV.addObject("nacionalidades", nacionalidades);
        
		return MV;

	}
}
