package dao;

import java.util.List;

import entidad.Localidad;

public interface IDaoLocalidad {

	public List<Localidad> ReadAll();

	public List<Localidad> getLocalidadesByProvinciaId(int provinciaId);

	public Localidad getLocalidadById(int localidadId);
}
