package resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import daoImpl.Conexion;
import daoImpl.daoEspecialidad;
import daoImpl.daoJornada;
import daoImpl.daoMedico;
import daoImpl.daoNacionalidad;
import daoImpl.daoPaciente;
import daoImpl.daoTurno;
import daoImpl.daoUsuario;
import entidad.Especialidad;
import entidad.Jornada;
import entidad.Medico;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Turno;
import entidad.Usuario;
import negocioImpl.EspecialidadNegocio;
import negocioImpl.JornadaNegocio;
import negocioImpl.MedicoNegocio;
import negocioImpl.NacionalidadNegocio;
import negocioImpl.PacienteNegocio;
import negocioImpl.TurnoNegocio;
import negocioImpl.UsuarioNegocio;

@Configuration
@ComponentScan(basePackages = {"controller", "entidad", "daoImpl", "negocioImpl"})
public class AppConfig {
	
	// BEAN CONEXION
	@Bean
	public Conexion beanConexion() { 
		Conexion conexion = new Conexion();
		return conexion;
	}
	
	// BEANS ENTIDADES
    @Bean
    public Medico beanMedico() {
        Medico medic = new Medico();
        return medic;
    }
    @Bean 
    Usuario beanUsuario() {
        Usuario user = new Usuario();
        return user;
    }
    @Bean
    public Especialidad beanEspecialidad() {
        Especialidad espe = new Especialidad();
        return espe;
    }
    @Bean
    public Paciente beanPaciente() {
        Paciente pac = new Paciente();
        return pac;
    }
    @Bean
    public Turno beanTurno() {
        Turno turn = new Turno();
        return turn;
    }
    
    @Bean
    public Nacionalidad beanNacionalidad() {
    	Nacionalidad nac = new Nacionalidad();
        return nac;
    }
    
    @Bean
    public Jornada beanJornada() {
    	Jornada jor = new Jornada();
        return jor;
    }
    
    //BEANS DAO
    @Bean
    public daoMedico beanDaoMedico() {
        daoMedico daoMedic = new daoMedico();
        return daoMedic;
    }
    @Bean
    public daoTurno beanDaoTurno() {
        daoTurno daoTurn = new daoTurno();
        return daoTurn;
    }
    @Bean
    public daoPaciente beanDaoPaciente() {
        daoPaciente daoPac = new daoPaciente();
        return daoPac;
    }
    @Bean
    public daoEspecialidad beanDaoEspecialidad() {
        daoEspecialidad daoEsp = new daoEspecialidad();
        return daoEsp;
    }
    @Bean
    public daoUsuario beanDaoUsuario() {
        daoUsuario daoUser = new daoUsuario();
        return daoUser;
    }
    @Bean
    public daoNacionalidad beanDaoNacionalidad() {
        daoNacionalidad daoNacionalidad = new daoNacionalidad();
        return daoNacionalidad;
    }
    @Bean
    public daoJornada beanDaoJornada() {
        daoJornada daoJornada = new daoJornada();
        return daoJornada;
    }
    
    // BEANS NEGOCIO
    @Bean
    public EspecialidadNegocio beanEspecialidadNegocio() {
        EspecialidadNegocio especialidadNeg = new EspecialidadNegocio();
        return especialidadNeg;
    }
    
    @Bean
    public TurnoNegocio beanTurnoNegocio() {
        TurnoNegocio turnoNeg = new TurnoNegocio();
        return turnoNeg;
    }
    
    @Bean
    public MedicoNegocio beanMedicoNegocio() {
        MedicoNegocio medicoNeg = new MedicoNegocio();
        return medicoNeg;
    }
    
    @Bean
    public PacienteNegocio beanPacienteNegocio() {
        PacienteNegocio pacienteNeg = new PacienteNegocio();
        return pacienteNeg;
    }
    
    @Bean
    public UsuarioNegocio beanUsuarioNegocio() {
        UsuarioNegocio userNeg = new UsuarioNegocio();
        return userNeg;
    }
    @Bean
    public NacionalidadNegocio beanNacionalidadNegocio() {
        NacionalidadNegocio nacionalidadNeg = new NacionalidadNegocio();
        return nacionalidadNeg;
    }
    @Bean
    public JornadaNegocio beanJornadaNegocio() {
    	JornadaNegocio jornadaNeg = new JornadaNegocio();
        return jornadaNeg;
    }
    
}
