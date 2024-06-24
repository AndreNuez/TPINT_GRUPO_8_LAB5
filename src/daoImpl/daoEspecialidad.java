package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoEspecialidad;
import daoImpl.Conexion;
import entidad.Especialidad;

@Repository("daoEspecialidad")
public class daoEspecialidad implements IDaoEspecialidad {

	@Autowired
	private Conexion conexion;
	
	public List<Especialidad> ReadAll() {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Especialidad> list = (List<Especialidad>)session.createQuery("from Especialidad").list();
		
		conexion.cerrarConexion();
		
		return list;
	}
	
}
