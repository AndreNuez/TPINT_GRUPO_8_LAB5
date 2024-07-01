package negocio;

import java.util.List;
import entidad.Medico;

public interface IMedicoNegocio {

	public boolean Add(Medico medico);
	
	public List<Medico> ReadAll();
	
	public boolean Update(Medico medico);
	
	public boolean Delete(Medico medico);
	
	public Medico obtenerMedicoPorLegajo(int legajo);
}
