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
	public ArrayList<Jornada> ReadAll() {

		Session session = conexion.abrirConexion();
		try {

			session.beginTransaction();
			@SuppressWarnings({ "unchecked" })
			ArrayList<Jornada> jornadas = (ArrayList<Jornada>) session.createQuery("FROM Jornada").list();
			conexion.cerrarConexion();
			return jornadas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
