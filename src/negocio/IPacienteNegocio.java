package negocio;

import java.util.List;

import entidad.Paciente;

public interface IPacienteNegocio {
	
	public boolean Add(Paciente paciente);
	
	public List<Paciente> ReadAll();
	
	public boolean Update(Paciente paciente);
	
	public boolean Delete(Paciente paciente);
	
	public Paciente obtenerPacientePorDNI(String dni);

}
