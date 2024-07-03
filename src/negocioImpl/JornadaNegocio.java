package negocioImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDaoJornada;
import entidad.Jornada;
import negocio.IJornadaNegocio;

@Service("servicioJornada")
public class JornadaNegocio implements IJornadaNegocio {

	@Autowired
	private IDaoJornada daoJornada;

	public JornadaNegocio() {		
	}
	
	@Override
	public ArrayList<Jornada> ReadAll() {
		return daoJornada.ReadAll();
	}
}
