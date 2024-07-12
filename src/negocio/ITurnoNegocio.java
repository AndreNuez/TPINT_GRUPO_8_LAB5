package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.EstadoTurno;
import entidad.Medico;
import entidad.Turno;

public interface ITurnoNegocio {
	
	public boolean Add(Turno turno);
	
	public List<Turno> ReadAll();
	
	public boolean Update(Turno turno);
	
	public boolean Delete(Turno turno);

	public ArrayList<Integer> turnosDisponiblesMedicoFecha(Medico medico, String fecha);
	
	public ArrayList<Turno> readTurnosMedico(Medico medico);
	
	public boolean turnoValido(Turno turno);
	
	public double obtenerPorcentajeTurnos(EstadoTurno estado, String fechaInicio, String fechaFin);
	
	public long obtenerTotalTurnos(String fechaInicio, String fechaFin);
}
