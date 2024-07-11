package entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Turnos")
public class Turno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Legajo")
    private Medico medico; // Relación con la clase Medico

    @ManyToOne
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente; // Relación con la clase Paciente

    @Column(name = "Fecha")
    private String fecha;

    @Column(name = "Hora")
    private int hora;

    @Column(name = "Observacion")
    private String observacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private EstadoTurno estado; // Enumeración para el estado (pendiente, presente, ausente)

    // Constructor en blanco
    public Turno() {
        // Deja los atributos en sus valores por defecto
    }

    // Constructor con todos los atributos
    /*public Turno(Medico medico, Paciente paciente, String fecha, String hora,
                 String observacion, EstadoTurno estado) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.observacion = observacion;
        this.estado = estado;
    }*/

    // Getters y Setters
    // Puedes generar automáticamente estos métodos en tu IDE para acceder a las propiedades.

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}
	
	public long getId() {
		return id;
	}

   
    
    // Método toString
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", medico=" + medico +
                ", paciente=" + paciente +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", observacion='" + observacion + '\'' +
                ", estado=" + estado +
                '}';
    }

}
