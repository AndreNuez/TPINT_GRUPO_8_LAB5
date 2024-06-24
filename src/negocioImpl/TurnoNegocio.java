package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoTurno;
import entidad.Turno;
import negocio.ITurnoNegocio;

@Service("servicioTurno")
public class TurnoNegocio implements ITurnoNegocio {
	
	@Autowired
	private IDaoTurno daoTurno;
	
	public TurnoNegocio() {
		
	}

	public IDaoTurno getDaoTurno() {
		return daoTurno;
	}


	public void setDaoTurno(IDaoTurno daoTurno) {
		this.daoTurno = daoTurno;
	}
	
	//FIN SPRING CORE

	@Override
	public boolean Add(Turno turno) {
		
		return daoTurno.Add(turno);
	}

	@Override
	public List<Turno> ReadAll() {
		
		return daoTurno.ReadAll();
	}

	@Override
	public boolean Update(Turno turno) {
		
		return daoTurno.Update(turno);
	}

	@Override
	public boolean Delete(Turno turno) {
		
		return daoTurno.Delete(turno);
	}

}
