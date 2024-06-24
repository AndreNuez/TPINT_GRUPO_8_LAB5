package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoEspecialidad;
import daoImpl.daoEspecialidad;
import entidad.Especialidad;
import negocio.IEspecialidadNegocio;

@Service("servicioEspecialidad")
public class EspecialidadNegocio implements IEspecialidadNegocio {
	
	@Autowired
	private IDaoEspecialidad daoEspecialidad; 
	
	public EspecialidadNegocio() {
		
	}
	
	public EspecialidadNegocio(daoEspecialidad daoespe) {
		this.daoEspecialidad = daoespe;
	}
	
	public IDaoEspecialidad getDaoEspecialidad() {
		return daoEspecialidad;
	}

	public void setDaoEspecialidad(IDaoEspecialidad daoEspecialidad) {
		this.daoEspecialidad = daoEspecialidad;
	}
	
	// FIN SPRING CORE

	@Override
	public List<Especialidad> ReadAll() {
		
		return daoEspecialidad.ReadAll();
	}

}
