package controller;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import javax.servlet.http.HttpSession;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Qualifier;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;
	
	import entidad.Medico;
	import entidad.Paciente;
import entidad.PerfilUsuario;
import entidad.Turno;
	import entidad.Usuario;
	import negocioImpl.MedicoNegocio;
	import negocioImpl.PacienteNegocio;
	import negocioImpl.TurnoNegocio;
	@Controller
	public class ControladorNav {
		
		@Autowired
		@Qualifier("servicioMedico")
		private MedicoNegocio medicoNg;
	
		@Autowired
		@Qualifier("servicioPaciente")
		private PacienteNegocio pacienteNg;
		
		 @Autowired
		 @Qualifier("servicioTurno")
		 private TurnoNegocio turnoNg;
		 
	
	    @RequestMapping("navPages.do")
	    public ModelAndView eventoRedireccionarPages(String inicioJsp, String medicosJsp, String pacientesJsp,
	            String turnosJsp, String reporteTurnosJsp, HttpSession session) {
	        ModelAndView MV = new ModelAndView();
	        Usuario user = (Usuario) session.getAttribute("user");
	        
	        if (inicioJsp != null && inicioJsp.equals("Inicio"))
	            MV.setViewName("Principal");
	        else if (medicosJsp != null && medicosJsp.equals("Medicos")) {
	        	List<Medico> medicos = medicoNg.ReadAll();
	        	MV.addObject("medicos", medicos);
	            MV.setViewName("ListarMedicos");
	            if (session.getAttribute("medico") != null)
	     			session.setAttribute("medico", null);
	
	        } else if (pacientesJsp != null && pacientesJsp.equals("Pacientes")) {
		    	List<Paciente> pacientes = pacienteNg.ReadAll();
		    	MV.addObject("pacientes", pacientes);
	            MV.setViewName("ListarPacientes");
	            if (session.getAttribute("paciente") != null)
	    			session.setAttribute("paciente", null);
	
		    }
	        
	        else if (turnosJsp != null && turnosJsp.equals("Turnos")) {
	            List<Turno> turnos = turnoNg.ReadAll();
	          
	            turnos = filtrarTurnosPorMedico(turnos, user);
	            
	            MV.addObject("turnos", turnos);
	            MV.setViewName("ListarTurnos");
	        }
	        
	        else if (reporteTurnosJsp != null && reporteTurnosJsp.equals("ReporteTurnos")) {
	        	MV.addObject("exito", null);
	        	MV.setViewName("ReporteTurnos");
	        }
	        return MV;
	    }
	
	    @RequestMapping("navLogOut.do")
	    public ModelAndView eventoRedireccionarLogOut(HttpSession session) {
	        ModelAndView MV = new ModelAndView();
	        MV.setViewName("Login");
	        return MV;
	    }
	    
	    private List<Turno> filtrarTurnosPorMedico(List<Turno> turnos, Usuario user) {
	        List<Turno> turnosFiltrados = new ArrayList<>();
	
	        for (Turno turno : turnos) {
	            if (turno.getMedico().getUsuario().getId() == user.getId() || user.getPerfil()== PerfilUsuario.ADMINISTRADOR.getPerfilUsuario()) {
	                turnosFiltrados.add(turno);
	            }
	        }
	
	        return turnosFiltrados;
	    }
	}



