package negocioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.IDaoNacionalidad;
import entidad.Nacionalidad;
import negocio.INacionalidadNegocio;

@Service("servicioNacionalidad")
public class NacionalidadNegocio implements INacionalidadNegocio {
	
	@Autowired
	private IDaoNacionalidad daoNacionalidad;
	
	
	public NacionalidadNegocio() {
		
	}

	public IDaoNacionalidad getDaoNacionalidad() {
		return daoNacionalidad;
	}


	public void setDaoNacionalidad(IDaoNacionalidad daoNacionalidad) {
		this.daoNacionalidad = daoNacionalidad;
	}


	@Override
	public List<Nacionalidad> ReadAll() {
		
		return daoNacionalidad.ReadAll();
	}

}
