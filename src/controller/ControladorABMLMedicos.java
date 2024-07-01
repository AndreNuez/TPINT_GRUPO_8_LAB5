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
import negocio.IEspecialidadNegocio;
import negocioImpl.MedicoNegocio;



@Controller
public class ControladorABMLMedicos {
	
	@Autowired
	@Qualifier("servicioMedico")
	private MedicoNegocio medicoNg;
	
    @Autowired
    @Qualifier("servicioEspecialidad")
    private IEspecialidadNegocio especialidadNegocio;
	
	@RequestMapping("ABMMedico.do")
	public ModelAndView eventoAMBMedico(Medico medico, HttpSession session)
	{
		ModelAndView MV = new ModelAndView();
			List<Especialidad> especialidades = especialidadNegocio.ReadAll();

			medico.setActivo(true);
			
			boolean confirmacion=medicoNg.Add(medico);
	        MV.addObject("especialidades", especialidades);
			MV.addObject("confirmacion",confirmacion);

		MV.setViewName("ABMMedico");
		return MV;
	}
	
}
