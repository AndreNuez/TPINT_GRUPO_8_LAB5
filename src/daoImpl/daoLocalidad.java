package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoLocalidad;
import entidad.Localidad;

@Repository("daoLocalidad")
public class daoLocalidad implements IDaoLocalidad {

	@Autowired
	private Conexion conexion;
	
	@Override
	public List<Localidad> ReadAll() {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Localidad> list = (List<Localidad>)session.createQuery("from Localidad").list();
		
		conexion.cerrarConexion();
		
		return list;
	}

	@Override
	public List<Localidad> getLocalidadesByProvinciaId(int provinciaId) {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Localidad> list = (List<Localidad>)session.createQuery("from Localidad where Id_Provincia = " + provinciaId).list();
		
		conexion.cerrarConexion();
		
		return list;
	}
	
	@Override
	public Localidad getLocalidadById(int localidadId) {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		
		Localidad localidad = (Localidad) session.createQuery("from Localidad where id = " + localidadId).uniqueResult();
		
		conexion.cerrarConexion();
		
		return localidad;
	}
}
