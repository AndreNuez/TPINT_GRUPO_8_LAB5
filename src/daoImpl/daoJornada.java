package daoImpl;

import java.util.ArrayList;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoJornada;
import entidad.Jornada;

@Repository("daoJornada")
public class daoJornada implements IDaoJornada {

	@Autowired
	private Conexion conexion;

	@Override
	public ArrayList<Jornada> readAll(int estado) {
		
		Session session = conexion.abrirConexion();
		ArrayList<Jornada> jornadas = null;
		String query = "FROM Jornadas j";			
		query = estado == 0?" WHERE j.estado = 0":" WHERE j.estado = 1";			
		try {			
			
			session.beginTransaction();
			@SuppressWarnings({ "unchecked" })			
			ArrayList<Jornada> jorns = (ArrayList<Jornada>)session.createQuery(query).list();
			if (jorns.size()>0)
				jornadas = jorns;
			conexion.cerrarConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jornadas;
	}
}
