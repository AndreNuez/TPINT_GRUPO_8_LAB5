package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.IDaoPaciente;
import entidad.Medico;
import entidad.Paciente;

@Repository("daoPaciente")
public class daoPaciente implements IDaoPaciente {
	
	@Autowired
	private Conexion conexion;
	
	public boolean Add(Paciente paciente) {
		
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		
		try {
			
			session.save(paciente);
			tx = session.getTransaction();
			tx.commit();
			
		} catch (Exception e) {
			aux=false;
			tx.rollback();
		}
		conexion.cerrarConexion();
		
		return aux;
		
	}
	
	public List<Paciente> ReadAll() {
		
		Session session = conexion.abrirConexion();
		
		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Paciente> list = (List<Paciente>)session.createQuery("from Paciente").list();
		
		conexion.cerrarConexion();
		
		return list;
	}
	
	public boolean Update(Paciente paciente) {
		
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		
		try {
			
			session.update(paciente);
			tx = session.getTransaction();
			tx.commit();
			
		} catch (Exception e) {
			
			aux=false;
			tx.rollback();
		}
		
		conexion.cerrarConexion();
		
		return aux;
	}
	
	public boolean Delete(Paciente paciente) {
		
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		
		try {
			
			session.delete(paciente);
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
