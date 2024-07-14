package negocioImpl;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoMedico;
import dao.IDaoUsuario;
import entidad.Jornada;
import entidad.Medico;
import entidad.Usuario;
import negocio.IMedicoNegocio;

@Service("servicioMedico")
public class MedicoNegocio implements IMedicoNegocio {
	
	@Autowired
	private IDaoMedico daoMedico;
	
	@Autowired
	private IDaoUsuario daoUsuario;
	
	public MedicoNegocio() {
		
	}
	
	public IDaoMedico getDaoMedico() {
		return daoMedico;
	}

	public void setDaoMedico(IDaoMedico daoMedico) {
		this.daoMedico = daoMedico;
	}
	
	// FIN SPRING CORE
	
	@Override
	public boolean Add(Medico medico) {
		
		return daoMedico.Add(medico);
	}

	@Override
	public List<Medico> ReadAll() {
		
		return daoMedico.ReadAll();
	}

	@Override
	public boolean Update(Medico medico) {
		medico.setActivo(true);
		medico.getUsuario().setActivo(true);
		return daoMedico.Update(medico);
	}

	@Override
	public boolean Delete(Medico medico) {
		
		Usuario user = medico.getUsuario();
		user.setActivo(false); 
		
		if (daoMedico.Delete(medico) && daoUsuario.Delete(user)) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public Medico obtenerMedicoPorLegajo(int legajo) {		
		return daoMedico.obtenerMedicoPorLegajo(legajo);
	}

	@Override
	public boolean exists(Medico medico) {
		boolean estado = false;
		if (medico.getLegajo() > 0) {
			Medico m = obtenerMedicoPorLegajo(medico.getLegajo());
			if (m != null)
				estado = true;
		}
		return estado;
	}
	
	@Override
	public boolean medicoAtiende(Medico medico, DayOfWeek dia, int hora) {
		Jornada jornada = medico.getJornada();
		 switch (dia) {
	         case MONDAY:
	             return horaEnRango(hora, jornada.getInicioLunes(), jornada.getFinLunes());
	         case TUESDAY:
	        	 return horaEnRango(hora, jornada.getInicioMartes(), jornada.getFinMartes());
	         case WEDNESDAY:
	        	 return horaEnRango(hora, jornada.getInicioMiercoles(), jornada.getFinMiercoles());
	         case THURSDAY:
	        	 return horaEnRango(hora, jornada.getInicioJueves(), jornada.getFinJueves());
	         case FRIDAY:
	        	 return horaEnRango(hora, jornada.getInicioViernes(), jornada.getFinViernes());
	         case SATURDAY:
	        	 return horaEnRango(hora, jornada.getInicioSabado(), jornada.getFinSabado());
	         case SUNDAY:
	        	 return horaEnRango(hora, jornada.getInicioDomingo(), jornada.getFinDomingo());
	         default:
	             return false;
		 }
	}
		 
	 private boolean horaEnRango(int hora, int inicio, int fin) {
		 return hora >= inicio && hora < fin;
	 }

	@Override
	public Medico obtenerMedicoPorDNI(String dni) {
		return daoMedico.obtenerMedicoPorDNI(dni);
	}
	
}
