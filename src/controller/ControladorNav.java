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


@Controller
public class ControladorNav {

    @Autowired
    @Qualifier("servicioEspecialidad")
    private IEspecialidadNegocio especialidadNegocio;
    
    @Autowired
    @Qualifier("servicioNacionalidad")
    private INacionalidadNegocio nacionalidadNegocio;

    @RequestMapping("navPages.do")
    public ModelAndView eventoRedireccionarPages(String inicioJsp, String medicosJsp, String pacientesJsp,
            String turnosJsp) {
        ModelAndView MV = new ModelAndView();

        if (inicioJsp != null && inicioJsp.equals("Inicio"))
            MV.setViewName("Principal");
        else if (medicosJsp != null && medicosJsp.equals("Medicos")) {
            MV.setViewName("ABMMedico");
            List<Especialidad> especialidades = especialidadNegocio.ReadAll();
            List<Nacionalidad> nacionalidades = nacionalidadNegocio.ReadAll();
            
            MV.addObject("especialidades", especialidades);
            MV.addObject("nacionalidades", nacionalidades);

        } else if (pacientesJsp != null && pacientesJsp.equals("Pacientes"))
            MV.setViewName("ABMPaciente");
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

