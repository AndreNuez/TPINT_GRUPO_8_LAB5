package daoImpl;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import dao.IDaoTurno;
import entidad.EstadoTurno;
import entidad.Turno;

@Repository("daoTurno")
public class daoTurno implements IDaoTurno {

	@Autowired
	private Conexion conexion;

	public boolean Add(Turno turno) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		try {

			session.save(turno);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {
			aux = false;
			tx.rollback();
		}
		conexion.cerrarConexion();

		return aux;

	}

	public List<Turno> ReadAll() {

		Session session = conexion.abrirConexion();

		session.beginTransaction();
		@SuppressWarnings({ "unchecked" })
		List<Turno> list = (List<Turno>) session.createQuery("from Turno").list();

		conexion.cerrarConexion();

		return list;
	}

	public boolean Update(Turno turno) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		try {

			session.update(turno);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {

			aux = false;
			tx.rollback();
		}

		conexion.cerrarConexion();

		return aux;
	}

	public boolean Delete(Turno turno) {

		Session session = conexion.abrirConexion();
		Transaction tx = session.beginTransaction();
		boolean aux = true;

		try {

			session.delete(turno);
			tx = session.getTransaction();
			tx.commit();

		} catch (Exception e) {

			aux = false;
			tx.rollback();
		}

		conexion.cerrarConexion();

		return aux;
	}

	// -------------------------------------------//

	// punto6
	public double obtenerPorcentajeTurnos(EstadoTurno estado, String fechaInicio, String fechaFin) {

		Session session = conexion.abrirConexion();

		session.beginTransaction();

		// Consulta HQL para obtener la cantidad de turnos en el estado especificado
		// entre las fechas especificadas
		Long cantidadTurnos = (Long) session
				.createQuery("SELECT COUNT(t) FROM Turno t " + "WHERE t.estado = :estado "
						+ "AND t.fecha BETWEEN :fechaInicio AND :fechaFin")
				.setParameter("estado", estado).setParameter("fechaInicio", fechaInicio)
				.setParameter("fechaFin", fechaFin).uniqueResult();

		session.getTransaction().commit();
		conexion.cerrarConexion();

		// Obtener el total de turnos entre las fechas dadas
		long totalTurnos = obtenerTotalTurnos(fechaInicio, fechaFin);

		// Calcular el porcentaje de turnos en el estado especificado
		return (double) cantidadTurnos / totalTurnos * 100;
	}

	public long obtenerTotalTurnos(String fechaInicio, String fechaFin) {

		Session session = conexion.abrirConexion();

		session.beginTransaction();

		// Consulta HQL para obtener el total de turnos entre las fechas especificadas
		Long totalTurnos = (Long) session
				.createQuery("SELECT COUNT(t) FROM Turno t " + "WHERE t.fecha BETWEEN :fechaInicio AND :fechaFin")
				.setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin).uniqueResult();

		session.getTransaction().commit();
		conexion.cerrarConexion();

		return totalTurnos != null ? totalTurnos : 0;
	}

	public double getPorcentajeTotalTurnosPorEstado(EstadoTurno estado, String fechaIni, String fechaFin) {
		double porcentajeT = 0;
		int cantidadTurnos = 0;
		int totalTurnos = 1;

		Session session = conexion.abrirConexion();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Object[]> objTurnos = (List<Object[]>) session
				.createQuery("SELECT t FROM Turno t WHERE t.fecha BETWEEN :fechaInicio AND :fechaFin")
				.setParameter("fechaInicio", fechaIni).setParameter("fechaFin", fechaFin).list();

		session.getTransaction().commit();
		conexion.cerrarConexion();

		for (Object objTurno2 : objTurnos) {
			Turno turno = (Turno) objTurno2;
			if (turno.getEstado().equals(estado))
				cantidadTurnos++;
		}
		if (cantidadTurnos > 0) {
			totalTurnos = objTurnos.size();
			porcentajeT = (double) cantidadTurnos / totalTurnos * 100;
		}

		return porcentajeT;
	}

	@Override
	public List<Turno> searchTurnosMedico(int legajoMedico) {
		List<Turno> turnos = null;
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			List<Turno> list = (List<Turno>) session.createQuery("FROM Turno t WHERE t.medico.legajo = :legajo")
					.setParameter("legajo", legajoMedico).list();
			turnos = list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		conexion.cerrarConexion();
		return turnos;
	}
	
	@Override
	public List<Turno> searchTurnosDiaHorario(String fecha, int hora){		
		List<Turno> turnos = null;
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			List<Turno> list = (List<Turno>) session.createQuery("FROM Turno t WHERE t.fecha = :fecha AND t.hora = :hora")
					.setParameter("fecha", fecha).setParameter("hora", hora).list();
			turnos = list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return turnos;
	}
	
    public List<Turno> listadoTurnosPorFecha(String fechaInicio, String fechaFin) {
        List<Turno> turnos = null;
        Session session = conexion.abrirConexion();
        session.beginTransaction();
        try {
            @SuppressWarnings("unchecked")
            List<Turno> list = (List<Turno>) session
                    .createQuery("FROM Turno t WHERE t.fecha BETWEEN :fechaInicio AND :fechaFin")
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .list();
            turnos = list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.getTransaction().commit();
            conexion.cerrarConexion();
        }
        return turnos;
    }

    public Turno turnoPorId(Long id) {
        Turno turno = null;
        Session session = conexion.abrirConexion();
        session.beginTransaction();
        try {
            turno = (Turno) session.createQuery("FROM Turno t WHERE t.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al buscar el turno por ID: " + e.getMessage());
        } finally {
            session.getTransaction().commit();
            conexion.cerrarConexion();
        }
        return turno;
    }
}
