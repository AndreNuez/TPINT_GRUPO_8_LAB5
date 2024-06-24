package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoMedico;
import entidad.Medico;
import negocio.IMedicoNegocio;

@Service("servicioMedico")

public class MedicoNegocio implements IMedicoNegocio {
	
	@Autowired
	private IDaoMedico daoMedico;
	
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
		
		return daoMedico.Update(medico);
	}

	@Override
	public boolean Delete(Medico medico) {
		
		return daoMedico.Delete(medico);
	}

}
