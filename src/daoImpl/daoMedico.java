package daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import dao.IDaoMedico;
import daoImpl.Conexion;
import entidad.Medico;

@Repository("daoMedico")
public class daoMedico implements IDaoMedico {

	@Autowired
	private Conexion conexion;

	@Override
	public boolean Add(Medico medico) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		try {

			session.save(medico);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {
			aux = false;
			System.out.println(e.getMessage());
			tx.rollback();
		}
		conexion.cerrarConexion();

		return aux;

	}

	@Override
	public List<Medico> ReadAll() {

		Session session = conexion.abrirConexion();

		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Medico> list = (List<Medico>) session.createQuery("from Medico where Activo = true").list();
		// List<Medico> list = (List<Medico>) session
		// .createQuery("from Medico medico join medico.usuario usuario where
		// medico.activo = true").list();
		conexion.cerrarConexion();

		return list;
	}

	@Override
	public boolean Update(Medico medico) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		try {

			session.update(medico);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {

			aux = false;
			tx.rollback();
		}

		conexion.cerrarConexion();

		return aux;
	}

	@Override
	public boolean Delete(Medico medico) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		medico.setActivo(false);

		try {

			session.update(medico);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {

			aux = false;
			tx.rollback();
		}

		conexion.cerrarConexion();

		return aux;
	}

	// -------------------------------------------------//

	// Metodos tp4
	public List<Medico> mostrarMedicosOrdenadosPorLegajoDesc() {

		Session session = conexion.abrirConexion();

		@SuppressWarnings({ "unchecked" })
		List<Medico> listaMedicos = (List<Medico>) session.createQuery("FROM Medico m ORDER BY m.legajo DESC").list();

		conexion.cerrarConexion();
		return listaMedicos;
	}

	public List<Object[]> mostrarMedicosOrdenadosPorLegajoAsc() {

		Session session = conexion.abrirConexion();

		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Object[]> listaMedicos = session
				.createQuery("SELECT m.legajo, m.nombre, m.apellido FROM Medico m ORDER BY m.legajo ASC").list();

		conexion.cerrarConexion();

		return listaMedicos;
	}

	public List<Integer> obtenerLegajosMedicos() {

		Session session = conexion.abrirConexion();

		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Integer> listaLegajos = (List<Integer>) session.createQuery("SELECT m.legajo FROM Medico m").list();

		session.getTransaction().commit();
		conexion.cerrarConexion();

		return listaLegajos;
	}

	public int obtenerMaxLegajoMedico() {

		Session session = conexion.abrirConexion();

		session.beginTransaction();

		Integer maxLegajo = (Integer) session.createQuery("SELECT MAX(m.legajo) FROM Medico m").uniqueResult();

		session.getTransaction().commit();
		conexion.cerrarConexion();

		return maxLegajo != null ? maxLegajo : 0; // Devuelve 0 si no se encuentra ningún médico
	}

	// PUNTO 3

	public List<Object[]> MostrarListadoTurnosPorLegajoYFecha(int legajoMedico, String fecha) {

		Session session = conexion.abrirConexion();

		@SuppressWarnings({ "unchecked" })
		List<Object[]> listaTurnos = (List<Object[]>) session
				.createQuery("SELECT m.legajo, lt.fecha, lt.estado FROM Medico as m INNER JOIN m.listaTurnos lt "
						+ "WHERE m.legajo = :legajoMedico " + "AND lt.fecha = :fecha")
				.setParameter("legajoMedico", legajoMedico).setParameter("fecha", fecha).list();

		conexion.cerrarConexion();

		return listaTurnos;
	}

	@Override
	public Medico obtenerMedicoPorLegajo(int legajo) {

		Session session = conexion.abrirConexion();

		session.beginTransaction();
		Medico medico = (Medico) session.createQuery("from Medico where legajo = :legajo")
				.setParameter("legajo", legajo).uniqueResult();

		conexion.cerrarConexion();

		return medico;
	}

	@Override
	public Medico obtenerMedicoPorDNI(String dni) {
		Session session = conexion.abrirConexion();

		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		Medico medico = (Medico) session.createQuery("from Medico where dni = :dni").setParameter("dni", dni)
				.uniqueResult();

		conexion.cerrarConexion();

		return medico;
	}
}
