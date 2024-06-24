package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoUsuario;
import entidad.Usuario;

@Repository("daoUsuario")
public class daoUsuario implements IDaoUsuario {
	
	@Autowired
	private Conexion conexion;
	
	public boolean Add(Usuario usuario) {
		
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		
		try {
			
			session.save(usuario);
			tx = session.getTransaction();
			tx.commit();
			
		} catch (Exception e) {
			aux=false;
			tx.rollback();
		}
		conexion.cerrarConexion();
		
		return aux;
	}

	public List<Usuario> ReadAll() {
	
		Session session = conexion.abrirConexion();
	
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Usuario> list = (List<Usuario>)session.createQuery("from Usuario").list();
		
		conexion.cerrarConexion();
		
		return list;
	}
}
