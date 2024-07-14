package dao;

import java.util.List;

import entidad.EstadoTurno;
import entidad.Turno;

public interface IDaoTurno {
	
	public boolean Add(Turno turno);
	
	public List<Turno> ReadAll();
	
	public boolean Update(Turno turno);
	
	public boolean Delete(Turno turno);

	public List<Turno> searchTurnosMedico(int legajoMedico);

	public List<Turno> searchTurnosDiaHorario(String fecha, int hora);
	
	public double obtenerPorcentajeTurnos(EstadoTurno estado, String fechaInicio, String fechaFin);
	
	public long obtenerTotalTurnos(String fechaInicio, String fechaFin);
	
	public List<Turno> listadoTurnosPorFecha(String fechaInicio, String fechaFin);

	public Turno turnoPorId(Long id);
}
