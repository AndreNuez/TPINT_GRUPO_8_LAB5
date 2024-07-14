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
import javax.persistence.Table;

@Entity
@Table(name = "Pacientes")
public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Direccion")
    private String direccion;
    
    @Column(name = "Sexo")
	private char sexo;

    public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@ManyToOne
	@JoinColumn(name = "Id_Localidad")
    private Localidad localidad;
    
    @ManyToOne
	@JoinColumn(name = "Id_Provincia")
    private Provincia provincia;

    @Column(name = "FechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "CorreoElectronico")
    private String correoElectronico;

    @Column(name = "Activo")
    private boolean activo;

    // Agregamos listado de turnos
    @OneToMany(mappedBy = "paciente", cascade = { CascadeType.ALL })
    //@JoinColumn(name = "id_Turno")
    List<Turno> listaTurnos = new ArrayList<Turno>();

    // Constructor en blanco
    public Paciente() {
        // Deja los atributos en sus valores por defecto
    }

    // Constructor con todos los atributos
    /*public Paciente(String nombre, String apellido, String dni, String telefono,
            String direccion, String localidad, String provincia,
            String fechaNacimiento, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }*/

    // Getters y Setters
    // Puedes generar automáticamente estos métodos en tu IDE para acceder a las
    // propiedades.

    public String getNombre() {
        return nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean actv) {
        this.activo = actv;
    }

    // Método toString
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }

}
