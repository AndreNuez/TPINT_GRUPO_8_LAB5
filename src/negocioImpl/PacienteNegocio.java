package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoPaciente;
import entidad.Paciente;
import negocio.IPacienteNegocio;

@Service("servicioPaciente")
public class PacienteNegocio implements IPacienteNegocio {
	
	@Autowired
	private IDaoPaciente daoPaciente;
	
	public PacienteNegocio() {
		
	}
	
	
	public IDaoPaciente getDaoPaciente() {
		return daoPaciente;
	}


	public void setDaoPaciente(IDaoPaciente daoPaciente) {
		this.daoPaciente = daoPaciente;
	}
	
	// FIN SPRING CORE
	
	
	@Override
	public boolean Add(Paciente paciente) {
		
		return daoPaciente.Add(paciente);
	}

	@Override
	public List<Paciente> ReadAll() {
	
		return daoPaciente.ReadAll();
	}

	@Override
	public boolean Update(Paciente paciente) {
		
		return daoPaciente.Update(paciente);
	}

	@Override
	public boolean Delete(Paciente paciente) {
		
		return daoPaciente.Delete(paciente);
	}


	@Override
	public Paciente obtenerPacientePorDNI(String dni) {
		return daoPaciente.obtenerPacientePorDNI(dni);
	}

}
