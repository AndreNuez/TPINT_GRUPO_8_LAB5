package entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Password")
	private String password;

	@Column(name = "Perfil")
	private int perfil;
	
	@Column(name = "Activo")
	private boolean activo;

	// Agregamos Medico
//	@OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
//	private Medico medico;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	// Constructor en blanco
	public Usuario() {
		// Puedes inicializar atributos aquí si es necesario
	}

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Agregamos getters y setters para Medico
//	public Medico getMedico() {
//		return medico;
//	}
//
//	public void setMedico(Medico medico) {
//		this.medico = medico;
//	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		String mensaje = "Usuario [id=" + id + ", nombre=" + nombre + ", password=" + password + ", medico=";
//		if (medico != null)
//			mensaje += medico.getNombre() + ", " + medico.getApellido() + " - " + medico.getEspecialidad();
		mensaje += "]";
		return mensaje;
	}
}
