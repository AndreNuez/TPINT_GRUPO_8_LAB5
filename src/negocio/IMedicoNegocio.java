package negocio;

import java.time.DayOfWeek;
import java.util.List;
import entidad.Medico;

public interface IMedicoNegocio {

	public boolean Add(Medico medico);
	
	public List<Medico> ReadAll();
	
	public boolean Update(Medico medico);
	
	public boolean Delete(Medico medico);
	
	public Medico obtenerMedicoPorLegajo(int legajo);

	public boolean exists(Medico medico);

	public boolean medicoAtiende(Medico medico, DayOfWeek dia, int hora);
}
