package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoMedico;
import dao.IDaoUsuario;
import entidad.Medico;
import entidad.Usuario;
import negocio.IMedicoNegocio;

@Service("servicioMedico")

public class MedicoNegocio implements IMedicoNegocio {
	
	@Autowired
	private IDaoMedico daoMedico;
	
	@Autowired
	private IDaoUsuario daoUsuario;
	
	public MedicoNegocio() {
		
	}
	
	public IDaoMedico getDaoMedico() {
		return daoMedico;
	}

	public void setDaoMedico(IDaoMedico daoMedico) {
		this.daoMedico = daoMedico;
	}
	
	// FIN SPRING CORE
	
	@Override
	public boolean Add(Medico medico) {
		
		return daoMedico.Add(medico);
	}

	@Override
	public List<Medico> ReadAll() {
		
		return daoMedico.ReadAll();
	}

	@Override
	public boolean Update(Medico medico) {
		medico.setActivo(true);
		medico.getUsuario().setActivo(true);
		return daoMedico.Update(medico);
	}

	@Override
	public boolean Delete(Medico medico) {
		
		Usuario user = medico.getUsuario();
		user.setActivo(false); 
		
		if (daoMedico.Delete(medico) && daoUsuario.Delete(user)) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public Medico obtenerMedicoPorLegajo(int legajo) {
		
		return daoMedico.obtenerMedicoPorLegajo(legajo);
	}

}
