package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import entidad.Turno;
import entidad.Especialidad;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.TurnoNegocio;

@Controller
public class ControladorReporteEspecialidades {

    @Autowired
    @Qualifier("servicioTurno")
    private TurnoNegocio turnoNg;
    
	@Autowired
	@Qualifier("servicioEspecialidad")
	private EspecialidadNegocio especialidadNg;

	@RequestMapping("reporteEspecialidades.do")
    public ModelAndView eventoBuscarTurnos(HttpSession session, String txtFechaInicio, String txtFechaFin) {
        ModelAndView MV = new ModelAndView();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaInicioFormatted = LocalDate.parse(txtFechaInicio, formatter).format(formatter);
        String fechaFinFormatted = LocalDate.parse(txtFechaFin, formatter).format(formatter);
        
		List<Turno> turnos = turnoNg.listadoTurnosPorFecha(fechaInicioFormatted, fechaFinFormatted);
		
        if (turnos == null || turnos.isEmpty()) {
            MV.addObject("exito", false);
            MV.addObject("fechaInicioFormatted", fechaInicioFormatted);
            MV.addObject("fechaFinFormatted", fechaFinFormatted);
            MV.setViewName("ReporteEspecialidades");
            return MV;
        }
        
		List<Especialidad> especialidades = especialidadNg.ReadAll();
        Map<String, Integer> contadorEspecialidades = new HashMap<>();
        int totalTurnos = turnos.size();

        if (turnos != null) {
            for (Turno turno : turnos) {
                String especialidadNombre = turno.getMedico().getEspecialidad().getNombre();
                contadorEspecialidades.put(especialidadNombre, contadorEspecialidades.getOrDefault(especialidadNombre, 0) + 1);
            }
        }

        List<Map<String, Object>> turnosPorEspecialidad = new ArrayList<>();

        for (Especialidad especialidad : especialidades) {
            String especialidadNombre = especialidad.getNombre();
            int cantidadTurnos = contadorEspecialidades.getOrDefault(especialidadNombre, 0);
            double porcentaje = (double) cantidadTurnos / totalTurnos * 100;
            double porcentajeFormateado = Math.round(porcentaje * 100.0) / 100.0;

            Map<String, Object> item = new HashMap<>();
            item.put("especialidadNombre", especialidadNombre);
            item.put("cantidadTurnos", cantidadTurnos);
            item.put("porcentaje", porcentajeFormateado); 
            turnosPorEspecialidad.add(item);
        }
                
        MV.addObject("exito", true);
        MV.addObject("turnosPorEspecialidad", turnosPorEspecialidad);
        MV.addObject("fechaInicioFormatted", fechaInicioFormatted);
        MV.addObject("fechaFinFormatted", fechaFinFormatted);
        MV.addObject("total", totalTurnos);
        
        MV.setViewName("ReporteEspecialidades");
        return MV;
    }
}
