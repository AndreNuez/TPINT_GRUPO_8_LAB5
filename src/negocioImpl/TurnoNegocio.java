package negocioImpl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoTurno;
import entidad.EstadoTurno;
import entidad.Jornada;
import entidad.Medico;
import entidad.Turno;
import negocio.ITurnoNegocio;

@Service("servicioTurno")
public class TurnoNegocio implements ITurnoNegocio {

	@Autowired
	private IDaoTurno daoTurno;
	
	private MedicoNegocio medNeg;
	
	private PacienteNegocio pacNeg;

	public TurnoNegocio() {

	}

	public IDaoTurno getDaoTurno() {
		return daoTurno;
	}

	public void setDaoTurno(IDaoTurno daoTurno) {
		this.daoTurno = daoTurno;
	}

	// FIN SPRING CORE

	@Override
	public boolean Add(Turno turno, MedicoNegocio medicoNg, PacienteNegocio pacienteNg) {
		// Logica para agregar el turno nuevo
		medNeg = medicoNg;
		pacNeg = pacienteNg;
		if (turnoValido(turno) && !turnoTomado(turno))
			return daoTurno.Add(turno);
		return false;
	}

	@Override
	public List<Turno> ReadAll() {

		return daoTurno.ReadAll();
	}

	@Override
	public boolean Update(Turno turno) {

		return daoTurno.Update(turno);
	}

	@Override
	public boolean Delete(Turno turno) {

		return daoTurno.Delete(turno);
	}

	// Logica para turnos disponibles
	@Override
	public ArrayList<Integer> turnosDisponiblesMedicoFecha(Medico medico, String fecha) {

		ArrayList<Turno> turnosMed = readTurnosMedico(medico);
		ArrayList<Integer> horasLibres = new ArrayList<Integer>();

		Jornada jornada = medico.getJornada();
		DayOfWeek dia = diaSemana(fecha);
		int inicio, fin;
		switch (dia) {
		case MONDAY:
			inicio = jornada.getInicioLunes();
			fin = jornada.getFinLunes();
			break;
		case TUESDAY:
			inicio = jornada.getInicioMartes();
			fin = jornada.getFinMartes();
			break;
		case WEDNESDAY:
			inicio = jornada.getInicioMiercoles();
			fin = jornada.getFinMiercoles();
			break;
		case THURSDAY:
			inicio = jornada.getInicioJueves();
			fin = jornada.getFinJueves();
			break;
		case FRIDAY:
			inicio = jornada.getInicioViernes();
			fin = jornada.getFinViernes();
			break;
		case SATURDAY:
			inicio = jornada.getInicioSabado();
			fin = jornada.getFinSabado();
			break;
		case SUNDAY:
			inicio = jornada.getInicioDomingo();
			fin = jornada.getFinDomingo();
			break;
		default:
			inicio = fin = 0;
		}

		for (int hora = inicio; hora < fin; hora++) {
			horasLibres.add(hora);
		}

		for (Turno turno : turnosMed) {
			if (turno.getFecha().equals(fecha))
				horasLibres.remove((Integer) turno.getHora());
		}

		return horasLibres;

	}

	@Override
	public ArrayList<Turno> readTurnosMedico(Medico medico) {
		try {
			List<Turno> lsDaoTurnosMed = daoTurno.searchTurnosMedico(medico.getLegajo());
			ArrayList<Turno> alsTurnosMed = new ArrayList<>(lsDaoTurnosMed);
			return alsTurnosMed;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private DayOfWeek diaSemana(String fecha) {

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de entrada, ajusta según el
																			// formato recibido
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato deseado
		String fechaFormateada = null;

		if (!fecha.contains("/")) {
			try {
				Date dFecha = inputFormat.parse(fecha);
				fechaFormateada = outputFormat.format(dFecha);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// Manejar la excepción de parseo si es necesario
			}
		}
		int d = 0;
		String formatoFecha = "dd/MM/yyyy";
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatoFecha);
			LocalDate fechaTurno = LocalDate.parse(fechaFormateada, dtf);
			d = fechaTurno.getDayOfWeek().getValue();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return DayOfWeek.of(d == 0 ? 7 : d);
	}

	// Logica para turno valido
	@Override
	public boolean turnoValido(Turno turno) {
		if (!fechaYHoraValida(turno))
			return false;
		if (!medNeg.exists(turno.getMedico()))
			return false;
		if (!pacNeg.exists(turno.getPaciente()))
			return false;
		return true;
	}

	private boolean fechaYHoraValida(Turno turno) {
		if (turno.getHora() > 23 || turno.getHora() < 0)
			return false;
		if (!medicoAtiende(turno))
			return false;

		return true;
	}

	private boolean medicoAtiende(Turno turno) {
		String fecha = turno.getFecha();
		boolean resp = false;
		resp = medNeg.medicoAtiende(turno.getMedico(), diaSemana(fecha), turno.getHora());
		return resp;
	}

	private boolean turnoTomado(Turno turno) {
		int legajoMed = turno.getMedico().getLegajo();
		String dniPac = turno.getPaciente().getDni();
		String fecha = turno.getFecha();

		List<Turno> tsDiaHorario = daoTurno.searchTurnosDiaHorario(fecha, turno.getHora());
		for (Turno tomado : tsDiaHorario) {
			if (legajoMed == tomado.getMedico().getLegajo())
				return true;
			if (dniPac == tomado.getPaciente().getDni())
				return true;
		}

		return false;
	}

	@Override
	public double obtenerPorcentajeTurnos(EstadoTurno estado, String fechaInicio, String fechaFin) {

		return daoTurno.obtenerPorcentajeTurnos(estado, fechaInicio, fechaFin);
	}

	@Override
	public long obtenerTotalTurnos(String fechaInicio, String fechaFin) {

		return daoTurno.obtenerTotalTurnos(fechaInicio, fechaFin);
	}
}
