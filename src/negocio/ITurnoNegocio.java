package negocio;

import java.util.List;

import entidad.Turno;

public interface ITurnoNegocio {
	
	public boolean Add(Turno turno);
	
	public List<Turno> ReadAll();
	
	public boolean Update(Turno turno);
	
	public boolean Delete(Turno turno);
}
