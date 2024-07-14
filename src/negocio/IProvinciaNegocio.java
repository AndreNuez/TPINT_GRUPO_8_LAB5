package negocio;

import java.util.List;

import entidad.Provincia;

public interface IProvinciaNegocio {

	public List<Provincia> ReadAll();
	public Provincia getProvinciaById(int provinciaId); 
}
