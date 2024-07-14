package entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Medicos")
public class Medico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Legajo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int legajo;
	
	@Column(name = "DNI")
    private String dni;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Apellido")
	private String apellido;

	@Column(name = "Sexo")
	private char sexo;

	@Column(name = "FNac")
	private String fNac;

	@Column(name = "Direccion")
	private String direccion;

	/*@Column(name = "Localidad")
	private String localidad;*/
	
	@ManyToOne
	@JoinColumn(name = "Id_Localidad")
    private Localidad localidad;
	
	@ManyToOne
	@JoinColumn(name = "Id_Provincia")
    private Provincia provincia;

	@Column(name = "Correo")
	private String correo;

	@Column(name = "Telefono")
	private String telefono;

	// Agregamos atributo del tipo Usuario
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "Id_Usuario")
	private Usuario usuario;

	// Agregamos atributo del tipo Especialidad
	@ManyToOne
	@JoinColumn(name = "Id_Especialidad")
	private Especialidad especialidad;

	// Agregamos listado de turnos
	@OneToMany(mappedBy = "medico", cascade = { CascadeType.ALL })
	// @JoinColumn (name="Id_Turno")
	List<Turno> listaTurnos = new ArrayList<Turno>();
	
	@Column(name = "Activo")
	private boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "IdJornada")
	private Jornada jornada;	

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public Medico() {

	}

	public int getLegajo() {
		return this.legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getfNac() {
		return this.fNac;
	}

	public void setfNac(String fNac) {
		this.fNac = fNac;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/*public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}*/

	public String getCorreo() {
		return this.correo;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// Getters y Setters para usuario
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters y Setters para especialidad
	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	// Getters y Setters para lista turnos
	public List<Turno> getListaTurnos() {
		return listaTurnos;
	}

	public void setListaTurnos(List<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean act) {
		this.activo = act;
	}

	@Override
	public String toString() {
		return "Medico [legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fNac=" + fNac + ", direccion=" + direccion + ", localidad=" + localidad + ", correo=" + correo
				+ ", telefono=" + telefono + ", usuario=" + usuario + ", especialidad=" + especialidad
				+ ", listaTurnos=" + listaTurnos + ", activo=" + activo + ", jornada=" + jornada + "]";
	}


}
