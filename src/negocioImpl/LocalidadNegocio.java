package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoLocalidad;
import entidad.Localidad;
import negocio.ILocalidadNegocio;

@Service("servicioLocalidad")
public class LocalidadNegocio implements ILocalidadNegocio {

	@Autowired
	private IDaoLocalidad daoLocalidad;
	
	public LocalidadNegocio () {
		
	}

	public IDaoLocalidad getDaoLocalidad() {
		return daoLocalidad;
	}

	public void setDaoLocalidad(IDaoLocalidad daoLocalidad) {
		this.daoLocalidad = daoLocalidad;
	}
	
	// FIN SPRING CORE

	@Override
	public List<Localidad> ReadAll() {
		
		return daoLocalidad.ReadAll();
	}

	@Override
	public List<Localidad> getLocalidadesByProvinciaId(int provinciaId) {
		
		return daoLocalidad.getLocalidadesByProvinciaId(provinciaId);
	}
	
	@Override
	public Localidad getLocalidadById(int localidadId) {
		
		return daoLocalidad.getLocalidadById(localidadId);
	}

}
