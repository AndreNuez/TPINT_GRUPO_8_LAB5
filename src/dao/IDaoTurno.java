package dao;

import java.util.List;
import entidad.Turno;

public interface IDaoTurno {
	
	public boolean Add(Turno turno);
	
	public List<Turno> ReadAll();
	
	public boolean Update(Turno turno);
	
	public boolean Delete(Turno turno);

	public List<Turno> searchTurnosMedico(int legajoMedico);

	public List<Turno> searchTurnosDiaHorario(String fecha, int hora);
}
