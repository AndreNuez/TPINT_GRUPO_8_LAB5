package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoProvincia;
import entidad.Provincia;

@Repository("daoProvincia")
public class daoProvincia implements IDaoProvincia {
	
	@Autowired
	private Conexion conexion;

	@Override
	public List<Provincia> ReadAll() {
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Provincia> list = (List<Provincia>)session.createQuery("from Provincia").list();
		
		conexion.cerrarConexion();
		
		return list;
	}
	
	@Override
	public Provincia getProvinciaById(int provinciaId) {
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		
		Provincia provincia = (Provincia) session.createQuery("from Provincia where id=" + provinciaId).uniqueResult();
		
		conexion.cerrarConexion();
		
		return provincia;
	}

	
}
