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
			System.out.println(e.getMessage());
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
	
	public Usuario getUserById(int idUser) {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Usuario> listUsers = (List<Usuario>)session.createQuery("from Usuario where id = " + idUser).list();
		Usuario user = listUsers.size() > 0 ? (Usuario) listUsers.get(0) : null;
		conexion.cerrarConexion();
		
		return user;
	}

	@Override
	public boolean Delete(Usuario user) {
		
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		
		try {
			
			session.update(user);
			tx = session.getTransaction();
			tx.commit();
			
		} catch (Exception e) {
			
			aux=false;
			tx.rollback();
		}
		
		conexion.cerrarConexion();
		
		return aux;
		
	}

}
