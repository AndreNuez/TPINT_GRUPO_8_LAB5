package negocioImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dao.IDaoJornada;
import entidad.Jornada;
import negocio.IJornadaNegocio;

public class JornadaNegocio implements IJornadaNegocio {

	@Autowired
	private IDaoJornada daoJornada;
	
	@Override
	public ArrayList<Jornada> readAll(int estado) {
		return daoJornada.readAll(estado);
	}
}
