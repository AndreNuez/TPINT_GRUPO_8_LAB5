package entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Nacionalidad")

public class Nacionalidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNacionalidad;
	
	@Column(name = "Nombre")
	private String nacionalidad;
	
	public Nacionalidad(){}
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public Nacionalidad(int idNacionalidad, String nacionalidad) {
		this.idNacionalidad = idNacionalidad;
		this.nacionalidad = nacionalidad;
	}
	@Override
	public String toString() {
		return "Nacionalidad [idNacionalidad=" + idNacionalidad + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
}
