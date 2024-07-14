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
	public ModelAndView eventoBuscarTurnos(HttpSession session, String txtFechaInicio, String txtFechaFin) {
		ModelAndView MV = new ModelAndView();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaInicioFormatted = LocalDate.parse(txtFechaInicio, formatter).format(formatter);
		String fechaFinFormatted = LocalDate.parse(txtFechaFin, formatter).format(formatter);

		EstadoTurno ausente = EstadoTurno.AUSENTE;
		EstadoTurno presente = EstadoTurno.PRESENTE;

		double porcPresentes = turnoNg.obtenerPorcentajeTurnos(presente, fechaInicioFormatted, fechaFinFormatted);
		double porcAusentes = turnoNg.obtenerPorcentajeTurnos(ausente, fechaInicioFormatted, fechaFinFormatted);
		long total = turnoNg.obtenerTotalTurnos(fechaInicioFormatted, fechaFinFormatted);
		
		if (!Double.isNaN(porcPresentes) && !Double.isNaN(porcAusentes)) {
			MV.addObject("exito", true);
			MV.addObject("porcPresentes", porcPresentes);
			MV.addObject("porcAusentes", porcAusentes);
			MV.addObject("fechaInicioFormatted", fechaInicioFormatted);
			MV.addObject("total", total);
			MV.addObject("fechaFinFormatted", fechaFinFormatted);

		} else {
			MV.addObject("fechaInicioFormatted", fechaInicioFormatted);
			MV.addObject("fechaFinFormatted", fechaFinFormatted);
			MV.addObject("exito", false);
		}

		MV.setViewName("ReporteTurnos");
		return MV;
	}
}
