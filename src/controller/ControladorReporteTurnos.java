package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidad.EstadoTurno;
import entidad.Turno;
import negocioImpl.TurnoNegocio;

@Controller
public class ControladorReporteTurnos {
	
	@Autowired
	@Qualifier("servicioTurno")
	private TurnoNegocio turnoNg;

	@RequestMapping("buscarTurnos.do")
	public ModelAndView eventoBuscarTurnos(HttpSession session, String txtFechaInicio, String txtFechaFin ) {
		ModelAndView MV = new ModelAndView();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate fechaInicio = LocalDate.parse(txtFechaInicio, formatter);
	    LocalDate fechaFin = LocalDate.parse(txtFechaFin, formatter);
		
		EstadoTurno ausente = EstadoTurno.AUSENTE;
		EstadoTurno presente = EstadoTurno.PRESENTE;
		
		double porcPresentes = turnoNg.obtenerPorcentajeTurnos(presente, fechaInicio.toString(), fechaFin.toString());
		double porcAusentes = turnoNg.obtenerPorcentajeTurnos(ausente, fechaInicio.toString(), fechaFin.toString());

		MV.addObject("exito", true);
		MV.addObject("porcPresentes", porcPresentes);
		MV.addObject("porcAusentes", porcAusentes);
		
		
		MV.setViewName("ReporteTurnos");		
		return MV;
	}
}
